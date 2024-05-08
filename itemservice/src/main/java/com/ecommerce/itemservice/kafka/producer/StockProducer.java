package com.ecommerce.itemservice.kafka.producer;


import com.ecommerce.itemservice.dto.TopicEnum;
import com.ecommerce.itemservice.kafka.payload.Stock;
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
  private final KafkaTemplate<Long, Stock> kafkaTemplate;
  public void send(Stock stock) {
      Message<Stock> message = MessageBuilder
              .withPayload(stock)
              .setHeader(KafkaHeaders.TOPIC,String.valueOf(TopicEnum.STOCK))
              .build();
      kafkaTemplate.send(String.valueOf(TopicEnum.STOCK),stock.getId(),stock);
  }
}
