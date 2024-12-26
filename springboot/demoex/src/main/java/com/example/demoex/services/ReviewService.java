package com.example.demoex.services;

import com.example.demoex.dto.PostDto;
import com.example.demoex.dto.ReviewDto;
import com.example.demoex.entities.Review;
import com.example.demoex.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    // DI, 생성자 방식 구현
    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    public void create(ReviewDto reviewDto) {
        // 1. 리뷰 엔티티 구성 (현재 Dto가 부재)
//        Review review = Review.builder()
//                .content(content)
//                .createDate(LocalDateTime.now())
//                .post(postDto.toEntity())
//                .build();
        // 2. 레퍼지토리를 이용하여 등록
        reviewRepository.save(reviewDto.toEntity());
    }

    public ReviewDto getOneReview(int id) {
        // id -> Review 엔티티
        // 데이터가 존재할 때 처리 -> 엔티티 -> ReviewDto 반환
        Optional<Review> oReview = reviewRepository.findById(id);
        if(oReview.isPresent()) {
            Review review = oReview.get();
            return ReviewDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .createDate(review.getCreateDate())
                    .post(review.getPost())
                    .build();
        }
        // 없을 경우 -> 예외처리를 던지는 것으로 정리
        // throw new Exception();
        return  null;
    }

    public void delete(ReviewDto reviewDto) {
        this.reviewRepository.delete(reviewDto.toEntity());
    }

    // 리뷰 수정
    public void modify(ReviewDto reviewDto) {
        this.reviewRepository.save(reviewDto.toEntity());
    }
}
