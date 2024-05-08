package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.entity.SalesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStockRepository extends JpaRepository<SalesInfo, Integer> {
    SalesInfo findByIdAndSellerId(Integer id, Integer sellerId);
}
