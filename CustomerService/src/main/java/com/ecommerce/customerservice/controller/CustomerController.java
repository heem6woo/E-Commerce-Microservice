package com.ecommerce.customerservice.controller;


import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import com.ecommerce.customerservice.vo.AuthenticateRequest;
import com.ecommerce.customerservice.vo.AuthenticateResponse;
import com.ecommerce.customerservice.vo.RegisterRequest;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticateResponse> registerCustomer(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(customerService.registerCustomer(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(customerService.authenticateCustomer(request));
    }

}
