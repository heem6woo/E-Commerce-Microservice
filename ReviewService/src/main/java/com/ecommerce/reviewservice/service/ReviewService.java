package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.controller.ItemFeign;
import com.ecommerce.reviewservice.controller.MemberFeign;
import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberFeign memberFeign;
    private final ItemFeign itemFeign;


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

    @Transactional
    public String saveReview(String email, String itemName, ReviewDto reviewRequest)
            throws HttpResponseException {
        // 추 후 상품 구매목록을 확인하여 해당 고객이 상품을 구매했는지 확인

        int customerId = memberFeign.retrieveCustomerId(email).getId();

        //int itemId = itemFeign.retrieveItemId(itemName).getId();

        // duplicate check
        if(reviewRepository.findByCustomerIdAndItemId(customerId, 0) != null) {
            throw new HttpResponseException(HttpStatus.SC_NOT_ACCEPTABLE, "Review already stored.");
        };

        Review review = Review.builder()
                .customerId(customerId)
                //.itemId(itemId)
                .itemId(0)
                .contains(reviewRequest.getContains())
                .date(reviewRequest.getDate())
                .score(reviewRequest.getScore())
                .build();

        reviewRepository.save(review);
        return "Successfully your review stored.";
    }

    @Transactional
    public String deleteReview(String userEmail, String itemName) {

        int customerId = memberFeign.retrieveCustomerId(userEmail).getId();

        int itemId = 0;

        // int itemId = itemFeign.retrieveItemId(itemName).getId();
        Review review = reviewRepository.findByCustomerIdAndItemId(customerId, itemId);

        reviewRepository.delete(review);

        return "Successfully your review for " + itemName + " deleted";
    }

    @Transactional
    public String updateReview(String email, String itemName, ReviewDto reviewRequest) {
        int customerId = memberFeign.retrieveCustomerId(email).getId();

        int itemId = 0;

        //int itemId = itemFeign.retrieveItemId(itemName).getId();

        Review review = reviewRepository.findByCustomerIdAndItemId(customerId,itemId);

        if (reviewRequest.getContains() != null) {
            review.setContains(reviewRequest.getContains());
        }

        review.setScore(reviewRequest.getScore());

        review.setDate(new Timestamp(System.currentTimeMillis()));

        reviewRepository.save(review);
        return "Successfully your review updated.";
    }

    public List<Review> findAllByItemNameEmailScore(String itemName, String email, int score) {
        int customerId = memberFeign.retrieveCustomerId(email).getId();
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = 1;
        return reviewRepository.findAllByItemIdAndCustomerIdAndScore(itemId,customerId,score);
    }

    public List<Review> findAllByItemNameEmail(String itemName, String email) {
        int customerId = memberFeign.retrieveCustomerId(email).getId();
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = 1;
        return reviewRepository.findAllByItemIdAndCustomerId(itemId, customerId);
    }

    public List<Review> findAllByItemNameScore(String itemName, int score) {
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = 1;
        return reviewRepository.findAllByItemIdAndScore(itemId, score);
    }

    public List<Review> findAllByEmailScore(String email, int score) {
        int customerId = memberFeign.retrieveCustomerId(email).getId();
        return reviewRepository.findAllByCustomerIdAndScore(customerId,score);
    }

    public List<Review> findAllByEmail(String email) {
        int customerId = memberFeign.retrieveCustomerId(email).getId();
        return reviewRepository.findAllByCustomerId(customerId);
    }

    public List<Review> findAllByScore(int score) {
        return reviewRepository.findAllByScore(score);
    }
}