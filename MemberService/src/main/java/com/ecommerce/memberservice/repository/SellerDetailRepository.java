package com.ecommerce.memberservice.repository;

import com.ecommerce.memberservice.entity.SellerDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SellerDetailRepository extends JpaRepository<SellerDetail, Integer> {

    SellerDetail findByMemberId(int id);

}
