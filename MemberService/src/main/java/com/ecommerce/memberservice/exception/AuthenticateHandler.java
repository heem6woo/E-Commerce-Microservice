package com.ecommerce.memberservice.exception;

import com.ecommerce.memberservice.vo.AuthenticateErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticateHandler {

    @ExceptionHandler
    public ResponseEntity<AuthenticateErrResponse> exceptionHandler(Exception ex) {

        AuthenticateErrResponse eer = AuthenticateErrResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();


        return new ResponseEntity<>(eer, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<AuthenticateErrResponse> customerExceptionHandler(CustomerException ex) {

        AuthenticateErrResponse eer = AuthenticateErrResponse.builder()
                .status(ex.getHttpStatus().value())
                .msg(ex.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();


        return new ResponseEntity<>(eer, ex.getHttpStatus());

    }
}
