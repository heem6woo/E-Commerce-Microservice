package com.ecommerce.orderservice.config;


import com.ecommerce.orderservice.dto.OrderStatus;
import com.ecommerce.orderservice.dto.TopicEnum;
import com.ecommerce.orderservice.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.ecommerce.orderservice.dto.TopicEnum.*;

@Configuration
@EnableKafka
@Slf4j
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean
    public StreamsBuilderFactoryBeanConfigurer configurer() {
        return fb -> fb.setStateListener((newState, oldState) -> {
            log.info("State transition from " + oldState + " to " + newState);
        });
    }

    @Bean
    public NewTopic orders() {
        return TopicBuilder.name(String.valueOf(ORDERS))
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name(String.valueOf(PAYMENTS))
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic stockTopic() {
        return TopicBuilder.name(String.valueOf(STOCK))
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public KStream<Long, Order> stream(StreamsBuilder builder) {

        Serde<Long> keySerde =  Serdes.Long();
        JsonSerde<Order> valueSerde = new JsonSerde<>(Order.class);

        KStream<Long, Order> paymentStream = builder
                .stream(String.valueOf(PAYMENTS), Consumed.with(keySerde, valueSerde));

        KStream<Long, Order> stockStream = builder
                .stream(String.valueOf(STOCK),Consumed.with(keySerde, valueSerde));

        //join records from both streams
        KStream<Long, Order> orderStream = paymentStream.join(
                stockStream,
                this::confirm,
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)),
                StreamJoined.with(keySerde, valueSerde, valueSerde)
        );

        // produce processed message to order topic
        orderStream.to(String.valueOf(ORDERS), Produced.with(keySerde, valueSerde));


        return orderStream;
    }

    @Bean
    public KTable<Long, Order> table(StreamsBuilder builder) {
        // name of ktable
        KeyValueBytesStoreSupplier store =
                Stores.persistentKeyValueStore(String.valueOf(ORDERS));

        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);

        KStream<Long, Order> stream = builder
                .stream(String.valueOf(ORDERS), Consumed.with(Serdes.Long(), orderSerde));


        return stream.toTable(Materialized.<Long, Order>as(store)
                .withKeySerde(Serdes.Long())
                .withValueSerde(orderSerde));
    }


    public Order confirm(Order orderPayment, Order orderStock) {

        Order order = Order.builder()
                .id(orderPayment.getId())
                .customerId(orderPayment.getCustomerId())
                .itemId(orderPayment.getItemId())
                .itemQuantity(orderPayment.getItemQuantity())
                .price(orderPayment.getPrice())
                .build();

        if (orderPayment.getStatus().equals(OrderStatus.ACCEPTED) &&
                orderStock.getStatus().equals(OrderStatus.ACCEPTED)) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED) &&
                orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.REJECTED);
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.ROLLBACK_STOCK);
        } else if (orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.ROLLBACK_PAYMENT);
        }
        return order;
    }

}