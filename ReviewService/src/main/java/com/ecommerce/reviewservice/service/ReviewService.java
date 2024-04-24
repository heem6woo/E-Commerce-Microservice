package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.dto.ReviewDto;
import com.ecommerce.reviewservice.entity.Review;
import com.ecommerce.reviewservice.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAllByCustomerId(int customerId) {
        return reviewRepository.findAllByCustomerId(customerId);
    }

    public List<Review> findAllByItemId(int customerId) {
        return reviewRepository.findAllByItemId(customerId);
    }

}
