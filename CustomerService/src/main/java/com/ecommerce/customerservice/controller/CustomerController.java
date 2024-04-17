package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.dto.CustomerDto;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.entity.CustomerDetail;
import com.ecommerce.customerservice.service.CustomerService;
import com.ecommerce.customerservice.vo.CustomerDetailRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

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


    // for customer


//    @PostMapping("/customer-detail")
//    public ResponseEntity<CustomerDetail> writeCustomerDetail(@RequestBody CustomerDetailRequest request) {
//
//
//    }


}
