package com.ecommerce.orderservice.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderErrResponse {

    int status;
    String msg;
    long timeStamp;
}
