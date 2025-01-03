package com.example.demo_sc.controller;

import com.example.demo_sc.entity.User;
import com.example.demo_sc.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * 홈페이지, 인증 후에만 접근 가능
 */
@Controller
public class HomeController {
    @Autowired
    private AuthUtil authUtil;

    @GetMapping("/")
    // UserDetails 객체에 @AuthenticationPrincipal 적용하여 현재 인증 정보 삽입
    // Principal은 스프링 시큐리티에서 인증된 사용자임, @AuthenticationPrincipal이거 사용하면 해당 객체에 principal 정보가 저장됨
    public String home(@AuthenticationPrincipal UserDetails userDetails, Principal principal) {
        // 1. 컨트롤러 내부(혹은 매개변수 추가하여)에서 인증 정보 체크, 접근 확인
        if(userDetails != null) System.out.println(userDetails.getUsername());
        if(principal != null) System.out.println(principal.getName());

        // 2. 컨트롤러 이외의 자바 코드에서 인증 정보 체크, 접근 확인
        System.out.println(authUtil.isUserAuth());
        System.out.println(authUtil.getUsername());
        return "index";
    }
}
