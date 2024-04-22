package com.ecommerce.apigateway.config;

import com.ecommerce.apigateway.jwt.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthentication implements GatewayFilterFactory<JwtAuthentication.Config> {
    private static final String ROLE_KEY = "role";

    private final JwtUtil jwtUtil;

    //private final RouteValidator routeValidator;

    @Value("${application.security.jwt.prefix}")
    public String TOKEN_PREFIX;

    @Data
    public static class Config {
        private String role;
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
            ServerHttpRequest request = exchange.getRequest();

            var authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
                return onError(exchange,"Invalid Header", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }

            String token = authHeader.substring(7);

            log.info("whwhhw");
            //if (routeValidator.isSecured.test(request)) {

            log.info(token);
                if (jwtUtil.isTokenValid(token))
                    return this.onError(exchange, "Token is invalid", HttpStatus.UNAUTHORIZED);

                String role = jwtUtil.extractRole(token);

                log.info(config.role);
                log.info(role);

                if (!role.contains(config.role)) {

                    return onError(exchange, "Invalid permission", HttpStatus.UNAUTHORIZED);
                }

                this.populateRequestWithHeaders(exchange, token);

                log.info(String.valueOf(exchange.getRequest().getBody()));
            //}
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(ROLE_KEY);
    }


    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        var header = request.getHeaders().getOrEmpty("Authorization").get(0);
        return header.replace(TOKEN_PREFIX,"").trim();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private boolean isPrefixMissing(ServerHttpRequest request) {
        var header = request.getHeaders().getFirst ("Authorization");
        assert header != null;
        return !header.startsWith(TOKEN_PREFIX);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        exchange.getRequest().mutate()
                .header("Autherization", TOKEN_PREFIX + " " + token)
                .header("email", String.valueOf(jwtUtil.extractUsername(token)))
                .header("role", String.valueOf(jwtUtil.extractRole(token)))
                .build();
    }
}
