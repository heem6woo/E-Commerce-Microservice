package com.ecommerce.itemservice.kafka.consumer;

import com.ecommerce.common.Order;
import com.ecommerce.common.OrderStatus;
import com.ecommerce.itemservice.kafka.producer.StockProducer;
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
    @Autowired
    StockProducer stockProducer;



    @KafkaListener(id = "order", topics = "ORDERS", groupId = "stock")
    public void onEvent(Order order) {
        //Boolean flag = itemStockService.decrease(order.getItemId(),order.getSellerId(),order.getItemQuantity());
        // for test
        if(true) {
            order.setStatus(OrderStatus.CONFIRMED);
            stockProducer.send(order);
            log.info("주문번호 " + order.getId() + " 주문 완료");
        }
        else {
            order.setStatus(OrderStatus.ROLLBACK_STOCK);
            stockProducer.send(order);
            System.out.println("재고없음");
        }
    }
}
