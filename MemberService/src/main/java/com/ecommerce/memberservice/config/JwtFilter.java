package com.ecommerce.memberservice.config;

import com.ecommerce.memberservice.exception.AuthenticationException;
import com.ecommerce.memberservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        if (request.getServletPath().contains("/api")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            filterChain.doFilter(request, response);
            throw new AuthenticationException("Invalid Header", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

        String jwt = authHeader.substring(7);

        try {
            String userEmail = jwtService.extractUsername(jwt);

            jwtService.isAccessToken(jwt);

            if (userEmail != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                // verify whether username is in the database.
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                logger.info(userDetails.getAuthorities());
                // validate JWT
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() // 토큰에 역활(Role) 배정
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // set authentication with username and password
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }


            }
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            response.sendError(ex.getHttpStatus().value(), ex.getMessage());
        }

        filterChain.doFilter(request, response);

    }

}
