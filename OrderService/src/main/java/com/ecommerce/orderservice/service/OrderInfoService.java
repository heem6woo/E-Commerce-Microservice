package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.grpclient.ItemIdClient;
import com.ecommerce.orderservice.grpclient.MemberIdClient;
import com.ecommerce.orderservice.repository.OrderInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderInfoService {

    private OrderInfoRepository orderInfoRepository;

    private MemberIdClient memberIdClient;

    private ItemIdClient itemIdClient;

    public List<OrderInfo> listAllOrders(HttpServletRequest request) {

        // Header added from api gateway
        String email = request.getHeader("email");

        String role = request.getHeader("role");

        int id = memberIdClient.requestMemberId(email);

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
        int id = 1;
        return orderInfoRepository.findAllByItemId(id);
    }

    public List<OrderInfo> findAllByItemNameCustomerEmailSellerEmail(String itemName, String customerEmail, String sellerEmail) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = 1;
        int customerId = memberIdClient.requestMemberId(customerEmail);
        int sellerId = memberIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerIdAndSellerId(itemId, customerId, sellerId);
    }

    public List<OrderInfo> findAllByItemNameCustomerEmail(String itemName, String customerEmail) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = 1;
        int customerId = memberIdClient.requestMemberId(customerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerId(itemId, customerId);
    }

    public List<OrderInfo> findAllByItemNameSellerEmail(String itemName, String sellerEmail) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = 1;
        int sellerId = memberIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByItemIdAndCustomerId(itemId, sellerId);
    }

    public List<OrderInfo> findAllByCustomerEmailSellerEmail(String customerEmail, String sellerEmail) {
        int customerId = memberIdClient.requestMemberId(customerEmail);
        int sellerId = memberIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllByCustomerIdAndSellerId(customerId, sellerId);
    }

    public List<OrderInfo> findAllByItemName(String itemName) {
        //int itemId = itemClient.requestItemId(itemName);
        int itemId = 1;
        return orderInfoRepository.findAllByItemId(itemId);
    }

    public List<OrderInfo> findAllByCustomerEmail(String customerEmail) {
        int customerId = memberIdClient.requestMemberId(customerEmail);
        return orderInfoRepository.findAllByCustomerId(customerId);
    }

    public List<OrderInfo> findAllBySellerEmail(String sellerEmail) {
        int sellerId = memberIdClient.requestMemberId(sellerEmail);
        return orderInfoRepository.findAllBySellerId(sellerId);
    }
}
