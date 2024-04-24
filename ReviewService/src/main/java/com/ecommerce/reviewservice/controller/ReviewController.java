package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.dto.CustomerDto;
import com.ecommerce.reviewservice.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;


    // for all
    @GetMapping("/items/{item-name}")
    public ResponseEntity<List<Review>> listAllReviewsByItem(@PathVariable(name = "item-name") String itemName) {
        //해당 상품의 모든 리뷰 쿼리 findAllByItemId
        return ResponseEntity.ok(reviewService.findAllByItemName(itemName));
    }


    // only for admin??
    @GetMapping("/customers/{email}")
    public ResponseEntity<List<Review>> listAllReviewsOfCustomer(@PathVariable(name = "email")String userEmail) {
//         해당 멤버의 모든 리뷰 쿼리 findAllByCustomerId
        return ResponseEntity.ok(reviewService.findAllByCustomerEmail(userEmail));
    }


    // for customer
    @GetMapping("/list")
    public ResponseEntity<List<Review>> listAllReviewsOfCustomer(HttpServletRequest httpServletRequest) {
//         멤버 서비스에 rest로 멤버 아이디 요청
        log.info(httpServletRequest.getHeader("Authorization"));
        log.info(httpServletRequest.getHeader("email"));
        String userEmail = httpServletRequest.getHeader("email");
//         해당 멤버의 모든 리뷰 쿼리 findAllByCustomerId
        return ResponseEntity.ok(reviewService.findAllByCustomerEmail(userEmail));
    }


    // for customer
    @PostMapping("/item/{item_name}")
    public ResponseEntity<String> writeReview(HttpServletRequest httpServletRequest,
                                              @RequestBody ReviewDto reviewRequest,
                                              @PathVariable(name = "item_name") String item_name) {

        String userEmail = httpServletRequest.getHeader("email");

        return ResponseEntity.ok(reviewService.saveReview(userEmail, item_name,  reviewRequest));
    }


}
