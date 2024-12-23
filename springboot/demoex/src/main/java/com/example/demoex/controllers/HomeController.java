package com.example.demoex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * (*)SSR (Server Side Rendering) : Springboot <-> CSR (Client Side Rendering) : react
 *
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        // @Controller이고, 타임리프 설치했다면
        // return "index"; => index.html을 찾아서 랜더링하여 응답하라는 뜻!! (SSR)
        // index.html -> src/main/resources/templates/*.html
        return "index";
    }
}
