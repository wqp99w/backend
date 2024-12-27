package com.example.demo_sc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  - User 엔티티와 데이터 교환
 *      - 디비 용도, 통신 용도(로그인, 회원가입 등 전달 데이터 받는 구조)
 */
@Getter
@Setter
@ToString
public class UserDto {
    private String email;
    private String password;

    @Builder
    public void toEntity(){

    }
}
