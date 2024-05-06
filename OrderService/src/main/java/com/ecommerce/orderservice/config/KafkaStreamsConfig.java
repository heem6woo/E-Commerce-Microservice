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
            System.out.println("State transition from " + oldState + " to " + newState);
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
        //provides serialization and deserialization in JSON format


        Serde<Long> keySerde =  Serdes.Long();// same as // Serdes.LongSerde keySerde=new Serdes.LongSerde();
        JsonSerde<Order> valueSerde = new JsonSerde<>(Order.class);

        // Kafka stream => it's a record stream that represents key & value pairs
        // key and value serdes means Key & value serializers & deserializers
        //of course, the key in our case is the order id
        KStream<Long, Order> paymentStream = builder
                .stream(String.valueOf(PAYMENTS), Consumed.with(keySerde, valueSerde));//Consumed With == passing some parameters for configuring the generated stream

        KStream<Long, Order> stockStream = builder
                .stream(String.valueOf(STOCK),Consumed.with(keySerde, valueSerde));

        //join records from both tables
        KStream<Long, Order> orderStream = paymentStream.join(
                        stockStream,
                        this::confirm,//the value joiner == the one responsible for joining the two records
                        JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)), // timestamps of matched records must fall within this window of time
                        StreamJoined.with(keySerde, valueSerde, valueSerde)//the key must be the same, 1st stream serde, 2nd stream serde
                )
                .peek((k,v)->log.info("Kafka stream match: key[{}],value[{}]"));

        return orderStream;
    }


    public Order confirm(Order orderPayment, Order orderStock) {

        Order order = Order.builder()
                .id(orderPayment.getId())
                .customerId(orderPayment.getCustomerId())
                .productId(orderPayment.getProductId())
                .productCount(orderPayment.getProductCount())
                .price(orderPayment.getPrice())
                .build();

        if (orderPayment.getStatus().equals(OrderStatus.ACCEPTED) &&
                orderStock.getStatus().equals(OrderStatus.ACCEPTED)) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED) &&
                orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.REJECTED);
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED) ||
                orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.ROLLBACK);
        }
        return order;
    }

}