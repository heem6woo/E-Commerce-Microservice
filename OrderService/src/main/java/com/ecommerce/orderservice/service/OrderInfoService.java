package com.ecommerce.orderservice.service;

import com.ecommerce.common.Order;
import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.grpclient.ItemIdClient;
import com.ecommerce.orderservice.grpclient.CustomerIdClient;
import com.ecommerce.orderservice.repository.OrderInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderInfoService {

    private final OrderInfoRepository orderInfoRepository;

    private final CustomerIdClient customerIdClient;

    private final ItemIdClient itemIdClient;

    public List<OrderInfo> listAllOrders(HttpServletRequest request) {

        // Header added from api gateway
        String email = request.getHeader("email");

        String role = request.getHeader("role");

        int id = customerIdClient.requestMemberId(email);

        if (role.contains("SELLER")) {
            return orderInfoRepository.findAllBySellerId(id);
        } else if (role.contains("CUSTOMER")) {
            return orderInfoRepository.findAllByCustomerId(id);
        } else {
            return orderInfoRepository.findAll();
        }

    }

    public List<OrderInfo> listAllOrdersForItem(String itemName) {
        // int id = itemClient.requestItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        return orderInfoRepository.findAllByItemId(itemId);
    }

    public List<OrderInfo> findAllByItemNameCustomerEmailSellerEmail(String itemName, String customerEmail, String sellerEmail) {
        int itemId = itemIdClient.requestItemId(itemName);

        int customerId = customerIdClient.requestMemberId(customerEmail);
        int sellerId = customerIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerIdAndSellerId(itemId, customerId, sellerId);
    }

    public List<OrderInfo> findAllByItemNameCustomerEmail(String itemName, String customerEmail) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        int customerId = customerIdClient.requestMemberId(customerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerId(itemId, customerId);
    }

    public List<OrderInfo> findAllByItemNameSellerEmail(String itemName, String sellerEmail) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        int sellerId = customerIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerId(itemId, sellerId);
    }

    public List<OrderInfo> findAllByCustomerEmailSellerEmail(String customerEmail, String sellerEmail) {
        int customerId = customerIdClient.requestMemberId(customerEmail);
        int sellerId = customerIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByCustomerIdAndSellerId(customerId, sellerId);
    }

    public List<OrderInfo> findAllByItemName(String itemName) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        return orderInfoRepository.findAllByItemId(itemId);
    }

    public List<OrderInfo> findAllByCustomerEmail(String customerEmail) {
        int customerId = customerIdClient.requestMemberId(customerEmail);
        return orderInfoRepository.findAllByCustomerId(customerId);
    }

    public List<OrderInfo> findAllBySellerEmail(String sellerEmail) {
        int sellerId = customerIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllBySellerId(sellerId);
    }

    public void save(Order order) {

        orderInfoRepository.save(OrderInfo.builder()
                .quantity(order.getItemQuantity())
                .itemId(order.getItemId())
                .customerId(order.getCustomerId())
                .sellerId(order.getSellerId())
                .price(order.getPrice())
                .build());
    }
}
