package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
public class SalesValues {
    private int salesId;
    private int sellerId;
    private int itemCount;
    private int itemPrice;
    private Byte itemStatus;

    @JsonCreator
    public SalesValues(@JsonProperty("salesId") int salesId,
                       @JsonProperty("sellerId") int sellerId,
                       @JsonProperty("itemCount") int itemCount,
                       @JsonProperty("itemPrice") int itemPrice,
                       @JsonProperty("itemStatus") Byte itemStatus) {
        this.salesId = salesId;
        this.sellerId = sellerId;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.itemStatus = itemStatus;
    }
}
