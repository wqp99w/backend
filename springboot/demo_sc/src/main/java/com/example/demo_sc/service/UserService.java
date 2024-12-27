package com.example.demo_sc.service;

import com.example.demo_sc.dto.UserDto;
import com.example.demo_sc.entity.User;
import com.example.demo_sc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 일반적인 User 테이블과 연관된 비즈니스 로직 처리
 *  - 회원가입
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void create(UserDto userDto) {
        userRepository.save(User.builder()
                        .email(userDto.getEmail())
                        // 비번 안호화!! -> DI
                        .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                        .build());
    }
}
