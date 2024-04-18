package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.Role;
import com.ecommerce.memberservice.repo.MemberRepository;
import com.ecommerce.memberservice.vo.AuthenticateRequest;
import com.ecommerce.memberservice.vo.AuthenticateResponse;
import com.ecommerce.memberservice.vo.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;



    public String registerCustomer(RegisterRequest request) {

//        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
//
//        }

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Customer)
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
}
