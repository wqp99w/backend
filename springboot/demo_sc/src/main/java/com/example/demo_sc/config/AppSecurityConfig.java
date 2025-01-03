package com.example.demo_sc.config;

import com.example.demo_sc.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * 스프링 시큐리티의 정책, 보안적 설정 등등 기술
 */
@RequiredArgsConstructor
@Configuration
public class AppSecurityConfig {
    // DI : Di 사용 시 문제점 -> 순환 참조(무한) 발생 소지 있음 -> 방향성 고려, 방식 섞어서 사용
    //      생성자 방식으로 DI 구성
    private final UserDetailService userDetailService;
    // 모두 다 @Bean 처리

    // 2. 전체 페이지 권한 여부설정 (가장 중요, 메인)
    //      - 인증이 필요한 페이지, 필요 없는 페이지 등등 설정
    //      - 로그인 페이지(커스텀), 로그인 성공 후 포워딩 페이지등 지정
    //      - 로그아웃 처리, 등 추가 처리
    //      - csrf 공격 대응 처리
    //      - 기타 필요 처리, ...
    //      - 시큐리티 5.x -> 6.x 변경되면서 바뀐 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                // 1. 인증이 필요한 페이지와 아닌 페이지
                .authorizeRequests()
                    // 아래 페이지는 인증 필요 x
                    .requestMatchers("/login", "/signup", "/signup_process").permitAll() //허가
                    // 나머지 모든 페이지는 인증 필요
                    .anyRequest().authenticated()
                .and() // 모든 정책 사이에는 .and()로 그리고 ~~ 이런 식으로 흘러감
                // 2. 로그인 페이지(커스텀), 로그인 성공 후 포워딩 페이지등 지정
                .formLogin()
                    .loginPage("/login") // `커스텀` 로그인 페이지 이동
                    .defaultSuccessUrl("/") // 로그인 성공하면 홈페이지 포워딩
                .and()
                // 3. 로그아웃 처리, 등 추가 처리
                .logout()
                    // 로그아웃 요처은 인증이 있을 때만 가능하다!! -> URL 지정 x, 기능만 붙이면 어떤 url도 로그아웃이 될 수 있음
                    .logoutSuccessUrl("/login") // 인증 없이는 접근이 안되게 구성 -> 다시 로그인
                    .invalidateHttpSession(true) // 로그아웃하면 세션(서버측에 클라이언트 정보 저장관리하는 객체)을 다 날려야 한다.
                .and()
                // 4. csrf 공격 대응 처리
                .csrf()
                    // 활성화 처리되면 -> form 태그 밑에 해당 값에 대한 설정이 필요(hidden 타입)
                    .disable() // 대응처리 안하겠다 -> 나중에 활성화
                .build();
    }

    // 3. 로그인 처리? 구성
    //      - 인증 매니저 객체가 담당-> 스프링 부트 내에서 고유하게 1개만 객체로 존재
    //      - 인증 관리자 객체 필요 -> 빈 구성 -> DI 처리 가능해짐
    //      - 비밀번호 암호화 설정
    //      - 디비와 연동되는 실제적인 로그인 처리 -> 비즈니스 로직 -> 서비스 등록
    //      - 1개의 객체가 필요한데, 서비스x, 레포지토리 x -> config 내부에 존재하는 @Bean or @Component
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailService userDetailService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                // 인증 관련하여 디비에 쿼리를 수행하여 존재여부 체크
                // 서비스를 이용해서 내가 입력한 이메일을 통해 유저 정보를 가져온다
                .userDetailsService(this.userDetailService)
                // 입력한 비밀번호를 암호화해서, 유저 엔티티에 저장된 암호와 동일한지 검증한다.
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 4. 암호화 관련 모듈 필요 -> 빈 구성 -> DI 처리 가능해짐
    //      사용자 -> 비번입력 1234 -> 암호화 -> 해시로 구성 29412iohafskjfsal
    @Bean
    // 한 유저 엔티티의 비밀번호는 동일한 해시 함수로 암호화를 해야 하기 때문에
    // 동일한 해시 함수를 사용하기 위해 아래의 함수를 사용하여 동일한 해시함수 입력
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1. 예외처리 -> 인증에 상관없이 원래 사용하던대로 자유롭게 접근 가능
    //      - h2 console
    //      - 정적 데이터 -> resource/static/js|css|image|...
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 다음 등록된 내용은 보안 정책이 무시된다
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers(toH2Console()) // ~/h2_console
                .requestMatchers("/static/**") // 정적 데이터 모든 것
                ;
    }
}
