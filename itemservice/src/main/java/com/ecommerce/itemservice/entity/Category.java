package com.ecommerce.itemservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATEGORY_TB")
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private short categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 50)
    private String categoryName;
    public Category() {
    }
    public Category(short categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // standard getters and setters
}