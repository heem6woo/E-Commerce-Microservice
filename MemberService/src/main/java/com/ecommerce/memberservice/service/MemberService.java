package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.repo.CustomerDetailRepository;
import com.ecommerce.memberservice.repo.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationNotSupportedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final CustomerDetailRepository customerDetailRepository;

    private final JwtService jwtService;


    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    public Member findById(int id) throws ChangeSetPersister.NotFoundException {
        Optional<Member> found = memberRepository.findById(id);

        if (!found.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return found.get();
    }

    public Member findByEmail(String email) throws ChangeSetPersister.NotFoundException {
        Optional<Member> found = memberRepository.findByEmail(email);

        if (!found.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return found.get();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }


    public CustomerDetail findCustomerDetailByEmail(String email) throws ChangeSetPersister.NotFoundException {

        Member found = findByEmail(email);

        return customerDetailRepository.findByMemberId(found.getId());
    }

    public Member findByToken(HttpServletRequest request)
            throws Exception {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // index 7
            throw new Exception();
        }
        String refreshToken = authHeader.substring(7);

        String customerEmail = jwtService.extractUsername(refreshToken); // todo extract the userEmail from JWT token

        return findByEmail(customerEmail);

    }


}
