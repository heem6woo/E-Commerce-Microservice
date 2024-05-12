package com.ecommerce.orderservice.controller;


import com.ecommerce.common.Order;
import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.service.OrderInfoService;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderInfoService orderInfoService;

    private final OrderService orderService;

    // requesting order

    @GetMapping("")
    public ResponseEntity<List<Order>> listAllOrdersInProgress(HttpServletRequest request) {

        return ResponseEntity.ok(orderService.listAllOrders(request));
    }


    @GetMapping("/items/{item-name}")
    public ResponseEntity<List<OrderInfo>> listAllOrdersForItem(@PathVariable("item-name") String itemName) {
        return ResponseEntity.ok(orderInfoService.listAllOrdersForItem(itemName));
    }

    // Request order


    // ADMIN ONLY
    @GetMapping("/")
    public ResponseEntity<List<OrderInfo>> listAllReviews(@RequestParam(name = "item-name", required = false) String itemName,
                                                       @RequestParam(name = "customer-email", required = false) String customerEmail,
                                                          @RequestParam(name = "seller-email", required = false) String sellerEmail) {

        if(itemName != null && customerEmail != null && sellerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllByItemNameCustomerEmailSellerEmail(itemName,customerEmail,sellerEmail));
        }
        if(itemName != null && customerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllByItemNameCustomerEmail(itemName,customerEmail));
        }
        if(itemName != null && sellerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllByItemNameSellerEmail(itemName,sellerEmail));
        }
        if(customerEmail != null && sellerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllByCustomerEmailSellerEmail(customerEmail,sellerEmail));
        }
        if(itemName != null) {
            return ResponseEntity.ok(orderInfoService.findAllByItemName(itemName));
        }
        if(customerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllByCustomerEmail(customerEmail));
        }
        if(sellerEmail != null) {
            return ResponseEntity.ok(orderInfoService.findAllBySellerEmail(sellerEmail));
        }

        //해당 상품의 모든 리뷰 쿼리 findAllByItemId
        return ResponseEntity.ok(null);
    }

    //
}
