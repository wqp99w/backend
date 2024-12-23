# 스프링부트
    - 사전 지식
        - 웹에 대한 간단한 이해/사용
        - 자바 사용 간단하게라도 이해하면 가능
        - 디비 사용 간단하게라도 이해하면 가능
        - html/css/js 로 화면 구성되는것 간단하게라도 이해하면 가능
    - 프로젝트 간단하게 생성하기        
        - hello world 출력
        - 프로젝트 생성
            - 햄버거 > project > springboot 선택
            - 기본 입력값 유지 > 프로젝트 위치만 조정 (~/backend/springboot)
            - next 클릭
            - springboot dev tool, lombok, spring web 선택(체크)
            - next or create 클릭
            - 프로젝트 생성됨
        - 서비스 생성
            - src/main/java/com.example.demo 밑에서 우클릭 > new > class > HomeController 엔터
            ```
                import org.springframework.stereotype.Controller;
                import org.springframework.web.bind.annotation.GetMapping;
                import org.springframework.web.bind.annotation.ResponseBody;

                // http://localhost:8080 접속
                @Controller
                public class HomeController {
                    @GetMapping("/")
                    @ResponseBody
                    public String home() {
                        return "hello world 스프링부트";
                    }
                }

            ```
        - 실행
        - 브라우저 접속
            - http://localhost:8080

    - 목적 
        - 웹서비스 구현 => 클라이언트에게 서비스 제공
            - 쿠팡, 네이버, 뱅킹, 노션, 주식, 블로그, ... 

# 개요
    - 백엔드 구성
        - 유사 제품 : (*)Springboot, .net, nodejs, django/flask/(*)fastapi, php,...
        - 언어를 중심으로 분류됨
    - 프레임워크 vs 라이브러리
        - 프레임워크는 해당 제품의 `룰(구조, 규칙)`에 따라 코드가 작성되어야 한다
        - 라이브러리는 내가 작성한 코드에 사용(적용), 나의 룰에 따라 작성된다. 단, 사용법은 있음
    - 스프링부트
        - 웹서비스를 쉽고 빠르게 개발하기 위해 자바 언어를 이용하여 만든 프레임쿼크
        - 필요한 대부분의 기능들은 라이브러리로 제공
            - 프로젝트 구성 시 쇼핑하듯이 필요한 제품 선택 : 데브툴, 롬복, jpa, 타임리프, h2, web, ...
            - 나중에 추가 가능
    - 스프링부트(프레임워크) 구조
        ```
            .
        ├── HELP.md
        ├── build                                           : 개발(*.class, 리소스), 배포(*.jar|war) 생성됨
        ├── (*)build.gradle                                 : 사용 라이브러리 기술/편집, 빌드 정보(JDK, 이름 등)
        ├── gradle
        │   └── wrapper
        │       ├── gradle-wrapper.jar
        │       └── gradle-wrapper.properties
        ├── gradlew                                         : 빌드 명령어 기술되어 있음 -> CI/CD 할 때 체크(세션 2), 맥/리눅스 용
        ├── gradlew.bat                                     : 빌드 명령어 기술되어 있음 -> CI/CD 할 때 체크(세션 2), 윈도우 용
        ├── settings.gradle
        └── (*)src
            ├── main: 개발 코드
            │   ├── java
            │   │   └── org
            │   │       └── example
            │   │           └── demoex
            │   │               ├── DemoexApplication.java
            │   │               └── controllers
            │   │                   └── HomeController.java
            │   └── resources                               : html/css/js or 리액트 포함 시킬 경우, 이미지, 환경변수파일
            │       ├── (*)application.properties           : 환경변수파일 (application.properties|yaml)
            │       ├── static                              : 정적 데이터 위치 (css, js, 이미지 등등)
            │       └── templates                           : html 파일 위치, 타임리프(템플릿 엔진)가 기본으로 인식하는 위치
            │           └── index.html
            └── test                                        : 단위 테스트 진행 시, TDD(테스트 주도 개발 방법론), CI/CD에서 중요하게 사용
                └── java
                    └── org
                        └── example
                            └── demoex
                                └── DemoexApplicationTests.java
        ```


# 프로젝트 생성 시 템플릿
    - 프로젝트명, 경로 설정
    - type -> 지정 후 프로젝트 경로 내 빌드 파일
        - Gradle.G...   : build.gradle  <- 2020년 이후에 많이 보임
        - Gradle Kotlin : build.gradle  <- 2020년 이후에 많이 보임
        - maven         : pom.xml       <- 레거시 방식

# 개발 도구 지원 - devtools
    - spring-boot-devtools  : 감시 -> 소스 수정 -> 감지 -> 재가동
    - lombok                : 코드 자동 완성(getter, ...)
    - live reload++         : 브라우저 내에서 해당 페이지를 감시 -> 변경이 확인(재가동) -> 새로고침 자동 처리
        - 크롬 익스텐션
            - https://chrome.google.com/webstore/detail/livereload%20%20/ciehpookapcdlakedibajeccomagbfab
            - 구글에서 미지원 (업데이트 이슈)

# 컨트롤러
    - 스프링부트가 요청을 받으면 -> 요청 URL 분석 -> 누가 처리할 것인지 판단 -> 해당 요청을 처리하는 모듈에 전달
    - 컨트롤러는 특정 URL을 처리하도록 구성 -> 라우팅 과정
        - 종류 (어노테이션으로 구분)
            - @Controller       :
                - html을 랜더링하여 응답하는 방식, 텍스트 응답도 가능
                - 응답 형식 : html
                - SSR 처리
                    - 전통적인 웹서비스
                    - SB : html/css/js 응답답
            - @RestController   :
                - 화면 X, html/css/js X -> open api
                - 응답 형식 : json/xml
                - CSR 처리와 세트로 구성이 됨
                    - 모바일/리액트(SPA) <-> SB

    - URL 구성
        - 구분
            - 직접 url 개별적 부여 (일일이)
            - prefix를 부여하여 ~/prefix/~ 하위로 부여
        - 방식
            - 기획서 작성 -> 필요한 페이지와 URL 정리 -> 프로젝트 시작 시 가장 처음에 구성!! (컨세)
        - auth(인증) 라는 prefix를 가진 컨트롤러 생성
            - prefix는 다른 플랫폼(블루프린트 라고도 함) -> 업무 범위 표현!!
            - http://localhost:8080/auth/~
            - A라는 개발자는 인증 모듈 개발, 회원가입, 로그인, 로그아웃, 등등 ... 구성 개발
        - 하위 주소
            - ~/auth/login
            - ~/auth/join
            - ~/auth/signup
            - ~/auth/logout
    
    - 데이터 전달
        - client -> server 데이터 전달
        - 전달 방법(method)
            - (*)get, (*)post, put, ...
        - 클라이언트 전송 방식 (프런트엔드)
            - html
                - form 전송 : get, post 사용
                - a 태그 : 링크 전송, get
            - js
                - ajax 사용
                    - axios 사용
                    - fetch 사용
        - 서버는 어떻게 데이터를 받는가?(수신)
            - 결론 : 요청(request) 객체를 타고 전달됨
            - 코드 표현
                - 메소드 방식
                    - @GetMapping()     : get 데이터 획득
                        - 브라우저 => 주소창 => URL 입력 => 엔터 : get 방식 요청
                    - @PostMapping()    : post 데이터 획득
                        - 보안 중요, 전달 데이터가 노출되면 X, 대량 데이터 전송 (파일 업로드)
                        - html form 혹은 ajax로 사용, 요청 테스트 도구(POSTMAN, thuder, ...)
                        - Thunder Client
                            - vscode extension 설치
                            - 번개 아이콘 클릭 > 진입 > new request 클릭
                                - POST 선탯
                                - https://localhost:8080/auth/signin
                                - SEND 버튼 클릭
                                - 화면 하단 200 OK (요청 테스트 확인)
                                - 데이터 세팅
                                    - Body > form
                                        - uid, 값
                                        - upw, 값
                                - SEND 버튼 클릭 => 요청
                    - ...
                - url path(타 플랫폼 동적 파라미터) 방식
                    - 주소(URL)에 타고 전달
                    - 여러 메소드 방식과 조합 가능함

# 템플릿 엔진
    - @Controller에서만 해당
    - 타임리프 제품 사용
        - thymleaf.org
        - modern server-side Java template engine
        - 관련 문법은 필요 시 등장시킴
    - html + 데이터 => 동적으로 html 재구성 => 응답용 html 완성시키는 템플릿 엔진의 한 종류
    - 기본적인 처리 문법은 실제 적용할 때 체크
        - 게시판 구현할 때 사용
# 롬복
    - getter, setter 생성자, 빌더 등 자동으로 생성해주는 라이브러리
    - 패키지 dto 준비
        - News.java 생성ㄴ

# RestAPI, @RestController
    - 목적
        - json을 응답하는 구조 체크
    
    - 구성
        - ~/controllers/ApiController 생성

# 스프링부트 기반 데이터베이스 연동 -> 게시판 구성
    - java의 DB 연동
        - JDBC -> 컨넥션풀 (기법) -> iBatis -> myBatis -> JPA
    - (*)JPA
        - 2020년 전후로 많이 사용, 신규 프로젝트에서 주로 사용
        - ORM 방식 (객체로 적용하여, 데이터 1개 => 객체 1개와 대응하는 구조), 객체로 관리
        - SQL을 잘 몰라도 적용가능, 디비 교체도 수월함
    - myBatis
        - 레거시 시스템 많이 적용되어 있음
        - 2010년 이후 많이 사용됨, 전자정부프레임워크, 등등 대부분 구성
        - SQL 중점으로 두고 개발, SQL 쿼리 작성 중요!!
        - 금요일날 별건으로 체크
    - 데이터베이스
        - 내장형 DB (*)h2 사용
            - 경량, 별다른 세팅없이 바로 사용가능, JPA 기반인 경우 마이그레이션 가능(다른 디비로)
        - 상용제품
            - 오라클, (*)mysql/mariadb, postgre, ...
    - H2 세팅
        - build.gradle 라이브러리 세팅 (초기 프로젝트 생성 시 완료됨)
            ```
                // 서버 구동 시(런타임 시) 라이브러리 작동
                runtimeOnly 'com.h2database:h2'
            ```
        - application.properities 환경설정
            ```
                spring.application.name=demoex
                # 1. H2 database 설정
                # 브라우저에서 접속하여 sql 실행
                spring.h2.console.enabled=true
                # 브라우저 접속 주소
                spring.h2.console.path=/h2-console

                # 2. database connect 파트
                # app_db는 커스텀이름
                # C:\Users\사용자명\app_db.mv.db 자동생성(생성 안되면 수동처리)
                spring.datasource.url=jdbc:h2:~/app_db
                # 접속 시 드라이버 설정
                # spring.datasource.driverClassName=org.h2.Driver 이렇게 해도 됨
                spring.datasource.driver-class-name=org.h2.Driver
                # 접속계정
                spring.datasource.username=sa
                spring.datasource.password=

                # 3. JPA 설정
                # 데이터베이스 엔진 종류 설정
                spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
                # JPA -> 사용이득 -> 테이블로 알아서 생성해준다!!
                # 엔티티 클래스 작성 -> 매칭되는 테이블이 자동 생성됨!!
                # 단 데이터베이스는 직접 생성!!
                spring.jpa.hibernate.ddl-auto=update
            ```
    - 디비 접속
        - 서버 가동 중이어야 함
        - http://localhost:8080/h2-console

# JPA를 이용한 데이터베이스 프로그램
    - 코드 관점
        - workflow
            - controller <-> service <-> dto <-> repository <-> entity <-> jpa <-> database
            - 일부 가감될 수는 있다

        - controller
            - 클라이언트 요청을 처리하는 진입점, 클라이언트는 여기까지만 올 수 있음
            - 라우트 처리의 결과물
            - http 요청을 받아서 적절한 서비스 계층으로 호출, 결과를 받아서 반환(응답) 담당
            - 요약
                - 요청 처리, 파라미터 검증/추출, 적절한 서비스 호출, 응답 생성/반환

        - service
            - (*)비즈니스 로직 처리 계층
            - ex) 검색, 로그인, 로그아웃, 회원가입, 글등록, 글수정, ...
            - 필요 시 여러 API, 라이브러리 동원하여 처리
            - 요약
                - 컨트롤러의 요청을 받아서 처리, 결과를 반환
                - 데이터는 DTO대상으로 처리

        - dto (Data Transfer Object)
            - (*)엔티티와 데이터 교환 역할
            - 왜?, 엔티티는 디비의 테이블에 1개의 데이터와 각각 연결되어 있음
                - 엔티티가 수정되면, 디비상 테이블의 데이터도 수정된다!! (동기화되어 있음)
                - 보안에 취약함 (엔티티, 엔티티가 털리면 디비도 털리는거니까)
                - 엔티티는 절대로 컨트롤러에 노출되면 안됨
            - 엔티티 -> dto를 통해서 데이터만 전달, 연결 고리가 끊김 -> 보안 처리할 수 있음
            - 보안때문에 사용
            - 종류
                - db
                    - 엔티티와 데이터 교환용
                - rest api
                    - 요청 데이터 대체용(JSON)
                    - 응답 데이터 대체용(JSON)
        - repository
            - JPA를 이용하여 데이터베이스와 상호 작용
            - 내부적으로 (*)SQL 담당
            - 자동 구성해줌 -> 메소드 사용 (알아서 내부적으로 해줌), 단 복잡한 쿼리는 필요 시 직접 구성 가능함
            - 엔티티 객체를 사용하여 CRUD 작업

        - entity
            - (*)데이터베이스의 테이블과 매핑이 되는 객체
            - 엔티티는 곧 테이블이다
            - 엔티티 객체 1개는 곧 데이터 1개이다 (1대 1로 매핑)
            - ORM(객체 관계 매핑)을 실제로 구현한 것
            - 보안에 취약
            - 클라이언트에게 노출되면 안됨, 최대 Service까지만 사용 가능, controller 사용 x

        - jpa
            - Java Persistance API
            - (*)자바 표준 ORM 기술
            - 객체와 관계형 데이터베이스 간의 매칭 제공
                - JPA 사용은 데이터베이스를 자바에서 객체 지향으로 관리하겠다 의도!!, SQL 사용을 최대한 배제

        - database
            - RDB

# 구성 실습
    - 1. 패키지 구성
        - 방식 (대략 2가지 존재재)
            - 비즈니스 로직 별로 구성
                - auth
                    - *Controller.java
                    - *Service.java
                    - ...
                - main
                    - *Controller.java
                    - *Service.java
                    - ...
            - (*)자바 파일의 용도별로 구성성
                - controllers
                    - *Controller.java
                - services
                    - *Service.java
                - dto
                - repositories
                - entities