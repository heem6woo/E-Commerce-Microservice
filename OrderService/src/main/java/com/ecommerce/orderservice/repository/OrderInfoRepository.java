package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
}
