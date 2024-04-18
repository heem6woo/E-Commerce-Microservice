package com.ecommerce.memberservice.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class AuthenticateRequest {

    private String email;

    private String password;
}
