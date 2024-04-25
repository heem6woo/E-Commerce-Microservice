package com.ecommerce.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SalesValues {

    private int salesId;
    private int sellerId;
    private int itemId;
    private int itemCount;
    private int itemPrice;
    private Byte itemStatus;

}
