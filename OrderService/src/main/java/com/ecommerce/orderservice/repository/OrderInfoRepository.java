package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findAllByCustomerId(int id);

    List<OrderInfo> findAllBySellerId(int id);

    List<OrderInfo> findAllByItemId(int id);

    List<OrderInfo> findAllByCustomerIdAndSellerId(int customerId, int sellerId);

    List<OrderInfo> findAllByItemIdAndCustomerId(int itemId, int customerId);

    List<OrderInfo> findAllByItemIdAndCustomerIdAndSellerId(int itemId, int customerId, int sellerId);
}
