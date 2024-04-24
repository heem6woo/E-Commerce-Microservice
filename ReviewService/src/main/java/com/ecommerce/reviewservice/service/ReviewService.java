package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.controller.ItemFeign;
import com.ecommerce.reviewservice.controller.MemberFeign;
import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberFeign memberFeign;
    private final ItemFeign itemFeign;

    @Autowired
    private final ReviewService reviewService;

    private final ReviewRepository reviewRepository;

    public List<Review> findAllByCustomerEmail(String email) {

        int customerId = memberFeign.retrieveCustomerId(email).getId();

        return reviewRepository.findAllByCustomerId(customerId);
    }

    public List<Review> findAllByItemName(String itemName) {
        //아이템 서비스에 rest로 상품 아이디 요청
        int itemId = (int) itemFeign.retrieveItemId(itemName);
        //
        return reviewRepository.findAllByItemId(itemId);
    }

    public String saveReview(String email, String itemName, ReviewDto reviewRequest) {
        // 추 후 상품 구매목록을 확인하여 해당 고객이 상품을 구매했는지 확인

        int customerId = memberFeign.retrieveCustomerId(email).getId();

        //int itemId = itemFeign.retrieveItemId(itemName).getId();

        Review review = Review.builder()
                .customerId(customerId)
                //.itemId(itemId)
                .contains(reviewRequest.getContains())
                .date(reviewRequest.getDate())
                .score(reviewRequest.getScore())
                .build();

        reviewRepository.save(review);
        return "Successfully your review stored.";
    }
}
