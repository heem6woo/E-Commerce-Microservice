package com.ecommerce.itemservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SALES_INFO_TB")
public class SalesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALES_INFO_ID")
    private Long salesInfoId;

    @Column(name = "SELLER_ID", nullable = false)
    private Integer sellerId;

    @Column(name = "ITEM_COUNT", nullable = false)
    private Integer itemCount;

    @Column(name = "ITEM_PRICE", nullable = false)
    private Integer itemPrice;

    @Column(name = "ITEM_STATUS", nullable = false)
    private Byte itemStatus;

    // standard getters and setters
}