package com.ecommerce.itemservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemInfo {
    int itemId;
    short category;
    String itemName;
}
