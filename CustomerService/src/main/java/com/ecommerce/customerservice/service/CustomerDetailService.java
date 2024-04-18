package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.entity.CustomerDetail;
import com.ecommerce.customerservice.repo.CustomerDetailRepository;
import com.ecommerce.customerservice.repo.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationNotSupportedException;

@Service
@RequiredArgsConstructor
public class CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;

    private final CustomerService customerRepository;

    @Transactional
    public String saveCustomerDetailByToken(HttpServletRequest request, CustomerDetail customerDetail)
            throws AuthenticationNotSupportedException, ChangeSetPersister.NotFoundException {

        Customer customer = customerRepository.findByToken(request);

        customer.setCustomerDetail(customerDetail);

        customerDetailRepository.save(customerDetail);

        return "Customer Detail Successfully Created";

    }

    @Transactional
    public String updateCustomerDetailByToken(HttpServletRequest request, CustomerDetail customerDetail)
            throws AuthenticationNotSupportedException, ChangeSetPersister.NotFoundException {

        Customer customer = customerRepository.findByToken(request);

        customerDetailRepository.save(customerDetail);

        return "Customer Detail Successfully Updated";

    }
}
