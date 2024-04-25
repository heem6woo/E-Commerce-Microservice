package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews/sellers")
public class SellerReviewController {

    private final ReviewService reviewService;




}
