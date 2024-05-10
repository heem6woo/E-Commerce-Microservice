package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemListDTO;
import com.ecommerce.itemservice.dto.ItemValues;
import com.ecommerce.itemservice.dto.SalesValues;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {
    public ItemDTO convertToItemDto(Item item, SalesInfo salesInfo) {
        ItemValues itemValues = ItemValues.builder()
                .itemId(item.getItemId())
                .category(item.getCategory().getCategoryId())
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .req_Dt(item.getRegDt())
                .build();

        SalesValues salesValues = SalesValues.builder()
                .salesId(salesInfo.getSalesInfoId())
                .sellerId(salesInfo.getSellerId())
                .itemId(salesInfo.getItem().getItemId())
                .itemCount(salesInfo.getItemCount())
                .itemPrice(salesInfo.getItemPrice())
                .itemStatus(salesInfo.getItemStatus())
                .build();

        return ItemDTO.builder()
                .itemValues(itemValues)
                .salesValues(salesValues)
                .build();
    }

    public ItemListDTO mapToItemListDTO(Item item) {
        ItemValues itemValues = ItemValues.builder()
                .itemId(item.getItemId())
                .category(item.getCategory().getCategoryId())
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .req_Dt(item.getRegDt())
                .build();

        List<SalesValues> salesValuesList = item.getSalesInfos().stream()
                .map(this::mapToSalesValues)
                .collect(Collectors.toList());

        return ItemListDTO.builder()
                .itemValues(itemValues)
                .salesValuesList(salesValuesList)
                .build();
    }

    public SalesValues mapToSalesValues(SalesInfo salesInfo) {
        return SalesValues.builder()
                .salesId(salesInfo.getSalesInfoId())
                .sellerId(salesInfo.getSellerId())
                .itemId(salesInfo.getItem().getItemId())
                .itemCount(salesInfo.getItemCount())
                .itemPrice(salesInfo.getItemPrice())
                .itemStatus(salesInfo.getItemStatus())
                .build();
    }
}
