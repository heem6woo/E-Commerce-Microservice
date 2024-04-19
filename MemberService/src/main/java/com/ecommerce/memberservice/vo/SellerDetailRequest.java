package com.ecommerce.memberservice.vo;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SellerDetailRequest {

    private long licence;

    private String address;

    private String domain;

    private int age;

}
