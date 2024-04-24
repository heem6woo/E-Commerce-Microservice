package com.ecommerce.reviewservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "contains")
    private String contains;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "score")
    private int score;

    // referenced from ItemService...entity.Item written by ooANAoo
    @PrePersist
    protected void onCreate() {
        date = new Timestamp(System.currentTimeMillis());
    }


}
