package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.ItemRepository;
import com.ecommerce.itemservice.repository.SalesInfoRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired; // Autowired를 임포트해야 함
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ItemStockService {


    @Autowired
    private SalesInfoRepository salesInfoRepository; // StockRepository 필드 추가
    @Autowired
    private ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW) // 재고관리
    public Boolean decrease(Integer id,Integer sellerId, Integer qty) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다 id : " + id));
        SalesInfo salesInfo = salesInfoRepository.findByItemAndSellerId(item, sellerId).orElseThrow(() -> new IllegalArgumentException("해당하는 salesinfo가 없습니다 id : " + sellerId));;

        if(salesInfo.stockCheck(qty) && salesInfo.getItemStatus() == 1){
            salesInfo.setItemCount(salesInfo.getItemCount() - qty);
            salesInfoRepository.save(salesInfo);
            return true;
        }
        log.info("재고없음");

        return false;

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 재고관리
    public Boolean rollBack(Integer id,Integer sellerId, Integer qty) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다 id : " + id));
        SalesInfo salesInfo = salesInfoRepository.findByItemAndSellerId(item, sellerId).orElseThrow(() -> new IllegalArgumentException("해당하는 salesinfo가 없습니다 id : " + sellerId));;

        if(salesInfo.getItemStatus() == 1){
            salesInfo.setItemCount(salesInfo.getItemCount() + qty);
            salesInfoRepository.save(salesInfo);
            return true;
        }
        log.info("이상상태");

        return false;
    }

}
