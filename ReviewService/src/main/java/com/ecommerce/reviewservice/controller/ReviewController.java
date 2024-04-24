package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.dto.CustomerDto;
import com.ecommerce.reviewservice.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

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
        String userEmail = httpServletRequest.getHeader("email");
        return ResponseEntity.ok(reviewService.findAllByCustomerEmail(userEmail));
    }


    // for customer
    @PostMapping("/items/{item_name}")
    public ResponseEntity<String> writeReview(HttpServletRequest httpServletRequest,
                                              @RequestBody ReviewDto reviewRequest,
                                              @PathVariable(name = "item_name") String item_name)
            throws HttpResponseException {
        String userEmail = httpServletRequest.getHeader("email");
        return ResponseEntity.ok(reviewService.saveReview(userEmail, item_name,  reviewRequest));
    }

    // for customer
    @DeleteMapping("/items/{item_name}")
    public ResponseEntity<String> deleteReview(HttpServletRequest httpServletRequest,
                                              @PathVariable(name = "item_name") String item_name) {

        String userEmail = httpServletRequest.getHeader("email");

        return ResponseEntity.ok(reviewService.deleteReview(userEmail, item_name));
    }

    @PatchMapping("/items/{item_name}")
    public ResponseEntity<String> updateReview(HttpServletRequest httpServletRequest,
                                               @RequestBody ReviewDto reviewRequest,
                                               @PathVariable(name = "item_name") String item_name) {

        String userEmail = httpServletRequest.getHeader("email");

        return ResponseEntity.ok(reviewService.updateReview(userEmail, item_name,  reviewRequest));
    }



}
