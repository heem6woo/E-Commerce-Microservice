package com.ecommerce.memberservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

//    private final TokenBlackListService tokenBlackListService;
    private final RedisService redisService;
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            return;
        }
        refreshToken = authHeader.substring(7);
        email = jwtService.extractUsername(refreshToken);
        if(email == null || !Objects.equals(redisService.getValues(email), refreshToken)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        redisService.deleteValues(email);
//        boolean blackListed = tokenBlackListService.isBlackListed(jwt);
//
//        if (!blackListed) {
//            tokenBlackListService.addToBlackList(jwt);
//        }


    }
}