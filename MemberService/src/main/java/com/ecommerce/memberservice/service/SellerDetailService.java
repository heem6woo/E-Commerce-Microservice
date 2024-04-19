package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.entity.SellerDetail;
import com.ecommerce.memberservice.repo.SellerDetailRepository;
import com.ecommerce.memberservice.vo.SellerDetailRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerDetailService {
    private final SellerDetailRepository sellerDetailRepository;

    private final MemberService memberService;

    @Transactional
    public String saveSellerDetailByToken(HttpServletRequest request, SellerDetail sellerDetail)
            throws Exception {

        Member member = memberService.findByToken(request);

        sellerDetail.setMember(member);

        sellerDetailRepository.save(sellerDetail);

        return "Seller Detail Successfully Created";

    }

    @Transactional
    public String updateSellerDetailByToken(HttpServletRequest request, SellerDetail sellerDetail)
            throws Exception {

        Member member = memberService.findByToken(request);

        sellerDetailRepository.save(sellerDetail);

        return "Seller Detail Successfully Updated";

    }


}
