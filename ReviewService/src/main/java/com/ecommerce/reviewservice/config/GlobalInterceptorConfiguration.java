package com.ecommerce.reviewservice.config;

import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
//public class GlobalInterceptorConfiguration {
//
//    /**
//     * Creates a new {@link LogGrpcInterceptor} bean and adds it to the global interceptor list. As an alternative you
//     * can directly annotate the {@code LogGrpcInterceptor} class and it will automatically be picked up by spring's
//     * classpath scanning.
//     *
//     * @return The newly created bean.
//     */
//    @GrpcGlobalClientInterceptor
//    LogGrpcInterceptor logClientInterceptor() {
//        return new LogGrpcInterceptor();
//    }
//
//}