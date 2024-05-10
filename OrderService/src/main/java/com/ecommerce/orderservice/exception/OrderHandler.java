package com.ecommerce.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderHandler {

    @ExceptionHandler
    public ResponseEntity<OrderErrResponse> exceptionHandler(Exception ex) {

        OrderErrResponse eer = OrderErrResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();


        return new ResponseEntity<>(eer, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<OrderErrResponse> customerExceptionHandler(OrderException ex) {

        OrderErrResponse eer = OrderErrResponse.builder()
                .status(ex.getHttpStatus().value())
                .msg(ex.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();


        return new ResponseEntity<>(eer, ex.getHttpStatus());

    }
}
