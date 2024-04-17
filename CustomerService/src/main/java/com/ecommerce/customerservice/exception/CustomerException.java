package com.ecommerce.customerservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerException extends RuntimeException{

    private HttpStatus httpStatus;

    public CustomerException() {
    }

    public CustomerException(String message, HttpStatus code) {
        super(message);
        this.httpStatus = code;
    }

    public CustomerException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.httpStatus = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
