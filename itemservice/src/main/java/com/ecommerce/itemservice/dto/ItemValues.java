package com.ecommerce.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemValues { // 아이템 간이 정보
    private long itemId;
    private short category;
    private String itemName;
    private String itemDescription;
    private Timestamp req_Dt;
}

