package com.ecommerce.customerservice.repo;

import com.ecommerce.customerservice.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer> {

}
