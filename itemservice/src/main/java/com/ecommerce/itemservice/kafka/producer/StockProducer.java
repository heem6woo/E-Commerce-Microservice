package com.ecommerce.itemservice.kafka.producer;


import com.ecommerce.itemservice.kafka.payload.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  public void send(Stock order) {
      Message<Stock> message = MessageBuilder
              .withPayload(order)
              .setHeader(KafkaHeaders.TOPIC,"order")
              .build();

      kafkaTemplate.send(message);
  }
}
