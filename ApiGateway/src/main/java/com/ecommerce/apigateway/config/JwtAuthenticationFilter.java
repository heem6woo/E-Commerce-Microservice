package com.ecommerce.apigateway.config;

import com.ecommerce.apigateway.jwt.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter implements GatewayFilterFactory<JwtAuthenticationFilter.Config> {
    private static final String ROLE = "role";
    private static final String TOKEN_TYPE = "tokenType";

    private final JwtUtil jwtUtil;

    //private final RouteValidator routeValidator;

    @Value("${application.security.jwt.prefix}")
    public String TOKEN_PREFIX;

    @Data
    public static class Config {
        private String role;
        private String tokenType;
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            //if (routeValidator.isSecured.test(request)) {
            ServerHttpRequest request = exchange.getRequest();

            var authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
                return onError(exchange,"Invalid Header", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

            String token = authHeader.substring(7);

            if (jwtUtil.isTokenValid(token))
                return this.onError(exchange, "Token is invalid", HttpStatus.UNAUTHORIZED);


            // access token have to check its role
            // in case of refresh token, pass to the next step (reissue refresh token or logout)
            if(config.tokenType == null || config.tokenType.contains("access")) {
                jwtUtil.isAccessToken(token);

                String role = jwtUtil.extractRole(token);

                // if there is an assigned role, check the role from the token and filter's role
                if (config.role != null && !config.role.contains(role)) {
                    return onError(exchange, "Invalid permission", HttpStatus.UNAUTHORIZED);
                }

            }


            updateRequestWithHeaders(exchange, token);
            //log.info(exchange.getRequest().getHeaders().getOrEmpty("Authorization").get(0));
            //}
            return chain.filter(exchange);
        };
    }

    // it supports to read fileds that is written in configuration files(YAML, properties)
    // the name of arguments is assigned in above, role and tokenType
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(ROLE,TOKEN_TYPE);
    }


    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        exchange.getAttributes().put("Error", err);
        return response.setComplete();
    }


    private void updateRequestWithHeaders(ServerWebExchange exchange, String token) {
        exchange.getRequest().mutate()
                .header("email", String.valueOf(jwtUtil.extractUsername(token)))
                .header("role", String.valueOf(jwtUtil.extractRole(token)))
                .build();
    }
}
