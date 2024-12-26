package com.example.demoex.services;

import com.example.demoex.entities.Review;
import com.example.demoex.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void create(Review review) {
        reviewRepository.save(review);
    }
}
