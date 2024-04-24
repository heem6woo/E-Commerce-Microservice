package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ItemValues { // 아이템 간이 정보
    private long itemId;
    private long category;
    private String itemName;
    private String itemDescription;
    private Timestamp req_Dt;

    @JsonCreator
    public ItemValues(@JsonProperty("itemId") long itemId,
                      @JsonProperty("category") long category,
                      @JsonProperty("itemName") String itemName,
                      @JsonProperty("itemDescription") String itemDescription,
                      @JsonProperty("req_Dt") Timestamp req_Dt) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.req_Dt = req_Dt;
    }
}
