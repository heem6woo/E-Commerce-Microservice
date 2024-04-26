package com.ecommerce.memberservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateErrResponse {

    int status;
    String msg;
    long timeStamp;
}
