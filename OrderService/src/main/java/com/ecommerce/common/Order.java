package com.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private int customerId;
    private int sellerId;
    private int itemId;
    private int itemQuantity;
    private int price;
    private OrderStatus status;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", sellerId=" + sellerId +
                ", itemId=" + itemId +
                ", itemQuantity=" + itemQuantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}