package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface SalesInfoRepository extends JpaRepository<SalesInfo, Integer> {
    // Custom query to update sales info
    @Transactional
    @Modifying
    @Query("UPDATE SalesInfo s SET s.itemCount = :itemCount, s.itemPrice = :itemPrice, s.itemStatus = :itemStatus WHERE s.salesInfoId = :salesInfoId")
    void updateSalesInfo(int salesInfoId, int itemCount, int itemPrice, byte itemStatus);

    @Override
    Optional<SalesInfo> findById(Integer integer);

    Optional<SalesInfo> findByItemAndSellerId(Item item, Integer sellerId);


}