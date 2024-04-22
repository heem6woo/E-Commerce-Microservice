package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesInfo {
    int salesId;
    int sellerId;
    int itemCount;
    int itemPrice;
    int itemStatus;
}
