package com.example.demoex.services;

import com.example.demoex.dto.PostDto;
import com.example.demoex.entities.Post;
import com.example.demoex.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 서비스, 비지니스 로직 처리, SQL 주로 실행 명령을 내리는 곳
 */
@Service
public class PostService {
    // DI (의존성 주입, 유일한 객체 1개를 가져와서 사용하도록 조치)
    @Autowired
    private PostRepository postRepository;
    // 레포지토리(SQL을 가지고 있음)를 이용하여 모든 게시물을 가져온다
    // Post 객체(엔티티, 데이터 1개)를 PostDto로 변환하여 List에 담아서 반환 -> 보안
    public List<PostDto> getAllPost() {
        // 1. 레포지토리를 통해서 모든 게시물을 가져온다
        //      1-1. 레포지토리?(PostRepository) -> DI 처리 필요
        //      1-2. 사전에 준비된 메소드, 모든 데이터를 가져온다!!
        //              내부적: select * from post; 수행된 것으로 판단
        List<Post> posts = postRepository.findAll();

        // 2. 결과물을 담을 그릇(자료구조)을 준비한다 -> List<PostDto>
        List<PostDto> postDtos = new ArrayList<>();

        // 3. 순환하면서 Post -> PostDto로 데이터를 교환하여 그릇(List)에 담는다
        //      코드 정렬 단축키 : ctrl + shift + alt + l
        for (Post post : posts) {
            postDtos.add(PostDto.builder()
                            .id(post.getId())
                            .subject(post.getSubject())
                            .content(post.getContent())
                            .createDate(post.getCreateDate())
                            .reviews(post.getReviews())
                            .build());
        }

        // 4. 반환
        return postDtos;
    }
    // 레포지토리를 이용하여 1개의 포스트 객체를 획득 -> DTO 교환 후 반환
    public PostDto getOnePost(Integer id) {
        // Optional는 옵션 => 결과셋 있을 수도 없을 수도 있다!! -> 쿼리 결과가 없을 경우 처리를 위해 사용
        Optional<Post> oPost = this.postRepository.findById(id);
        if(oPost.isPresent()) { // 데이터가 존재하면
            // Post 엔티티 -> PostDto로 변환 후 반환
            Post post = oPost.get();
            return PostDto.builder()
                    .id(post.getId())
                    .subject(post.getSubject())
                    .content(post.getContent())
                    .createDate(post.getCreateDate())
                    .reviews(post.getReviews())
                    .build();
        }
        // 없을 경우 -> 예외처리를 던지는 것으로 정리
        //throw new Exception()
        return null; // 향후 커스텀 예외 처리 변경
    }

    public void create(PostDto postDto) {
        // insert ~ 작업 지시!!
        this.postRepository.save(postDto.toEntity());
    }

    // 글 수정 처리 -> 데이터를 보고 자동 판단함 (insert, update)그래서 코드는 동일함
    // 가독성 높이기 위해서 따로 함수를 뺀거임
    public void modify(PostDto postDto) {
        this.postRepository.save(postDto.toEntity());
    }

    // 글 삭제 처리
    public void delete(PostDto postDto) {
        this.postRepository.delete(postDto.toEntity());
    }
}
