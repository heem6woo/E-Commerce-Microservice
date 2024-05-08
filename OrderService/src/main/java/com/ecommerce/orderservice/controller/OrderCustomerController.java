package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.common.Order;
import com.ecommerce.orderservice.service.OrderInfoService;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/customer/orders")
public class OrderCustomerController {

    private final OrderInfoService orderInfoService;
    private final OrderService orderService;

    /*
    @GetMapping("")
    public ResponseEntity<List<OrderInfo>> listAllOrders(HttpServletRequest request) {

        return ResponseEntity.ok(orderInfoService.listAllOrders(request));
    }

     */
    @GetMapping("")
    public ResponseEntity<List<Order>> listAllOrders(HttpServletRequest request) {

        return ResponseEntity.ok(orderService.listAllOrders(request));
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest) {

        return ResponseEntity.ok(orderService.createOrder(request, orderRequest));
    }

    // Only for testing
    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Order> updateOrder(HttpServletRequest request, @PathVariable long id, @PathVariable String status) {

        return ResponseEntity.ok(orderService.updateOrder(request, id, status));
    }

}
