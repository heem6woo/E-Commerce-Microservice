package com.ecommerce.itemservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM_TB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private int itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<SalesInfo> salesInfos = new ArrayList<>();

    @Column(name = "ITEM_NAME", nullable = false, length = 50)
    private String itemName;

    @Column(name = "ITEM_DES", nullable = false, length = 400)
    private String itemDescription;

    @Column(name = "REG_DT", nullable = false)
    private Timestamp regDt;
    @PrePersist
    protected void onCreate() {
        regDt = new Timestamp(System.currentTimeMillis());
    }
    // standard getters and setters
}