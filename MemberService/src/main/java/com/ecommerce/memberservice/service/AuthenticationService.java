package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.Role;
import com.ecommerce.memberservice.exception.AuthenticationException;
import com.ecommerce.memberservice.repository.MemberRepository;
import com.ecommerce.memberservice.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;
    private final MemberService memberService;

    private final Logger logger = log;


    public String register(RegisterRequest request) {

//        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
//
//        }
// Seller Name duplication check
        if (request.getRole().equals(Role.SELLER)) {
            Member found = memberRepository.findByNameAndRole(request.getName(), Role.SELLER);
            if (found != null) {
                throw new AuthenticationException("Seller name cannot be duplicated", HttpStatus.BAD_REQUEST);
            }
        }

        if (request.getRole().equals(Role.ADMIN)) {
            throw new AuthenticationException("You do not have permission to create an ADMIN member", HttpStatus.FORBIDDEN);
        }

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        Member savedMember = memberRepository.save(member);

        return "Successfully Registered";
    }

    public AuthenticateResponse authenticate(AuthenticateRequest request) throws ChangeSetPersister.NotFoundException {
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
                var accessToken =  jwtService.generateAccessToken(user);

                var authResponse = AuthenticateResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                // Create ObjectMapper instance
                ObjectMapper objectMapper = new ObjectMapper();

                // Serialize the AuthenticateResponse object to JSON
                String jsonResponse = objectMapper.writeValueAsString(authResponse);

                // Set the content type to JSON in the response
                response.setContentType("application/json");

                // Write the JSON response to the output stream
                response.getOutputStream().print(jsonResponse);

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


    // Only Admin Member can access to this
    public String grantPermission(HttpServletRequest httpRequest, ChangePermissionRequest request)
            throws ChangeSetPersister.NotFoundException {
        //var member = (Member) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        // extract member from the principal (passed from  jwt filter)
        Optional<Member> adminMember = memberRepository.findByEmail(httpRequest.getHeader("email"));

        log.info(adminMember.toString());
        if(adminMember.isEmpty()){
            throw new IllegalStateException("Not Found");
        }

        // 저장된 패스워드와 유저가 기입한 패스워드 비교
        if (!passwordEncoder.matches(request.getPassword(), adminMember.get().getPassword())) {
            throw new IllegalStateException("Wrong Password!");
        }

        Member member = memberService.findByEmail(request.getEmail());

        member.setRole(request.getRole());

        memberRepository.save(member);

        return request.getRole() + " is granted to " +request.getEmail();
    }

}
