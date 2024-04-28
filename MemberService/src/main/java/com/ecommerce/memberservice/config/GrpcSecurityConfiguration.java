package com.ecommerce.memberservice.config;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.annotation.Nullable;

@Configuration
public class GrpcSecurityConfiguration {

    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        // You can return an instance of the GrpcAuthenticationReader you want to use
        // For example, NullGrpcAuthenticationReader if you don't want any authentication
        return new GrpcAuthenticationReader() {
            @Nullable
            @Override
            public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers) throws AuthenticationException {
                return null;
            }
        };
    }

}