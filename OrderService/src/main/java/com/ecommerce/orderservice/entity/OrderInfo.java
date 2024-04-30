package com.ecommerce.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "seller_id")
    private int sellerId;


    @Column(name = "date")
    private Timestamp date;

    @PrePersist
    protected void onCreate() {
        date = new Timestamp(System.currentTimeMillis());
    }

}
