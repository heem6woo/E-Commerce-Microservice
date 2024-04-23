package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ItemInfo {// 아이템 간이 정보
    long itemId;
    long category;
    String itemName;
    Timestamp req_Dt;

    public ItemInfo(long itemId, long category, String itemName, Timestamp req_Dt) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.req_Dt = req_Dt;
    }
}
