package com.ecommerce.memberservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private int id;
    private String email;

}
