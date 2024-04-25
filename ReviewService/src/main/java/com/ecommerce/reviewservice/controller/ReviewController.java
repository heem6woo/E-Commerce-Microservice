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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    // for all
    @GetMapping("/")
    public ResponseEntity<List<Review>> listAllReviews(@RequestParam(name = "item-name", required = false) String itemName,
                                                             @RequestParam(name = "customer-email", required = false) String email,
                                                             @RequestParam(name = "score", required = false, defaultValue = "-1") int score) {

        if(itemName != null && email != null && score != -1) {
            return ResponseEntity.ok(reviewService.findAllByItemNameEmailScore(itemName,email,score));
        }
        if(itemName != null && email != null) {
            return ResponseEntity.ok(reviewService.findAllByItemNameEmail(itemName,email));
        }
        if(itemName != null && score != -1) {
            return ResponseEntity.ok(reviewService.findAllByItemNameScore(itemName,score));
        }
        if(email != null && score != -1) {
            log.info("Score and email");
            return ResponseEntity.ok(reviewService.findAllByEmailScore(email,score));
        }
        if(itemName != null) {
            return ResponseEntity.ok(reviewService.findAllByItemName(itemName));
        }
        if(email != null) {
            return ResponseEntity.ok(reviewService.findAllByEmail(email));
        }
        if(score != -1) {
            return ResponseEntity.ok(reviewService.findAllByScore(score));
        }

        //해당 상품의 모든 리뷰 쿼리 findAllByItemId
        return ResponseEntity.ok(null);
    }


}
