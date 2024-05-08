package com.ecommerce.itemservice.kafka.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private int customerId;
    private int sellerId;
    private int itemId;
    private int itemQuantity;
    private int price;
    private OrderStatus status;
}
