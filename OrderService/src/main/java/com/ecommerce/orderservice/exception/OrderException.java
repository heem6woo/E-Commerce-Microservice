package com.ecommerce.orderservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
public class OrderException extends RuntimeException{

    private HttpStatus httpStatus;

    public OrderException() {
    }

    public OrderException(String message, HttpStatus code) {
        super(message);
        this.httpStatus = code;
    }

    public OrderException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.httpStatus = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
