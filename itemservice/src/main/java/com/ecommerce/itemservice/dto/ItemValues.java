package com.ecommerce.itemservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder

public class ItemValues {// 아이템 간이 정보
    long itemId;
    long category;
    String itemName;
    String itemDescription;
    Timestamp req_Dt;

    public ItemValues(long itemId, long category, String itemName, String itemDescription, Timestamp req_Dt) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.req_Dt = req_Dt;
    }
}
