package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    // 프로그램 시작 -> 웹서비스, 현재 손대지 x
    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

}
