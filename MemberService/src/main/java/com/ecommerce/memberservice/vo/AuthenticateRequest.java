package com.ecommerce.memberservice.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
