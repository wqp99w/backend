package com.example.demoex.controllers;

import com.example.demoex.dto.PostDto;
import com.example.demoex.dto.ReviewDto;
import com.example.demoex.entities.Review;
import com.example.demoex.form.ReviewForm;
import com.example.demoex.services.PostService;
import com.example.demoex.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/review")
@RequiredArgsConstructor // Di를 위한 준비
@Controller
public class ReviewController {
    // DI (의존성 주입, 1개의 유일한 객체를 삽입하여 사용가능하게 처리)
    // 세팅법: @Autowired, (*)생성자 방식, setter 방식
    private final PostService postService;
    private final ReviewService reviewService;

    // 리뷰 등록 -> 특정 글에 대한 리뷰, 댓글 작성 (1:N 관계)
    // Post 방식, 검증폼 사용 x
    @PostMapping("/create/{id}")
    public String create(@PathVariable int id,
                         @Valid ReviewForm reviewForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/post/detail/" + id;
        }
        // id : 본글 고유 번호 (PK)
        // content : 리뷰/댓글 내용
        // 1. id를 기반으로 post dto 획득
        PostDto postDto = this.postService.getOnePost(id);

        // 2. post dto, 리뷰 내용을 가지고, 서비스(ReviewService)로 이동, 리뷰 등록 처리
        this.reviewService.create(ReviewDto.builder()
                        .content(reviewForm.getContent())
                        .createDate(LocalDateTime.now())
                        .post(postDto.toEntity())
                        .build());

        // 3. 원래 본글의 상세 보기 화면으로 포워딩
        return "redirect:/post/detail/" + id;

    }

    @GetMapping("/list")
    public String list() {
        return "list";

    }

    // 리뷰 수정, 검증 폼을 활용, 화면 세팅 (기존 내용 채워서) 구성
    @GetMapping("/modify/{id}")
    public String modify(ReviewForm reviewForm, @PathVariable int id) {

        // 리뷰폼에 내용 세팅 -> 수정 시 관련 내용 자동 세팅
        // 리뷰 id -> 리뷰 획득 -> 리뷰폼 세팅
        ReviewDto reviewDto = this.reviewService.getOneReview(id);
        reviewForm.setContent(reviewDto.getContent());
        return "board/review_form";
    }
    // 리뷰 수정 처리
    @PostMapping("/modify/{id}")
    public String modify(@Valid ReviewForm reviewForm,
                         BindingResult bindingResult,
                         @PathVariable int id){
        // 1. 유효성 검증 결과 에러가 존재하면 -> 오류 세팅하여 입력폼 화면 띄우기
        if (bindingResult.hasErrors()) {
            return "board/review_form"; // 같은 url의 get 방식의 리턴값
        }

        // 2. id에 해당되는 ReviewDto 획득
        ReviewDto reviewDto = this.reviewService.getOneReview(id);

        // 3. 새로 입력한 내용으로 ReviewDto 수정
        reviewDto.setContent(reviewForm.getContent());

        // 4. 서비스에 수정 요청, ReviewDto 전달
        this.reviewService.modify(reviewDto);

        // 5. 본글 상세 보기로 포워딩 -> 수정된 리뷰 확인 가능 (본글 상세보기에서 확인)
        return "redirect:/post/detail/" + reviewDto.getPost().getId();
    }

    // 리뷰 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        // 1. 리뷰 ID를 기반 -> 실제로 데이터가 존재하는지 조회
        ReviewDto reviewDto = this.reviewService.getOneReview(id);
        // 2. 없을 경우 처리 -> 생략
        // 3. 테이블상에서 실제 삭제 -> 서비스.delete(reviewDto)
        this.reviewService.delete(reviewDto);
        // 4. 본글 상세보기 -> 특정 리뷰의 삭제 버튼 클릭 -> 삭제 요청/처리 -> 본글 상세보기
        return "redirect:/post/detail/" + reviewDto.getPost().getId();

    }
}
