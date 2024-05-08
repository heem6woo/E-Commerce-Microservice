package com.ecommerce.orderservice.kafka;

import com.ecommerce.orderservice.dto.TopicEnum;
import com.ecommerce.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<Long, Order> kafkaTemplate;

    public void sendMessage(Order order) {
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, String.valueOf(TopicEnum.ORDERS))
                .build();


        log.info("Sent: {}", order);

        kafkaTemplate.send(String.valueOf(TopicEnum.ORDERS), order.getId(), order);


    }
}
