package com.ecommerce.customerservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private String name;

    private String email;
}
