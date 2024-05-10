package com.ecommerce.orderservice.config;

import com.ecommerce.orderservice.exception.OrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class OrderConfig {
    private final OrderHandler orderHandler;
}
