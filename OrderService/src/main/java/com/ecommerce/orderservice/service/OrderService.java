package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.TopicEnum;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.grpclient.ItemIdClient;
import com.ecommerce.orderservice.grpclient.MemberIdClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final StreamsBuilderFactoryBean kafkaStreamsFactory;


    private final MemberIdClient memberIdClient;

    private final ItemIdClient itemIdClient;

    public Order createOrder(HttpServletRequest request) {




    }

    public List<Order> listAllOrders(HttpServletRequest request) {

        List<Order> orders = new ArrayList<>();

        // Queryable Store
        ReadOnlyKeyValueStore<Long, Order> store = Objects.requireNonNull(kafkaStreamsFactory
                        .getKafkaStreams())
                .store(StoreQueryParameters.fromNameAndType(
                        String.valueOf(TopicEnum.ORDERS),
                        QueryableStoreTypes.keyValueStore()));

        int memberId = memberIdClient.requestMemberId(request.getHeader("email"));
        KeyValueIterator<Long, Order> it = store.all();

        while(it.hasNext()) {
            KeyValue<Long, Order> kv = it.next();
            if(kv.value.getCustomerId() == memberId) {
                orders.add(kv.value);
            }

        }

        return orders;

    }
}
