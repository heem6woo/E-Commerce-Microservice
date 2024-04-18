package com.ecommerce.memberservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private String name;

    private String email;
}
