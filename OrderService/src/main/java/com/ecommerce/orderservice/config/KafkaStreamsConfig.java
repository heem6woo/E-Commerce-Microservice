package com.ecommerce.orderservice.config;


import com.ecommerce.common.OrderStatus;
import com.ecommerce.common.Order;
import com.ecommerce.orderservice.service.OrderInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;

import static com.ecommerce.orderservice.dto.TopicEnum.*;

@Configuration
@EnableKafka
@Slf4j
@RequiredArgsConstructor
@EnableKafkaStreams
public class KafkaStreamsConfig {

    private final OrderInfoService orderInfoService;


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

        //join may have to be changed to outer join
        //since if one of the stream is not available, the order should be rejected
        stockStream.join(
                paymentStream,
                this::confirm,
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)),
                StreamJoined.with(keySerde, valueSerde, valueSerde)
        ).to(String.valueOf(ORDERS), Produced.with(keySerde, valueSerde));

//        KStream<Long, Order> orderStream = stockStream.mapValues(
//                value -> {
//                    return confirm(value, value);
//                }
//        );


        return stockStream;
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


    public Order confirm(Order orderStock, Order orderPayment) {
        Order order = Order.builder()
                .id(orderStock.getId())
                .customerId(orderStock.getCustomerId())
                .itemId(orderStock.getItemId())
                .itemQuantity(orderStock.getItemQuantity())
                .price(orderStock.getPrice())
                .build();

        if (orderPayment.getStatus().equals(OrderStatus.ACCEPTED) &&
                orderStock.getStatus().equals(OrderStatus.ACCEPTED)) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED) &&
                orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.REJECTED);
            orderInfoService.deleteById(order.getId());
        } else if (orderPayment.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.ROLLBACK_STOCK);
            orderInfoService.deleteById(order.getId());
        } else if (orderStock.getStatus().equals(OrderStatus.REJECTED)) {
            order.setStatus(OrderStatus.ROLLBACK_PAYMENT);
            orderInfoService.deleteById(order.getId());
        }
        return order;
    }

}