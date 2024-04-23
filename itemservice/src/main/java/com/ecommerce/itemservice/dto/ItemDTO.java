package com.ecommerce.itemservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDTO { // 아이템의 모든 정보를 담은 객체
    ItemValues itemValues;
    SalesValues salesValues;
    short categoryId;

    public ItemDTO(ItemValues itemValues, SalesValues salesValues, short categoryId) {
        this.itemValues = itemValues;
        this.salesValues = salesValues;
        this.categoryId = categoryId;
    }
}
