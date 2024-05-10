package com.ecommerce.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemListDTO { // 아이템의 모든 정보를 담은 객체
    private ItemValues itemValues;
    private List<SalesValues> salesValuesList = new ArrayList<>();
}
