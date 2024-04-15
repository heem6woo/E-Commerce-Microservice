package com.ecommerce.customerservice.vo;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticateResponse {


    private String accessToken;

    private String refreshToken;


}
