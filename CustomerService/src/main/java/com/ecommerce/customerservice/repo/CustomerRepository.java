package com.ecommerce.customerservice.repo;

import com.ecommerce.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // To find Customer using email
    // email is used as user name for user detail
    Optional<Customer>findByEmail(String email);
}
