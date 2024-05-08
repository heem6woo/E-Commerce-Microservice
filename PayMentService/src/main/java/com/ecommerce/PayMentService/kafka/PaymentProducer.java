package com.ecommerce.PayMentService.kafka;

import com.ecommerce.PayMentService.dto.TopicEnum;
import com.ecommerce.common.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<Long, Order> kafkaTemplate;
    public void send(Order order) {
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC,String.valueOf(TopicEnum.PAYMENTS))
                .setHeader(KafkaHeaders.KEY,order.getId())
                .build();
        kafkaTemplate.send(message);
    }
}
