package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.repository.CustomerDetailRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;

    private final MemberService memberService;

    @Transactional
    public String saveCustomerDetailByToken(HttpServletRequest request, CustomerDetail customerDetail)
            throws Exception {

        Member member = memberService.findByToken(request);

        customerDetail.setMember(member);

        customerDetailRepository.save(customerDetail);

        return "Customer Detail Successfully Created";

    }

    @Transactional
    public String updateCustomerDetailByToken(HttpServletRequest request, CustomerDetail customerDetail)
            throws Exception {

        Member member = memberService.findByToken(request);

        customerDetailRepository.save(customerDetail);

        return "Customer Detail Successfully Updated";

    }

    public CustomerDetail getCustomerDetailByToken(HttpServletRequest request)
            throws Exception {

        Member member = memberService.findByToken(request);

        return customerDetailRepository.findByMemberId(member.getId());

    }
}
