package com.ecommerce.itemservice.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "ITEM_TB")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SALES_INFO_ID", nullable = false)
    private SalesInfo salesInfo;

    @Column(name = "ITEM_NAME", nullable = false, length = 50)
    private String itemName;

    @Column(name = "ITEM_DES", nullable = false, length = 400)
    private String itemDescription;

    @Column(name = "REG_DT", nullable = false)
    private Timestamp regDt;

    // standard getters and setters
}