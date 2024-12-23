package com.example.demoex.controllers;

import com.example.demoex.dto.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API 위한 컨트롤러 테스트
 *      - 화면 (html)이 없는 응답 내용 전송 (json or xml)
 *      - 백엔드이지만, 화면이 없는 요구사항일 경우 해당됨
 *          - open api 대상
 *          - 화며을 react로 모두 구성, 백엔드는 비즈니스 로직만 처리하는 구조
 */
@RestController
public class ApiController {
    // ~/api
    @GetMapping("/api")
    public News api() {
        // 요구사항 x
        // 롬복 적용 예시 활용 -> 객체 반환으로 교체
        return  News.builder()
                .title("스포츠 뉴스")
                .content("토트넘 리버플 경기")
                .author("기자")
                .build();
    }
}
