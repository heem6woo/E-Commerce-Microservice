package com.ecommerce.memberservice.config;

import com.ecommerce.memberservice.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests( req -> req
                        // whitelist for authorization, authorize all the request within lists
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/members/**").hasRole(String.valueOf(Role.ADMIN))
                        .requestMatchers("/api/v1/customers/**").hasRole(String.valueOf(Role.CUSTOMER))
                        .requestMatchers("/api/v1/sellers/**").hasRole(String.valueOf(Role.SELLER))
                        .anyRequest()// any other request required authentication
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request,response,authentication) ->
                                SecurityContextHolder.clearContext()));


        return http.build();
    }

}
