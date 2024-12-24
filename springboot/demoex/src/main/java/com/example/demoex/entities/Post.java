package com.example.demoex.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 본글에 대한 엔티티, post 테이블 대변
 */
@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@Entity // 이거 붙여야 엔티티 됨
public class Post {
    // 컬럼 나열
    @Id
    // auto_increment, 자동 부여, 자동 증가, 중복 x
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 크기
    @Column(length = 256)
    private String subject;

    // 65536 크기 입력 가능
    @Column(columnDefinition = "TEXT")
    private String content;

    // 커스텀 컬럼 생략
    private LocalDateTime createDate;

    // mappedBy => FK 컬럼명
    // Review에서 사용한 FK 이름 가져옴
    // cascade = CascadeType.REMOVE => 본글 삭제되면 관계된 리뷰 모두 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    // 전체 맴버를 구성(초기화)하는 생성자를 빌더 패턴으로 사용가능하게 표현
    @Builder
    public Post(int id, String subject, String content, LocalDateTime createDate, List<Review> reviews) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.reviews = reviews;
    }
}

