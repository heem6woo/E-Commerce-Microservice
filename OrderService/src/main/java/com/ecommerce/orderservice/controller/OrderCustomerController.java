package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.service.OrderInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/customer/orders")
public class OrderCustomerController {

    private OrderInfoService orderInfoService;

    @GetMapping("")
    public ResponseEntity<List<OrderInfo>> listAllOrders(HttpServletRequest request) {

        return ResponseEntity.ok(orderInfoService.listAllOrders(request));
    }

}
