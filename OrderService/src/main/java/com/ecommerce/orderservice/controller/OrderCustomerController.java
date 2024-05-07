package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.TopicEnum;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.service.OrderInfoService;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/customer/orders")
public class OrderCustomerController {

    private OrderInfoService orderInfoService;
    private OrderService orderService;

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
    public ResponseEntity<Order> createOrder(HttpServletRequest request) {

        return ResponseEntity.ok(orderService.createOrder(request));
    }

}
