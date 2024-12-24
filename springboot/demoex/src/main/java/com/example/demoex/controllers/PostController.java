package com.example.demoex.controllers;
import com.example.demoex.dto.PostDto;
import com.example.demoex.entities.Post;
import com.example.demoex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * - 글쓰기, 글목록, 글수정, 글삭제 등 url 제공
 * - 요청/응답 처리
 */
@RequestMapping("/post")
@Controller
public class PostController {
    // (* 매우 중요)DI 구성 3가지 방식 (@Autowired 사용, 생성자 함수 활용, setter 활용)
    @Autowired
    private PostService postService; // 서비스 객체를 가져와서


    // /post/list, get 방식 준비
    @GetMapping("/list")
    public String list() {
        // 1. 서비스(PostService)를 통해서 쿼리가 실행이 되고(내부적), 그 결과를 가져온다
        //      서비스 객체를 가져와서 -> 매번 가져온다? -> 객체가 매번 생성된다? -> n개
        //      (*)객체가 한번만 만들어지고 -> 요청할 때 마다 그 객체가 와서 작업되어진다면? -> 1개
        //      싱글톤 디자인 패턴!! 어떻게 제공할 것인가 -> SB에서는 Di(의존성 주입) 개념 도입 -> 해결
        //  postService.xxxx() 바로 사용 가능 (이미 객체는 만들어져 있다)

        // 서비스의 getAllPost() 메소드를 호출해서, 모든 게시물을 가져와서 DTO에 교환해서 받을 것이다!!
        // Dto 생성
        List<PostDto> posts =  postService.getAllPost(); // 모든 post의 내용 가져오기
        // 디비에서 가져온 데이터 확인
        for (PostDto post : posts) {
            System.out.println(post);
        }
        // 2. 조회 결과는 타임리프 템플릿 엔진에 전달아혀, 동적으로 html 구성한다(랜더링)
        // 3. 응답한다
        return "test/post_list"; // resources/templates/test/post_list.html 읽어서 랜더링
    }
}
