package com.ecommerce.itemservice.kafka.producer;

import com.ecommerce.itemservice.kafka.payload.Order;
import com.ecommerce.itemservice.kafka.payload.Stock;
import com.ecommerce.itemservice.kafka.payload.TopicEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class StockProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  public void send(Order order) {
      Message<Order> message = MessageBuilder
              .withPayload(order)
              .setHeader(KafkaHeaders.TOPIC, String.valueOf(TopicEnum.STOCK))
              .setHeader(KafkaHeaders.KEY, order.getId()) // 키 설정
              .build();
      kafkaTemplate.send(message);
  }
}
