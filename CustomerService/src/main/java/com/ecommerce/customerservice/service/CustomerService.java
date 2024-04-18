package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.entity.CustomerDetail;
import com.ecommerce.customerservice.repo.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationNotSupportedException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final JwtService jwtService;


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

    public Customer findByToken(HttpServletRequest request)
            throws ChangeSetPersister.NotFoundException, AuthenticationNotSupportedException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            throw new AuthenticationNotSupportedException();
        }
        String refreshToken = authHeader.substring(7);

        String customerEmail = jwtService.extractUsername(refreshToken); // todo extract the userEmail from JWT token

        return findByEmail(customerEmail);

    }



}
