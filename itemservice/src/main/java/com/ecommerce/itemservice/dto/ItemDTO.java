package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
public class ItemDTO { // 아이템의 모든 정보를 담은 객체
    private ItemValues itemValues;
    private SalesValues salesValues;
    private short categoryId;

    @JsonCreator
    public ItemDTO(@JsonProperty("itemValues") ItemValues itemValues,
                   @JsonProperty("salesValues") SalesValues salesValues,
                   @JsonProperty("categoryId") short categoryId) {
        this.itemValues = itemValues;
        this.salesValues = salesValues;
        this.categoryId = categoryId;
    }
}
