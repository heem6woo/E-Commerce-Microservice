package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.entity.CustomerDetail;
import com.ecommerce.customerservice.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Customer findById(int id) throws ChangeSetPersister.NotFoundException {
        Optional<Customer> found = customerRepository.findById(id);

        if (!found.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return found.get();
    }

    public Customer findByEmail(String email) throws ChangeSetPersister.NotFoundException {
        Optional<Customer> found = customerRepository.findByEmail(email);

        if (!found.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return found.get();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


    public CustomerDetail findCustomerDetailByEmail(String email) throws ChangeSetPersister.NotFoundException {

        Customer found = findByEmail(email);

        return found.getCustomerDetail();
    }
}
