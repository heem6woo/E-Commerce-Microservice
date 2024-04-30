package com.ecommerce.memberservice.service;

import com.ecommerce.grpc.IdServiceGrpc;
import com.ecommerce.memberservice.dto.CustomerDto;
import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.SellerDetail;
import com.ecommerce.memberservice.repository.CustomerDetailRepository;
import com.ecommerce.memberservice.repository.MemberRepository;
import com.ecommerce.memberservice.repository.SellerDetailRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final CustomerDetailRepository customerDetailRepository;

    private final SellerDetailRepository sellerDetailRepository;

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


    public CustomerDetail findCustomerDetailByEmail(String email)
            throws ChangeSetPersister.NotFoundException {

        Member found = findByEmail(email);

        return customerDetailRepository.findByMemberId(found.getId());
    }

    public SellerDetail findSellerDetailByEmail(String email)
            throws ChangeSetPersister.NotFoundException {

        Member found = findByEmail(email);
        return sellerDetailRepository.findByMemberId(found.getId());
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

    public CustomerDto findCustomerIdByEmail(String email) throws ChangeSetPersister.NotFoundException {
        return CustomerDto.builder()
                        .id(findByEmail(email).getId())
                        .build();
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }


//    public Member findBySellerName(String sellerName) {
//        // Seller Name should be unique
//        return memberRepository.findByName(sellerName);
//    }
}
