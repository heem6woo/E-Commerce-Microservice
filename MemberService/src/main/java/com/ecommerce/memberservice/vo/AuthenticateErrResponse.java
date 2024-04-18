package com.ecommerce.memberservice.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class AuthenticateErrResponse {

    int status;
    String msg;
    long timeStamp;
}
