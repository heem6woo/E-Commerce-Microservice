package com.ecommerce.memberservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationException extends RuntimeException{

    private HttpStatus httpStatus;

    public AuthenticationException() {
    }

    public AuthenticationException(String message, HttpStatus code) {
        super(message);
        this.httpStatus = code;
    }

    public AuthenticationException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.httpStatus = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
