package com.ecommerce.memberservice.controller;

import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.entity.SellerDetail;
import com.ecommerce.memberservice.service.CustomerDetailService;
import com.ecommerce.memberservice.service.SellerDetailService;
import com.ecommerce.memberservice.vo.SellerDetailRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController {
    private final SellerDetailService sellerDetailService;


    @PostMapping("/seller-detail")
    public ResponseEntity<String> createSellerDetail(
            HttpServletRequest request,
            @RequestBody SellerDetail sellerDetail)
            throws Exception {

        return ResponseEntity.ok(sellerDetailService.saveSellerDetailByToken(request, sellerDetail));

    }

    @PatchMapping("/seller-detail")
    public ResponseEntity<String> updateSellerDetail(
            HttpServletRequest request,
            @RequestBody SellerDetail sellerDetail)
            throws Exception {

        return ResponseEntity.ok(sellerDetailService.updateSellerDetailByToken(request, sellerDetail));

    }
}

