package com.ecommerce.memberservice.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class AuthenticateRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
