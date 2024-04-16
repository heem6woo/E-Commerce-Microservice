package com.ecommerce.customerservice.controller;


import com.ecommerce.customerservice.service.AuthenticationService;
import com.ecommerce.customerservice.vo.AuthenticateRequest;
import com.ecommerce.customerservice.vo.AuthenticateResponse;
import com.ecommerce.customerservice.vo.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticateResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.registerCustomer(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(authenticationService.authenticateCustomer(request));
    }

    @PostMapping("/access-token")
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.reissueAccessToken(request, response);

    }

}
