package com.ecommerce.itemservice.kafka.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {

    private Long id;
    private OrderStatus status;
}
