package com.ecommerce.itemservice.dto;

import lombok.Getter;

@Getter
public class ItemDTO { // 아이템의 모든 정보를 담은 객체
    ItemInfo itemInfo;
    SalesInfoDTO salesInfoDTO;
    public ItemDTO(ItemInfo itemInfo, SalesInfoDTO salesInfo) {
        this.itemInfo = itemInfo;
        this.salesInfoDTO = salesInfo;
    }
}
