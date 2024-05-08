package com.ecommerce.itemservice.kafka.consumer;

import com.ecommerce.itemservice.dto.TopicEnum;
import com.ecommerce.itemservice.kafka.payload.Order;
import com.ecommerce.itemservice.service.ItemStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {
    @Autowired
    ItemStockService itemStockService;
    @KafkaListener(topics = "ORDERS", groupId = "stock")
    public void consumeJsonMsg(Order order) {
        Boolean flag = false;
        flag = itemStockService.decrease(order.getItemId(),order.getSellerId(),order.getItemQuantity());
        if(flag)
            System.out.println(order.getPrice()+"아이템 가격");
        else System.out.println("재고없음");
    }
}
