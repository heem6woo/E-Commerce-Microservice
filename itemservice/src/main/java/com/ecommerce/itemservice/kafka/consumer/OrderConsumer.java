package com.ecommerce.itemservice.kafka.consumer;

import com.ecommerce.itemservice.kafka.payload.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {
    @KafkaListener(topics = "order")
    public void consumeJsonMsg(Order order) {
        //msg 처리
    }
}
