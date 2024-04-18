package com.ecommerce.memberservice.repo;

import com.ecommerce.memberservice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // To find Customer using email
    // email is used as user name for user detail
    Optional<Member> findByEmail(String email);

}
