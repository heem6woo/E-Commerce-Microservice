package com.ecommerce.orderservice.entity;

import com.ecommerce.orderservice.dto.OrderStatus;
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
    private Long customerId;
    private Long productId;
    private int productCount;
    private int price;
    private OrderStatus status;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productCount=" + productCount +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}