# 개요
    - Java 언어
        - 과정 : JavaScript, Java, Python
        - 특징
            - 객체 지향 프로그래밍 언어
                - 클래스 단위로 개발발
            - 기반 C/`C++`
            - 1995년 개발됨, Sun 마이크로시스템즈 (www.java.com)
                - 2008~9 Orcle 인수되었음
            - 철학
                - 모든 기기를 연결하는 `네트워크` 상의 언어
                    - 초기 : J2SE(PC유저), J2ME(모바일), J2EE(엔터프라이즈, 웹)
                    - 현재
                        - 엔터프라이즈 시장!! 집중 -> `웹 서비스`
                            - Sevelet/JSP -> EJB -> Spring -> `Spring Boot (현재)`
                        - 모바일(피처폰(midp) -> 안드로이드: 자바->코틀린)
                            - 점점 비중이 사라짐
                    - 정리
                        - 자바 => Spring Boot
    - 개발 환경
        - 개발 PC에는 JDK(JRE(런타임 환경)+JVM(가상 머신)) : (자바 디밸롭 툴킷) 설치되어 있어야 함
            - 17 이상 버전 권장, 스프링부트 3.x 버전 이상
            - Window x64 installer
                - https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe
        
        - IDE
            - 개발 통합 환경
            - 레거시 방식   - eclipse (이클립스) -> STS 
            - 최근 3~4년    - InterliJ (인텔리J)
                            - Ultimate (유료, 대학생/교육용 무료)
                            - https://www.jetbrains.com/ko-kr/idea/download/?section=windows (30일 평가판 설치)
                            - 졸업생등 비현역
                                - 교육 라이센스 제공 (별도로 제공)
                - 젯브레인 : 안드로이드 스튜디오, 파이참, ... 코틀린,,

- 환경 구축 절차
    - JDK 설치
        - path 부분 주의깊게 체크 (안나오면 생략)
        - 설치간 특이 사항 없음
        - 설치 완류 후 확인 -> path 확인
            - 터미널 오픈
            - 아래 명령어 실행
                ```
                    java
                    java -version
                ```
    - IDE 설치
        - 설치간 특이점
        - 연결 파일등.. 메뉴