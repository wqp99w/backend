package com.example.demo_sc.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 인증 정보, 권한 정보 등등 체크하는 등 기능을 가진 메소드 제공
 *  - 어떤 클래스에거든 싱글톤 작동하길 원함 -> 의존성 주입 가능하게 구성
 *  - -> @Component로 정의 -> 스프링부트 관리자가 빈으로 관리하게 구성 -> 빈이 되면 의존성 관리를 할 수 있게 됨
 */
@Component
public class AuthUtil {
    // 서브에 구성하는 메소드 -> DI 선언 후, 메소드 사용
    public boolean isUserAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated(); // 필요 시 더 꼼꼼하게 체크 가능함 (조건 추가 가능)
    }
    public String getUsername() {
        if (isUserAuth())
            return SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }
}
