package com.ecommerce.orderservice.controller;

import com.ecommerce.common.Order;
import com.ecommerce.orderservice.service.OrderInfoService;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/seller/orders")
@RequiredArgsConstructor
public class OrderSellerCustomer {

    private OrderInfoService orderInfoService;

    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<Order>> listAllOrders(HttpServletRequest request) {

        return ResponseEntity.ok(orderService.listAllOrders(request));
    }

}
