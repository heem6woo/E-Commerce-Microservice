package com.ecommerce.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private String itemName;

    private String sellerName;

    private int price;

    private int quantity;

}
