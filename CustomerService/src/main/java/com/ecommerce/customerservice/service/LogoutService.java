package com.ecommerce.customerservice.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenBlackListService tokenBlackListService;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            return;
        }
        jwt = authHeader.substring(7);

        boolean blackListed = tokenBlackListService.isBlackListed(jwt);

        if (!blackListed) {
            tokenBlackListService.addToBlackList(jwt);
        }

    }
}