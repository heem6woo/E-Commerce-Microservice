package com.ecommerce.memberservice.repository;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // To find Customer using email
    // email is used as user name for user detail
    Optional<Member> findByEmail(String email);


    Member findByName(String name);

    Member findByNameAndRole(String sellerName, Role role);
}
