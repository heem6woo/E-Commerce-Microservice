package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.controller.ItemFeign;
import com.ecommerce.reviewservice.controller.MemberFeign;
import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.grpclient.ItemIdClient;
import com.ecommerce.reviewservice.grpclient.CustomerIdClient;
import com.ecommerce.reviewservice.grpclient.SellerIdClient;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberFeign memberFeign;
    private final ItemFeign itemFeign;

    // gRPC
    private final CustomerIdClient customerIdClient;
    private final SellerIdClient sellerIdClient;
    private final ItemIdClient itemIdClient;


    private final ReviewRepository reviewRepository;

    public List<Review> findAllByCustomerEmail(String email) {

        int customerId = memberFeign.retrieveCustomerId(email).getId();

        return reviewRepository.findAllByCustomerId(customerId);
    }

    public List<Review> findAllByItemName(String itemName) {
        //아이템 서비스에 rest로 상품 아이디 요청
        //int itemId = (int) itemFeign.retrieveItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        //
        return reviewRepository.findAllByItemId(itemId);
    }

    @Transactional
    public String saveReview(String email, String itemName, ReviewDto reviewRequest)
            throws HttpResponseException {
        // 추 후 상품 구매목록을 확인하여 해당 고객이 상품을 구매했는지 확인

        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);

        int sellerId = sellerIdClient.requestMemberId(reviewRequest.getSellerName());
        //int itemId = itemFeign.retrieveItemId(itemName).getId();
        //int itemId = 0;
        int itemId = itemIdClient.requestItemId(itemName);

        // duplicate check
        if(reviewRepository.findByCustomerIdAndItemId(customerId, itemId) != null) {
            throw new HttpResponseException(HttpStatus.SC_NOT_ACCEPTABLE, "Review already stored.");
        };

        Review review = Review.builder()
                .customerId(customerId)
                .sellerId(sellerId)
                .itemId(itemId)
                .contains(reviewRequest.getContains())
                .date(reviewRequest.getDate())
                .score(reviewRequest.getScore())
                .build();

        reviewRepository.save(review);
        return "Successfully your review stored.";
    }

    @Transactional
    public String deleteReview(String userEmail, String itemName, String sellerName) {

        //int customerId = memberFeign.retrieveCustomerId(userEmail).getId();
        int customerId = customerIdClient.requestMemberId(userEmail);
        int sellerId = sellerIdClient.requestMemberId(sellerName);
        int itemId = itemIdClient.requestItemId(itemName);

        // int itemId = itemFeign.retrieveItemId(itemName).getId();
        Review review = reviewRepository.findByCustomerIdAndSellerIdAndItemId(customerId, sellerId, itemId);

        reviewRepository.delete(review);

        return "Successfully your review for " + itemName + " deleted";
    }

    @Transactional
    public String updateReview(String email, String itemName, ReviewDto reviewRequest) {
        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);

        int itemId = itemIdClient.requestItemId(itemName);

        int sellerId = sellerIdClient.requestMemberId(reviewRequest.getSellerName());

        //int itemId = itemFeign.retrieveItemId(itemName).getId();

        Review review = reviewRepository.findByCustomerIdAndSellerIdAndItemId(customerId,sellerId, itemId);

        if (reviewRequest.getContains() != null) {
            review.setContains(reviewRequest.getContains());
        }

        review.setScore(reviewRequest.getScore());

        review.setDate(new Timestamp(System.currentTimeMillis()));

        reviewRepository.save(review);
        return "Successfully your review updated.";
    }

    public List<Review> findAllByItemNameEmailScore(String itemName, String email, int score) {
        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        return reviewRepository.findAllByItemIdAndCustomerIdAndScore(itemId,customerId,score);
    }

    public List<Review> findAllByItemNameEmail(String itemName, String email) {
        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        return reviewRepository.findAllByItemIdAndCustomerId(itemId, customerId);
    }

    public List<Review> findAllByItemNameScore(String itemName, int score) {
        //int itemId = itemFeign.retrieveItemId(itemName);
        int itemId = itemIdClient.requestItemId(itemName);
        return reviewRepository.findAllByItemIdAndScore(itemId, score);
    }

    public List<Review> findAllByEmailScore(String email, int score) {
        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);
        return reviewRepository.findAllByCustomerIdAndScore(customerId,score);
    }

    public List<Review> findAllByEmail(String email) {
        //int customerId = memberFeign.retrieveCustomerId(email).getId();
        int customerId = customerIdClient.requestMemberId(email);
        return reviewRepository.findAllByCustomerId(customerId);
    }

    public List<Review> findAllByScore(int score) {
        return reviewRepository.findAllByScore(score);
    }
}
