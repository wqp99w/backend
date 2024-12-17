# 데이터베이스
- 개발환경 구축
    - 로컬 PC 기반 설치
        - OS에 직접 설치
            - 단점, 업데이트 이슈, PC가 교체되면 다시 설치
            - 인터넷 연결 X 사용가능
            - 상대적으로 빠른 구동!!
        - mysql은 최초는 무료 > oracle 회사에 인수
            - mysql 개발자들이 나와서 만든 DB -> mariadb
            - aws에서 오로라DB -> mysql 엔터프라이즈급 디비 제품 존재
            - 동일한 SQL을 사용
                - SQL = 표준SQL + 벤더별SQL
            - 다운로드 주소
                - https://downloads.mysql.com/archives/installer/

    - (*)Docker 기반 컨테이너 설치
        - 컨테이너 베이스로 설치
        - OS에 영향 X, 삭제, 설치등 원활
        - 리눅스 위에서 작동 -> 일관성 가짐
        - Devops상에서 기본 구동 형태로 사용 
        - 세팅
            - (*)Docker client 설치
            - 명령어에서 mysql 설치 
            - mysql 전용 workbanch 설치(별로도)

        - mysql 설치
            - image 다운로드
                - mysql 이미지 다운로드 (ex) xx.iso파일->CD굽기, usb 설치파일등등등 )
            - image로부터 컨테이너 생성 
                - mysql 이미지 -> 설치
                - 컨테이너 가동 -> mysql 사용!!
            - (*)mysql 이미지/컨테이서 생성
                ```
                    docker run -d -p 3306:3306 --name mysql --env MYSQL_USER=ai --env MYSQL_PASSWORD=1234 --env MYSQL_ROOT_PASSWORD=1234 mysql
                ```
                ```
                    docker run  : 이미지를 다운, 컨테이너 생성,구동
                    -d          : 백그라운드로 가동
                    -p 3306:3306 : OS단에서 3306번으로 접근(포트)
                    --name mysql : 컨테이너 이름
                    --env MYSQL_USER=ai : 환경변수 ai 유저 생성
                    --env MYSQL_PASSWORD=1234  : ai 유저의 비번
                    --env MYSQL_ROOT_PASSWORD=1234 : root 유저의 비번
                    mysql : 이미지 이름
                ```
            - 확인
                - 도커 클라이언트 툴
                - 컨테이너 탭
                - mysql 클릭
                - EXEC 클릭 
                    ```
                        # 접속
                        mysql -u root -p
                        password:1234 <= 비번입력
                        ...

                        mysql> <= 정상 설치및 접근 OK
                    ```

    - Cloud 기반 설치
        - AWS(아마존 클라우드) 기반 RDS 서비스 사용
            - 비용 주의!!
        - AWS 기반 고사양 EC2 직접 설치

    - Client Tool
        - GUI 기반 툴로써, mysql을 손쉽게 엑세스하여 쿼리 수행및 기타 관리를 하는 용도임!!, 디비 본체는 도커내 컨테이너임

        - MySQL Workbench
            - https://dev.mysql.com/downloads/workbench/
        
        - (*)하이디SQL
            - https://www.heidisql.com/download.php
            - 연결 
                - 실행 > 세션관리자 > 신규
                - 호스트 : 127.0.0.1
                - 사용자 : root
                - 비밀번호 : 1234
                - 데이터베이스 : 삼각형 버튼 눌러서 목록이 보이면 접속 성공
                    - 선택 없이 확인 버튼 클릭 > 진입
            - 더미 데이터 다운
                - 자료실(구글)
                    - ~/backend/data/sql_test_db.sql
            - 접속후 화면 
                - File > SQL 파일 실행 > sql_test_db.sql 선택
                - 인코딩 팝업(확인 클릭)
                - F5 : 새로고침 실행
            - 차후 세션관리자에서 데이터베이스 항목 t1으로 변경
        
        - (*)vscode, 인텔리J등 => extention or 기본메뉴
            - mysql 검색후  Weijan cjen 제품(2번째 ) 설치

- 학습 범위
    - (*)SQL
    - 상황보고
        - 모델링
        - (*)ERD

# SQL 구성
    - Structured Query Language
        - Database
            - (대량) 데이터를 스키마(구조)에 맞춰서 저장하는 저장소
            - (*)정형, 비정형, 반정형 데이터
            - 개발자는 DB에 질의를 해서 데이터를 획득
                - 비즈니스 로직 처리
                    - 아이디/비번 => 조회 => 로그인
                    - 검색어 => 검색 => 결과를 출력 : 검색엔진진
        - 질의
            - 구조화된 질의 언어(SQL) 문법 통해 진행
            - 데이터베이스와 대화를 하는 언어
    - 구성
        - SQL = (*)DQL + DDL + DCL + DML (or TCL) 
            - DQL : Data Query Language
                - 조회!!, 질의 => 결과셋, 가장많은 분량
                - select
            - 나머지 언어, 진행하면서 정의

# 접속
- 도커 클라이언트 기준
    - 컨테이너 > mysql 클릭 > Execs 클릭
    ```
        mysql -u root -p
        password > 1234 <= 않보임
    ```

# DQL
- 개요
    - 데이터베이스라는 저장소(데이터웨어하우스,...)에서 원하는 데이터를 가져오는 쿼리문
    - 데이터 질의어
    - SQL => 대소문자 구분 x
        - Select or SELECT or select, ...

- 데이터베이스 조회
    - 존재하는 모든 데이터베이스 확인
    ```
        show databases;
    ```
    - 내가 접근한 데이터베이스 존재여부 확인
        - 물리적으로 존재하는가?
        - 내가 로그인한 계정에서 접근 가능한가?

- 특정 데이터베이스 사용 지정
    ```
        use mysql;
    ```

- 지정한 데이터베이스에서 테이블 목록 확인
    ```
        -- 모든 테이블 목록 출력
        show tables;
    ```

- 테이블의 상세 정보 출력
    ```
        - 테이블의 구조(컬럼, 타입, ....) 확인 가능함
        desc user;
        describe user;
    ```

- SELECT - 데이터 조회
    - 가장 많이 사용하는 구문
    - [ .. ] => 생략가능!!
    ```
        SELECT select_expression(조회 결과로 나오는 내용:컬럼,..)
            [FROM table_reference:테이블명]  <= 출처
            [WHERE condition:조건]      <= 데이터 조건, 1차 조건
            [GROUP BY {col_name|expression|posion}] <= 집계/통계
            [HAVING condition:조건]     <= 2차조건
            [ORDER BY 정렬(오름|내림) ]  <= 정렬대상을 여러개 나열가능 (a컬럼 오름, b컬럼 내림)
            [LIMIT [시작], 끝] <= 제한, 게시판(페이징처리)
    ```

- SELECT ~ FROM
    - 특정 테이블로부터 데이터 획득!!
        ```
            -- City2라는 테이블에서 => 출처
            -- 모든 데이터를 => 모든(*) 컬럼
            -- 가져(조회)오시오
            -- 문장의 끝 => ; 반드시 붙임
            -- 결과셋 : (4079, 5)
            SELECT *
            FROM city2;
            -- * : 사용 가급적 금지!!
            -- 반드시 조건 사용!! -> 모두 가져오기 x
        ```

    - 특정 컬럼만 가져오기
        ```
            -- city2 테이블에서 
            -- name, Population 컬럼만 조회하여
            -- 모든 데이터를 가져오시오
            SELECT name, Population
            FROM city2;
            -- select_expression 자리에는 컬럼을 순서대로
            -- 나열하면 결과를 원하는대로 가져올수 있다
        ```