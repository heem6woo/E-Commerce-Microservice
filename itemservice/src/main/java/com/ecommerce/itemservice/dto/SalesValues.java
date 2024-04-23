package com.ecommerce.itemservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalesValues {
    private int salesId;     // Assuming `salesInfoId` in the entity is a Long
    private int sellerId; // Wrapper class to match Integer type
    private int itemCount;
    private int itemPrice;
    private Byte itemStatus;  // Using Byte to match the entity

    // Updated constructor to match the entity's data types
    public SalesValues(int salesId, int sellerId, int itemCount, int itemPrice, Byte itemStatus) {
        this.salesId = salesId;
        this.sellerId = sellerId;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.itemStatus = itemStatus;
    }
}

