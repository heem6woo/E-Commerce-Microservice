package com.ecommerce.memberservice.vo;

import lombok.Data;

@Data
public class SellerDetailRequest {

    private long licence;

    private String address;

    private String domain;

    private int age;

}
