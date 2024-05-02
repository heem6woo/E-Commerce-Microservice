package com.ecommerce.reviewservice.repository;


import com.ecommerce.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByCustomerId(int customerId);
    List<Review> findAllByItemId(int itemId);

    Review findByCustomerIdAndItemId(int customerId, int itemId);

    List<Review> findAllByItemIdAndCustomerIdAndScore(int itemId, int customerId, int score);

    List<Review> findAllByItemIdAndCustomerId(int itemId, int customerId);

    List<Review> findAllByItemIdAndScore(int itemId, int score);

    List<Review> findAllByScore(int score);

    List<Review> findAllByCustomerIdAndScore(int customerId, int score);

    Review findByCustomerIdAndSellerIdAndItemId(int customerId, int sellerId, int itemId);
}

