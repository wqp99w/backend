package com.example.demoex.dto;

import com.example.demoex.entities.Post;
import com.example.demoex.entities.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class ReviewDto {
    private int id;
    private String content;
    private LocalDateTime createDate;
    private Post post;

    public Review toEntity() {
        return Review.builder()
                .id(this.id)
                .content(this.content)
                .createDate(this.createDate)
                .post(this.post)
                .build();
    }
}
