package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reviews/sellers")
public class SellerReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list")
    public ResponseEntity<List<Review>> listAllReviewsOfSeller(HttpServletRequest httpServletRequest) {
        String userEmail = httpServletRequest.getHeader("email");
        return ResponseEntity.ok(reviewService.findAllBySellerEmail(userEmail));
    }

    @GetMapping("/items/{item_name}")
    public ResponseEntity<List<Review>> listAllReviewOfSellerItem(HttpServletRequest httpServletRequest,
                                                                    @PathVariable(name = "item_name") String item_name)
            throws HttpResponseException {
        String userEmail = httpServletRequest.getHeader("email");

        return ResponseEntity.ok(reviewService.findAllBySellerEmailAndItemName(userEmail, item_name));
    }


}
