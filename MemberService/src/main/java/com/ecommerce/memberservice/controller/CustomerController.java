package com.ecommerce.memberservice.controller;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.service.CustomerDetailService;
import com.ecommerce.memberservice.service.MemberService;
import com.ecommerce.memberservice.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationNotSupportedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
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


}
