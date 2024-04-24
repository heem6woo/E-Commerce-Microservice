package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.dto.CustomerDto;
import com.ecommerce.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final MemberFeign memberFeign;
    private final ItemFeign itemFeign;

    @Autowired
    private final ReviewService reviewService;


    @GetMapping("/items/{item-name}")
    public ResponseEntity<List<Review>> listAllReviewsByItem(@PathVariable(name = "item-name") String itemName) {
        //아이템 서비스에 rest로 상품 아이디 요청
        int itemId = (int) itemFeign.retrieveItemId(itemName);

        //해당 상품의 모든 리뷰 쿼리 findAllByItemId
        return ResponseEntity.ok(reviewService.findAllByItemId(itemId));
    }


    // only for admin??
    @GetMapping("/customers/{email}")
    public ResponseEntity<List<Review>> listAllReviewsOfCustomer(@PathVariable(name = "email") String userEmail) {
//         멤버 서비스에 rest로 멤버 아이디 요청
        int customerId = memberFeign.retrieveCustomerId(userEmail).getId();
//         해당 멤버의 모든 리뷰 쿼리 findAllByCustomerId
        return ResponseEntity.ok(reviewService.findAllByCustomerId(customerId));
    }


}
