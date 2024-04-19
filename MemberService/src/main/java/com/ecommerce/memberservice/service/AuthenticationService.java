package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.*;
import com.ecommerce.memberservice.repo.MemberRepository;
import com.ecommerce.memberservice.vo.AuthenticateRequest;
import com.ecommerce.memberservice.vo.AuthenticateResponse;
import com.ecommerce.memberservice.vo.ChangePasswordRequest;
import com.ecommerce.memberservice.vo.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    private final Logger logger = log;


    public String register(RegisterRequest request) {

//        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
//
//        }

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        Member savedMember = memberRepository.save(member);

        return "Successfully Registered";
    }

    public AuthenticateResponse authenticateCustomer(AuthenticateRequest request) throws ChangeSetPersister.NotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        ); // check valid email and password

        Member savedUser = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        var accessToken = jwtService.generateAccessToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);

        // save email, refresh Token pair to redis
        redisService.setValues(savedUser.getEmail(), refreshToken);

        return AuthenticateResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // Check the token like JwtFilter's internal filter
    // it takes refresh token
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // "AUTHORIZATIONOn"
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            return;
        }
        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractUsername(refreshToken); // todo extract the userEmail from JWT token

        var savedRefreshToken = redisService.getValues(userEmail);

        if (userEmail != null && Objects.equals(savedRefreshToken, refreshToken)) {

            // verify whether username is in the database.
            var user = memberRepository.findByEmail(userEmail).orElseThrow();
            //logger.info(String.valueOf(user));

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken =  jwtService.generateRefreshToken(user);

                var authResponse = AuthenticateResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }


    public String changePassword(ChangePasswordRequest request, Principal connectedUser) {
        //var member = (Member) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        var member = (Member) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // 저장된 패스워드와 유저가 기입한 패스워드 비교
        if (!passwordEncoder.matches(request.getCurrentPassword(), member.getPassword())) {
            throw new IllegalStateException("Wrong Password!");
        }
        if (! request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not same!");
        }

        //update the password
        member.setPassword(passwordEncoder.encode(request.getNewPassword()));

        memberRepository.save(member);

        return "Password Successfully Changed";
    }
}
