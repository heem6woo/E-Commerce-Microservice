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
public class ItemDTO { // 아이템의 모든 정보를 담은 객체
    private ItemValues itemValues;
    private SalesValues salesValues;
}
