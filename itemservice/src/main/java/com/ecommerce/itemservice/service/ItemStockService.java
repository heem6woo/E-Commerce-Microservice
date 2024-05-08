package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.ItemStockRepository;
import org.springframework.beans.factory.annotation.Autowired; // Autowired를 임포트해야 함
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemStockService {
    @Autowired
    private ItemStockRepository itemStockRepository; // StockRepository 필드 추가

    @Transactional(propagation = Propagation.REQUIRES_NEW) // 재고관리
    public Boolean decrease(Integer id,Integer sellerId, Integer qty) {
        SalesInfo salesInfo = itemStockRepository.findByIdAndSellerId(id, sellerId);
        if (salesInfo == null) {
            throw new RuntimeException("Stock not found");
        }
        return salesInfo.stockCheck(qty);
    }
}
