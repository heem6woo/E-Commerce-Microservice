package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.dto.CustomerDto;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.entity.CustomerDetail;
import com.ecommerce.customerservice.service.CustomerDetailService;
import com.ecommerce.customerservice.service.CustomerService;
import com.ecommerce.customerservice.service.JwtService;
import com.ecommerce.customerservice.vo.CustomerDetailRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationNotSupportedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDetailService customerDetailService;
    private final JwtService jwtService;

    // Only for Admin
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable int id)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/customer-detail/{email}")
    public ResponseEntity<CustomerDetail> findCustomerDetailByEmail(@PathVariable String email)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(customerService.findCustomerDetailByEmail(email));
    }




    @PostMapping("/customer-detail")
    public ResponseEntity<String> createCustomerDetail(
            HttpServletRequest request,
            @RequestBody CustomerDetail customerDetail)
            throws AuthenticationNotSupportedException, ChangeSetPersister.NotFoundException {

        return ResponseEntity.ok(customerDetailService.saveCustomerDetailByToken(request, customerDetail));

    }

    @PostMapping("/customer-detail")
    public ResponseEntity<String> updateCustomerDetail(
            HttpServletRequest request,
            @RequestBody CustomerDetail customerDetail)
            throws AuthenticationNotSupportedException, ChangeSetPersister.NotFoundException {

        return ResponseEntity.ok(customerDetailService.updateCustomerDetailByToken(request, customerDetail));

    }


}
