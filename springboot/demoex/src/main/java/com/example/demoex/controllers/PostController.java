package com.example.demoex.controllers;
import com.example.demoex.dto.PostDto;
import com.example.demoex.entities.Post;
import com.example.demoex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public String list(Model model) {
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
        //      Model 객체를 활용하여 데이터를 세팅 후 전달한다
        //      1. 매개변수에 모델 객체 추가
        //      2. 코드 내부에서 모델 객체에 데이터 추가
        model.addAttribute("posts", posts); // 게시물 데이터 전달
        model.addAttribute("dummy", "hello"); // 더미

        // 3. 응답한다
        return "board/post_list"; // resources/templates/test/post_list.html 읽어서 랜더링
    }

    @GetMapping("/create")
    public String create() {
        return "board/post_form";
    }

    // 자바의 오보로딩 => 매개변수를 다르게 사용해서 다른 메소드처럼 관리
    // 매개변수명은 <input name="값"> 값을 변수명으로 사용!! (중요)
    @PostMapping("/create")
    public String create(@RequestParam String subject,
                         @RequestParam String content) {
        // 1. 클라이언트가 전송한 데이터 추출
        System.out.println(subject + " " + content);
        // 2. 서비스를 통해서 데이터 입력 처리 요청
        //      입력값 -> PostDto 세팅 -> 서비스 처리 함수(미구현)에 전달
        this.postService.create(PostDto.builder()
                        .subject(subject)
                        .content(content)
                        .createDate(LocalDateTime.now()) // 서버의 시간, 클라이언트의 시간 값은 쓰면 관리가 어려움
                        .build());
        // 3. 처리 결과를 받아서 후속처리(잘 되었는지, 오류 났는지 등등) -> 생략
        // 4. 글이 하나 추가되었으므로, 다시 목록으로 포워딩!!
        return "redirect:/post/list"; // 특정 페이지로 자동 이동!!
    }

    /**
     * id 번호에 따라 글을 조회(디비 연동)하여, 상세보기 처리
     */
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        // 1. 파라미터 추출 => id 값 추출
        // 2. id를 이용하여 PostDto(Post와 교환된) 1개를 추출
        // 3. PostDto를 Model에 적용하여 타임리트에서 랜더링 요청
        // 4. 응답 html 완료(상세 보기 완성)
        PostDto post = this.postService.getOnePost(id);
        model.addAttribute("post", post);
        return "board/post_detail";

    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable Integer id) {
        return "board/post_form";

    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id) {

        return "delete";

    }

}
