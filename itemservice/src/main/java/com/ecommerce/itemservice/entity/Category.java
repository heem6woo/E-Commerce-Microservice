package com.ecommerce.itemservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "CATEGORY_TB")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 50)
    private String categoryName;

    public Category() {
    }

    // standard getters and setters
}