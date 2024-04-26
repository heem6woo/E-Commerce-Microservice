package com.ecommerce.memberservice.repository;

import com.ecommerce.memberservice.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer> {

    @Query("SELECT cd FROM CustomerDetail cd WHERE cd.member.id = :memberId")
    CustomerDetail findByMemberId (@Param("memberId") int id);
}
