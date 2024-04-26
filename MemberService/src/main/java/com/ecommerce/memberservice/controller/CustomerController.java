package com.ecommerce.memberservice.controller;

import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.service.CustomerDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerDetailService customerDetailService;


    @PostMapping("/customer-detail")
    public ResponseEntity<String> createCustomerDetail(
            HttpServletRequest request,
            @RequestBody CustomerDetail customerDetail)
            throws Exception {

        return ResponseEntity.ok(customerDetailService.saveCustomerDetailByToken(request, customerDetail));

    }

    @PatchMapping("/customer-detail")
    public ResponseEntity<String> updateCustomerDetail(
            HttpServletRequest request,
            @RequestBody CustomerDetail customerDetail)
            throws Exception {

        return ResponseEntity.ok(customerDetailService.updateCustomerDetailByToken(request, customerDetail));

    }

    @GetMapping("/customer-detail")
    public ResponseEntity<CustomerDetail> readCustomerDetail(
            HttpServletRequest request)
            throws Exception {

        return ResponseEntity.ok(customerDetailService.getCustomerDetailByToken(request));

    }


}
