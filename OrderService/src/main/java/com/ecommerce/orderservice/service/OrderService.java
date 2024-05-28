package com.ecommerce.orderservice.service;

import com.ecommerce.grpc.ItemReply;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.common.OrderStatus;
import com.ecommerce.orderservice.dto.TopicEnum;
import com.ecommerce.common.Order;
import com.ecommerce.orderservice.entity.OrderInfo;
import com.ecommerce.orderservice.exception.OrderException;
import com.ecommerce.orderservice.grpclient.ItemIdClient;
import com.ecommerce.orderservice.grpclient.CustomerIdClient;
import com.ecommerce.orderservice.grpclient.ItemInfoClient;
import com.ecommerce.orderservice.grpclient.SellerIdClient;
import com.ecommerce.orderservice.kafka.OrderProducer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final StreamsBuilderFactoryBean kafkaStreamsFactory;

    private final CustomerIdClient customerIdClient;

    private final ItemInfoClient itemInfoClient;

    private final SellerIdClient sellerIdClient;

    private final OrderProducer orderProducer;

    private final OrderInfoService orderInfoService;

    @Transactional
    public Order createOrder(HttpServletRequest request, OrderRequest orderRequest) throws Exception {

        int customerId = customerIdClient.requestMemberId(request.getHeader("email"));
        int sellerId = sellerIdClient.requestMemberId(orderRequest.getSellerName());
        ItemReply itemReply = itemInfoClient.requestItemInfo(sellerId, orderRequest.getItemName());


        itemValidation(orderRequest, itemReply);

        Order order = Order.builder()
                .customerId(customerId)
                .sellerId(sellerId)
                .itemId(itemReply.getItemId())
                .price(orderRequest.getPrice())
                .itemQuantity(orderRequest.getQuantity())
                .status(OrderStatus.PLACED)
                .build();

        OrderInfo orderInfo =  orderInfoService.save(order);

        order.setId(orderInfo.getId());

        orderProducer.sendMessage(order);

        order.setItemQuantity(itemReply.getItemCount());
        return order;
    }

    private void itemValidation(OrderRequest orderRequest, ItemReply itemReply) throws OrderException {

        if (orderRequest.getQuantity() > itemReply.getItemCount() ||
                orderRequest.getPrice() != itemReply.getItemPrice() ||
                itemReply.getItemStatus() == 0)
        {

            throw new OrderException("Order is invalid!", HttpStatus.BAD_REQUEST);
        }

    }

    public List<Order> listAllOrders(HttpServletRequest request) {

        List<Order> orders = new ArrayList<>();

        // Queryable Store
        ReadOnlyKeyValueStore<Long, Order> store = Objects.requireNonNull(kafkaStreamsFactory
                        .getKafkaStreams())
                .store(StoreQueryParameters.fromNameAndType(
                        String.valueOf(TopicEnum.ORDERS),
                        QueryableStoreTypes.keyValueStore()));

        int memberId = customerIdClient.requestMemberId(request.getHeader("email"));

        KeyValueIterator<Long, Order> it = store.all();

        while(it.hasNext()) {
            KeyValue<Long, Order> kv = it.next();
            if(request.getHeader("role").contains("SELLER")) {
                if(kv.value.getSellerId() == memberId) {
                    orders.add(kv.value);
                }
            } else if(request.getHeader("role").contains("CUSTOMER")){
                if(kv.value.getCustomerId() == memberId) {
                    orders.add(kv.value);
                }
            } else {
                orders.add(kv.value);
            }


        }

        return orders;

    }

    public Order updateOrder(HttpServletRequest request, long id, String status) {

        Order order = Order.builder()
                .id(id)
                .itemQuantity(0)
                .price(0)
                .status(OrderStatus.CONFIRMED)
                .customerId(1)
                .sellerId(1)
                .itemId(1)
                .build();

        orderProducer.sendMessage(order);

        return order;
    }


}
