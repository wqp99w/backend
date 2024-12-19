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
            -- city라는 테이블에서 => 출처
            -- 모든 데이터를 => 모든(*) 컬럼
            -- 가져(조회)오시오
            -- 문장의 끝 => ; 반드시 붙임
            -- 결과셋 : (4079, 5)
            SELECT *
            FROM city;
            -- * : 사용 가급적 금지!!
            -- 반드시 조건 사용!! -> 모두 가져오기 x
        ```

    - 특정 컬럼만 가져오기
        ```
            -- city 테이블에서 
            -- name, Population 컬럼만 조회하여
            -- 모든 데이터를 가져오시오
            SELECT name, Population
            FROM city;
            -- select_expression 자리에는 컬럼을 순서대로
            -- 나열하면 결과를 원하는대로 가져올수 있다
        ```
    
    - 컬럼명 별칭
        ```
            -- 컬럼의 이름이 너무 길거나, 특정되지 않는다!!
            -- 별칭 -> 결과셋의 컬럼명을 변경
            -- 원본 이름 as 별칭
            -- 컬럼명을 변조 -> 원본 테이블의 컬럼명 노출 방지(긍정)
            SELECT NAME AS nm, 
                    Population AS popu
            FROM city;
        ```

- SELECT ~ FROM ~ WHERE ~
    - 특정 조건을 부여 -> 서브셋만드는 과정
    - 조건이 없다면 -> 모든 데이터, (*)조건이 있다면 -> 해당 데이터
    - 연산자
        - 조건 연산자 : =, >, <, >=, <=, !=, <>
        - 관계(논리) 연산자 : NOT, AND, OR
        - 연산자 조합

    - 조건 적용
        ```
            -- city 테이블에서
            -- 이상,이하 (>=, <=), 초과,미만 (>, <)
            -- 인구수가 5,000,000 이상(>=)이 되는 
            -- 도시를 추출(조회)하시오 -> 모든 컬럼
            -- 테이블명.컬럼명 자동완성됨 -> tool에서 제공
            SELECT *
            FROM city
            WHERE city.Population >= 5000000;
            -- (24r x 5c)

            -- 위의 쿼리문 기반으로 조건 변경
            -- 인구수가 5백만 이상이고(AND),  6백만 이하인 
            -- 도시의 모든 데이터 조회
            SELECT *
            FROM city
            WHERE  city.Population >= 5000000 
                AND city.Population <= 6000000;

            -- 인구수가 5598953이 아닌(!=, <>) 도시의 모든 개수를 구하시오
            -- 개수는 count(*) 함수 사용, 별칭 부여 cnt
            SELECT COUNT(*) AS cnt
            FROM city
            WHERE Population != 5598953;

            SELECT COUNT(*) AS cnt
            FROM city
            WHERE Population <> 5598953;
            -- 전체는 4,079개, 조건부여 4,078개            

            -- city 테이블에서
            -- 국가코드(CountryCode)가 KOR 혹은(OR) USA인 데이터를
            -- 모두 가져오시오
            SELECT *
            FROM city
            WHERE city.CountryCode='KOR' OR CountryCode='USA';

            -- 한국의 도시들중 AND 인구수가 백만이상인 도시 데이터만
            -- 모두 조회하시오
            SELECT *
            FROM city
            WHERE city.CountryCode='KOR' AND city.Population>=1000000;
            -- 0.016초

            SELECT *
            FROM city
            WHERE city.Population>=1000000 AND city.CountryCode='KOR';
            -- 0.000초

            -- 조건식의 배치 순서에 따라 처리 속도가 다름!!
        ```


- SELECT ~ FROM ~ WHERE ~ BETWEEN
    - 조건식에 BETWEEN를 사용
    - 조건을 부여하는 컬럼의 데이터 타입이 수치형(or 연속형)일때 사용가능
        - 값과 값사이가 무한대 : 통상 연속형의 특징을 가짐
        - 컬럼명 BETWEEN a AND b
            - a <= 컬럼값 <= b
        ```
            -- city 테이블에서
            -- 모든데이터를 가져온다
            -- 단, 인구수가 5백만이상, 6백만 이하인 도시만 해당된다
            -- 5000000 <= 인구수 <= 6000000
            SELECT *
            FROM city
            WHERE city.Population BETWEEN 5000000 AND 6000000;
        ```

- SELECT ~ FROM ~ WHERE ~ IN
    - 대상 컬럼의 데이터가 명목형(범위 부여 불가능) 데이터 대상
    - 직접 나열한 대상만 조건이 되는 방식
    ```
        -- 한국(KOR), 미국(USA), 일본(JPN), 프랑스(FRA) 도시를대상
        -- 모든 데이터 총 수를 구하시오, 별칭은 cnt
        SELECT COUNT(*) AS cnt
        FROM city
        WHERE city.CountryCode IN ('KOR','USA','JPN','FRA');
        -- 632


        -- 한국(KOR), 미국(USA), 일본(JPN), 프랑스(FRA) 도시를대상
        -- 이중 인구수가 6백만 이상(>=)인 도시 => AND로 연결
        -- 도시명, 인구수만 출력하시오
        SELECT `NAME`, Population
        FROM city
        WHERE  city.CountryCode IN ('KOR','USA','JPN','FRA')
            AND city.Population >= 6000000;
        -- 3

    ```

- sub query
    - 서브 쿼리
    - 쿼리문(결과셋) 안에 쿼리문(결과셋)이 존재
        - 대상
            - 특정 테이블이 대상이 아니라, 테이블의 조회가 결과가 일종의 대상이 됨
        - 조건
            - 테이블의 조회결과가 조건에 사용된다

    ```
        -- 프랑스에 존재하는 모든 도시 정보를 출력하시오
        -- 조건- 국가 코드를 모른다!!
        -- paris라는 도시명은 알고 있다!!
        -- 해결 => paris를 이용하여 프랑스 국가코드를 획득 
        --      => 위의 결과를 이용하여 <-'프랑스' <-'모든 도시 정보 획득'
        -- (서브 쿼리->결과값 1개인가(where) n개 인가(from)?)
        SELECT *
        FROM city
        WHERE city.CountryCode=( 	SELECT CountryCode
                                            FROM city
                                            WHERE `NAME`='paris'
                                        );
        -- 서브쿼리 결과 FRA -> 1개의 결과

        -- 동일 요구사항, 단 District 컬럼값에 'New York'를 이용하여 구성
        -- 주 정보를 이용하여 국가코드 획득 -> 해당국가의 모든 도시 정보 획득
        SELECT *
        FROM city
        WHERE city.CountryCode=( 	SELECT CountryCode
                                            FROM city
                                            WHERE District='New York'
                                        );
        -- 오류발생, 1개의 일치된 값을 요구하는 조건문에 N개의 값을 대입
        -- 해결 : 서브쿼리 조정-> 결과가 1개만 나오게 처리 (의도는 아님)
        -- 다른 방식 : ANY, SOME을 응용하여 처리가능

        -- 서브쿼리를 FROM에 사용 -> n개의 결과셋을 대상으로 진행
        -- 통상 별칭 부여 -> 결과셋의 이름부여
        -- 컬럼을 나열할때 이름.컬럼명 표현하여 출처를 명확하게 명시
        -- 최종 컬럼명 별칭.컬럼명
        SELECT A.*
        FROM (SELECT CountryCode, `NAME` AS city_nm
                FROM city
                WHERE District='New York') AS A
    ```
    - 대부분의 요구사항들 대상, 디테일한 sql을 모른다면 => 서브쿼리로 모두 해결 가능하다!!


- ANY, SOME
    - 서브쿼리의 결과가 여러개가 나왔다, 조건식에서 사용하고 싶다
    - 한가지만 만족하면 사용가능하게 처리

    ```
        -- 뉴욕`주`인 데이터 대상으로 인구를 구한다
        -- 해당 인구보다 크기만 하면, 특정 대상이 되어, 
        -- 모든 도시 정보를 출력한다

        -- 모든 데이터들중에 뉴욕`주`에 해당되는 인구수보다 크면 조회된다
        SELECT *
        FROM city
        WHERE Population > ( SELECT Population
                                    FROM city
                                    WHERE District='New York'
                                );

        -- ANY 적용
        SELECT *
        FROM city
        WHERE Population > ANY ( SELECT Population
                                    FROM city
                                    WHERE District='New York'
                                );
        -- 3782

        SELECT *
        FROM city
        WHERE Population > SOME ( SELECT Population
                                    FROM city
                                    WHERE District='New York'
                                );
        -- 3782
        -- 결론:뉴욕주의 가장 작인 인구수를 가진 도시보다 크기만 하면 모두 대상이됨


        -- IN 하고 같은 결과물
        SELECT *
        FROM city
        WHERE Population = ANY ( SELECT Population
                                    FROM city
                                    WHERE District='New York'
                                );
    ```

- ALL
    - 서브 쿼리의 모든 결과를 만족하면 조회된다
    ```
        -- ALL
        -- 서브 쿼리의 결과 셋과 다 비교하여 모두 만족할때 대상이 됨
        -- 가장 큰값보다 크면 모두 해당됨!!
        SELECT *
        FROM city
        WHERE Population > ALL ( SELECT Population
                                    FROM city
                                    WHERE District='New York'
                                );
    ```

- ORDER BY 컬럼명|조건 ASC|DESC
    - 조회 결과를 특정 조건에 맞게 `정렬`
    - 기본값 
        - ASC (오름차순), 생략 가능함
    ```
        -- order by
        -- city 테이블 대상
        -- 모든 정보(데이터)를
        -- `인구`순으로 오름차순(작은값 -> 큰값)  정렬한다
        SELECT *
        FROM city
        ORDER BY  city.Population ;

        SELECT *
        FROM city
        ORDER BY  city.Population ASC;

        -- 내림차순 (큰값 -> 작은값)
        SELECT *
        FROM city
        ORDER BY  city.Population DESC;


        -- 정렬 조합 -> ,기준 열거
        -- 위와 동일한 데이터를 요청
        -- 인구는 내림차순, 국가코드는 오름차순 정렬
        -- 인구는 내람차순-> 동일수치 -> 국가코드는 오름차순 정렬
        SELECT *
        FROM city
        ORDER BY  city.Population DESC, city.CountryCode ASC;

        -- 순서 변경하여 각각 정렬되었는지 체크
        -- 명목(혹은 범주)형 데이터는 먼저 배체, 연속형(혹은 수치형) 차후 배치
        SELECT *
        FROM city
        ORDER BY  city.CountryCode ASC, city.Population DESC;


        -- 실습
        -- city 테이블 대상
        -- 한국 데이터만 대상, 도시명 오름차순, 인구 내림차순 정렬하시오
        -- 모든 데이터를 보여준다
        SELECT *
        FROM city AS c
        WHERE c.CountryCode='KOR'
        ORDER BY  c.`Name` ASC, c.Population DESC;
        -- 70개 데이터, 중요도에 따라 인구 혹은 도시명을 먼저 노출!!

        -- country 테이블 대상
        -- 국가 면적 순으로 정렬 (내림차순)
        -- 국가명, 면적만 출력하시오
        SELECT co.`Name`, co.SurfaceArea
        FROM country AS co
        ORDER BY co.SurfaceArea DESC;

    ```

- select ~ distinct ~ from 
    - 중복제거
    - (*)키워드
        - distinct 컬럼명|함수|...
    - 집계
        - 통계를 위해서 데이터를 뭉치는 과정에 중복이 제거되는 것뿐
        - 성격이 다름
    
    ```
        -- 중복 데이터 제거
        -- city 테이블 대상
        -- 국가코드의 중복 제거하여, 유니크한 국가코드 결과셋을 구하시오
        -- 유니크한 국가코드만 출력!!
        SELECT DISTINCT city.CountryCode
        FROM city;
        -- 232개 코드 결과셋
    ```

- LIMIT
    - 결과셋의 개수를 제한
    - 형식
        - limit n    : 처음(상위)부터 n개 까지만 대상 제한
        - limit n, m : n ~ m까지 대상 제한
    - 게시판의 페이징과 연관을 맷음, 필요한 만큼만 가져온다 => 성능에 영향향
    ```
        -- country 테이블에서
        -- 국가 면적 순으로 정렬 (내림차순) 결과물에서
        -- 상위 10(탑 10)개만 출력
        -- 국가명, 면적만 출력하시오
        SELECT co.`Name`, co.SurfaceArea
        FROM country AS co
        ORDER BY co.SurfaceArea desc
        LIMIT 10;
        -- (10, 2)

        -- 페이징 기능 삽입 (원하는 범위만 획득)
        SELECT co.`Name`, co.SurfaceArea
        FROM country AS co
        ORDER BY co.SurfaceArea DESC
        -- LIMIT 0, 10; -- 1page
        LIMIT 10, 10; -- 2page
        -- 페이지값 에 따른 제한값은 
        -- 페이지번호 pno, 페이지수 N
        -- limit (pno-1)*N, N
    ```

- GROUP BY
    - 집계 -> 보이지 않는 데이터가 탐색된다, 서열/종속=>직급,명령체계등도 체크...
    - 통상적 집계(혹은 통계) 함수들과 같이 사용
        - SUM()
        - AVG()
        - MIN()
        - MAX()
        - COUNT()
        - COUNT( DISTINCT )
        - STDEV()
        - VARIANCE()
    - 해당 함수들은 별칭 부여!!
    - 집계의 대상의 되는 컬럼 사용가능함 -> 중복제거가 되고 유니크해짐 -> 표현가능함
        - 국가 코드 기준 집계
            - 국가코드별 도시의 면적평균을 구하시오
            - KOR, 면적평균
            - USA, ...
            - ...
    - 집계의 대상이 아닌 컬럼을 출력 X
    ```
        -- 집계
        -- city 테이블 대상
        -- 같은 국가 코드를 가진 데이터간 집계
        -- GROUP BY CountryCode
        -- 같은 그룹내에서 인구수가 가장 작은 값을 출력, 별칭 min_popu
        -- 출력값은 국가코드, 최소인구수(도시별)
        -- 최소인구수 기준오름차순 정렬
        -- 탑 10 데이터만 획득

        -- 일반적인 요구사항
        -- 국가별 도시가 여러개 존재하는데, 
        -- 국가별 가장 적은 인구수를 가진
        -- 도시의 인구 및 국가코드를 출력하시오
        -- 오름차순, 인구수는 min_popu, 상위 10개만 출력
        SELECT c.CountryCode, MIN(c.Population) AS min_popu
        FROM city AS c
        GROUP BY c.CountryCode
        ORDER BY min_popu ASC
        LIMIT 10;

        -- 국가코드, 국가별로 존재하는 도시들의 평균값 출력
        -- 출력항목, 국가코드, 평균인구수(avg_popu)
        -- 평균 인구 기준 내림차순 정렬
        -- 4번째에서(0, 1, 2, (*)3) 10개만 출력
        SELECT c.CountryCode, AVG(c.Population) AS avg_popu
        FROM city AS c
        GROUP BY c.CountryCode
        ORDER BY avg_popu desc
        LIMIT 3, 10;


        -- 오류 발생 -> name은 집계의 대상도 아니고, 짒계처리된 내용 x
        -- 출력 결과에 사용 불가!!
        SELECT c.CountryCode, AVG(c.Population) AS avg_popu, c.`Name`
        FROM city AS c
        GROUP BY c.CountryCode

    ```

- HAVING
    - 조건 부여
        - where : 최초 결과셋에 대한 조건
        - HAVING : 최초, (*)집계 이후 데이터에 대한 조건
    ```
        -- having 

        -- 재료 : 국가별로 가장 큰 인구수를 가진 도시의 인구수와, 국가코드
        -- 조회하여 출력, 인구별로 내림차순 정렬
        SELECT c.CountryCode, MAX(c.Population) AS max_popu
        FROM city AS c
        GROUP BY c.CountryCode
        ORDER BY max_popu DESC;

        -- 위의 결과 집합에서 인구수가 9000000이상(>=)인 정보만 출력하시오
        -- 서브쿼리로 해결 가능함!!
        -- 오후 학습 정리시 시도
        SELECT c.CountryCode, MAX(c.Population) AS max_popu
        FROM city AS c
        -- GROUP BY는 from을 대상으로 집계
        GROUP BY c.CountryCode     
        -- GROUP BY를 보고(1차 가공된 데이터)  조건 처리
        HAVING max_popu >= 9000000 
        ORDER BY max_popu DESC;

        -- country 테이블에서
        -- 대륙별(Contin...)로 집계, 면적 평균 획득 avg_surf
        -- 대륙별값, 면적 평균 출력
        -- 면적 기준 내림차순 정렬
        -- 면적 평균이 1000000 이상인 데이터만 추출
        SELECT co.Continent, AVG(co.SurfaceArea) AS avg_surf
        FROM country AS co
        GROUP BY co.Continent
        HAVING avg_surf > 1000000
        ORDER BY avg_surf DESC;


        -- 위까지 완료된 분들은 면적이 100,000 이하인 국가는 제외하고
        -- 동일하게 진행
        SELECT co.Continent, AVG(co.SurfaceArea) AS avg_surf
        FROM country AS co
        -- country를 대상으로한 1차 조건
        WHERE co.SurfaceArea > 100000
        GROUP BY co.Continent
        -- 집계된 데이터를  대상으로한 2차 조건
        HAVING avg_surf > 1000000
        ORDER BY avg_surf DESC;

    ```

- ROLLUP
    - GROUP BY 와 같이 사용됨
        - GROUP BY ~ with ROLLUP
        - 집계간 중간 합계를 표현, 
    - 중간 집계
    ```
        -- 중간집계
        -- city 테이블에서
        -- 국가별, 도시별 인구수 집계
        -- 집계된 데이터별 중간합계값(ROLLUP)을 데이터로 추가
        -- 조건은 where도 관계 없음
        SELECT c.CountryCode, c.`Name`, SUM(c.Population) AS sum_popu
        FROM city AS c
        -- 도시명은 중복된 데이터가 없다-> 결과셋에 포함시키기 위해
        -- 집계에 포함시킴
        -- 결과셋의 마지막에 중간집계값 포함되어 있음
        GROUP BY c.CountryCode, c.`Name` with  ROLLUP
        -- HAVING c.CountryCode='KOR';

        -- 실습 : 오후학습 정리시 sum_popu값을 내림차순 기준으로 정렬
    ```

- 여기까지 1개 테이블 대상으로 DQL 진행 

- 1개 이상 테이블 대상 DQL
    - JOIN
    - UNION, UNION ALL

- JOIN
    - https://firebasestorage.googleapis.com/v0/b/repo-27c12.appspot.com/o/sql%2Fsql_join.png?alt=media&token=9779d791-580e-44dc-8e0f-05d17a58148b

    - 조인의 종류는 DB vender 별로 일부 상이하다!!
        - 지원하지 않는 파트도 존재
    - 다른 테이블의 레코드와 조합(결합)하여, 새로운 결과셋(집합)을 생성하는 과정

    ```
        -- 기본 조인
        -- 2(n)개의 테이블을 결합하여 1개의 결과셋 획득
        -- 왼쪽 테이블과 오른쪽 테이블 고려
        -- 왼쪽에 city 배치, 오른족 country <= 가정
        -- 조건 city의 CountryCode 와 country 의 Code가 일치하는 
        -- 모든 레코드만 추출
        SELECT *
        -- 왼쪽 테이블
        FROM city
        -- 오른쪽에 어떤 테이블과 결합 묘사
        JOIN country
        -- 결합의 조건
        ON city.CountryCode = country.`Code`;
        -- (4079, 20)
        -- 결과집합은 왼쪽 테이블의 데이터수보다 작거나 같다!! (특징)
        -- 위의 *의 문제점 : 중복 데이터 등장, 컬럼명 중복 가능성 존재

        -- 개선 -> 테이블명 별칭 처리 필요 -> 간결, 명확하게
        SELECT *
        FROM city AS A
        JOIN country AS B
        ON A.CountryCode = B.`Code`;


        -- 개선 -> *이 아닌, 필요한 컬럼만 사용!!
        -- 대륙, 국가, 도시, 면적, 인구 순서로 결과셋 획득
        SELECT 
            B.Continent, 	
            A.CountryCode, 
            A.`Name`, 
            B.SurfaceArea, 
            A.Population
        FROM city AS A
        JOIN country AS B
        ON A.CountryCode = B.`Code`;

        -- countrylanguage 테이블 대상
        -- 한국 기준 사용하는 언어 데이터 모두 획득
        DESC countrylanguage;

        SELECT *
        FROM countrylanguage AS cl
        WHERE cl.CountryCode='KOR';

        SELECT *
        FROM countrylanguage AS cl
        WHERE cl.CountryCode='JPN';


        -- 3개 테이블 조인
        -- city, country, countrylanguage 순으로 조인
        -- 국가 코드가 일치하는 조건으로 조인
        -- 모든 데이터 조회
        SELECT *
        FROM city AS A
        JOIN country AS B			  ON A.CountryCode = B.`Code`
        JOIN countrylanguage AS C ON A.CountryCode = C.CountryCode;
        -- (30670, 24)
        -- city 데이터 4079개 였는데, countrylanguage가 국가별로
        -- 사용언어가 여러개로 조회되는 관계로 => city 데이터수보다
        -- 많게 결과가 나왔음!! -> 조인하는 테이블의 상황에 따라 상이해짐


        -- car_order를 중심으로
        -- car_orderdetail, car_product, car_store, car_member '조인'
        -- 각 테이블별로 필요한 컬럼만 구성하여 => 구매 내역 데이터마트구성 => View
        -- View -> 각종 데이터분석(파이썬, DB쿼리,..) ( 관리자쪽에서 진행)
        -- 조인 기준 -> 구성 다음시간 체크
        -- 중요: car_order의 데이터개수를 초과하면 않됨, 동일 개수 유지 : left join
        SELECT A.*
            ,B.prod_cd
            ,B.quantity
            ,C.price
            -- , 단가 * 총량 => 판매금액
            ,C.brand
            ,C.model
            ,D.store_addr
            ,E.gender
            ,E.age
            ,E.addr
            ,E.join_date
        FROM car_order AS A
        left JOIN car_orderdetail 	AS B ON A.order_no = B.order_no
        left JOIN car_product 		AS C ON B.prod_cd  = C.prod_cd
        left JOIN car_store 			AS D ON A.store_cd = D.store_cd
        left JOIN car_member 		AS E ON A.mem_no   = E.mem_no
        ;
        -- (4176, 14) 
        -- 이 데이터를 기반으로 추가 분석 쿼리 -> 마케팅 계획 수립!!

        -- 기본 join == inner join => 교집합 중심
        SELECT COUNT(*)
        FROM city AS A
        JOIN country AS B
        ON A.CountryCode = B.`Code`;
        -- 4079

        SELECT COUNT(*)
        FROM city AS A
        inner JOIN country AS B
        ON A.CountryCode = B.`Code`;
        -- 4079

        -- left join,왼쪽 테이블의 데이터는 유지!! (마스터 데이터 배치)
        SELECT COUNT(*)
        FROM city AS A
        left JOIN country AS B
        ON A.CountryCode = B.`Code`;
        -- 4079

        SELECT COUNT(*) FROM city;
        -- 4079


        -- 오른쪽 조인 -> 개수는 다른 결과로 나왔음(예측 잘 않됨)
        -- right join
        SELECT COUNT(*)
        FROM city AS A
        right JOIN country AS B
        ON A.CountryCode = B.`Code`;
        -- 4086, city와 숫자와 다름
        SELECT COUNT(*) FROM country;

        -- full join -> x
        -- 대체제 : union or union all
        SELECT *
        FROM city AS A
        left JOIN country AS B
        ON A.CountryCode = B.`Code`

        union

        SELECT *
        FROM city AS A
        right JOIN country AS B
        ON A.CountryCode = B.`Code`;
        -- (4086, 20)

    ```

- union, union all
    - 합집합 담당
    - 조건 : 컬럼의 이름/개수가 동일해야함!!
    - 차이점
        - union : 중복제거
        - union all : 모든 데이터가 포함(중복 제거 x)
    
    ```
        -- union, union all
        -- A 집합
        -- city 테이블 대상
        -- 한국만 대상, 인구수 9000000 이상인 데이터에서
        -- 도시명, 인구수 조회
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=9000000;
        -- 1

        -- B 집합
        -- city 테이블 대상
        -- 한국만 대상, 인구수 800000 이상인 데이터에서
        -- 도시명, 인구수 조회
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=800000;
        -- 8


        -- A 집합 union b 집합
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=9000000
        UNION
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=800000;
        -- 8개 결과

        -- A 집합 union all b 집합
        -- 중복 제거 x
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=9000000
        UNION all
        SELECT c.`Name`, c.Population
        FROM city AS c
        WHERE c.CountryCode='KOR' AND c.Population>=800000;

    ```


- 함수
    - 연산자
    - 문자열
        - LENGHT()
            - 문자열 길이 반환
            ```
                -- 함수 -  문자열
                SELECT LENGTH('hi');
                SELECT LENGTH('HI');
                SELECT LENGTH('가나다'); -- 한글은 3byte 길이
                SELECT LENGTH('12');
                SELECT LENGTH('!@');

                -- city 테이블에서
                -- 도시명, 도시명의 길이 출력 -> 별칭으로  size 
                -- 도시명의 길이순으로 내림차순 정렬
                -- 상위 5개만 출력
                SELECT c.`Name`, LENGTH(c.`Name`) AS size
                FROM city AS c
                ORDER BY size DESC
                LIMIT 5;
            ```
        - CONCAT()
            - 문자열 결합, 요구사항 중에 여러 칼럼값을 합쳐서 제공되길 원할 수 있음
            - 단, 1개라도 NULL이 존재한다면! -> NULL이 됨

            ```
            -- 기본 결합
            SELECT CONCAT('hello','-','world');

            -- null 이 존재한다면 모두 null
            SELECT CONCAT('hello',NULL,'world');

            -- 실제 테이블에서 적용
            -- 형식: 도시명-인구수 결과셋으로 나오는 결과 요청
            -- 컬럼명 spec 지정
            SELECT concat(c.`name`,'-', c.Population) AS spec
            FROM city2 AS c;
            ```

        - LOCATE()
            - 문자열 내에 특정 문자열의 처음 등장하는 위치를 반환
            - 시작 위치는 1부터 출발
            - 0: 없다
            ```
                        
            -- 위치 체크
            SELECT LOCATE('w','world');
            -- 앞에는 찾으려는 문자열, 뒤에는 대상 문자열
            -- w는 맨 앞이니까 1로 나오는 것을 알 수 있음

            SELECT LOCATE('or','world');
            -- 이건 2

            SELECT LOCATE('z','world');
            -- 찾을 수 없으면 0으로 나온다

            -- 특정 게시물, 말뭉치(텍스트 덩어리)에서 검색어가 존재하는지 체크
            -- city 테이블에서
            -- 도시이름이 se로 시작하는 모든 도시들을 찾아서
            -- 위치값이 1<=위치값<4 : 1,2,3만 해당
            -- 해당 데이터들은 모두 오름차순 정렬
            -- 출력값 도시명, 위치값(loc)

            -- 서브 쿼리 사용 -> 결과셋을 이용하여 요구사항 구현
            SELECT c.`Name`, LOCATE('se',c.`Name`) AS loc
            FROM city2 AS c
            WHERE LOCATE('se',c.`Name`) BETWEEN 1 and 2
            ORDER BY loc;
            -- between은 문자 between 조건 and 조건 형식으로 이상 이하를 묶은거

            SELECT c.`Name`, LOCATE('se',c.`Name`) AS loc
            FROM city2 AS c
            having loc BETWEEN 1 and 2
            ORDER BY loc;
            -- 셀렉트에서 선언한 별명을 변수로 이용하고 싶으면 having을 사용
            -- where는 셀렉보다 먼저 작동해서 셀렉에서 선언한거 사용 불가능
            ```
        - LEFT(), RIGHT()
            - 왼쪽 기준 자르기
            - 오른쪽 기준 자르기
            - 데이터의 특정 프만 추출할 때 사용
            ```
                -- 왼쪽 기준 3개
                SELECT LEFT('hello world', 3);

                -- 오른쪽 기준 3개
                SELECT RIGHT('hello world', 3);

                -- 테이블에 적용
                -- 문자열, 수치형 -> 모두OK
                SELECT
                LEFT(c.`Name`, 2), c.`Name`, RIGHT(c.`Name`, 3), -- 문자열
                LEFT(c.Population, 2), c.Population, RIGHT(c.Population, 3) -- 수치형
                FROM city2 AS c;
            ```
        - LOWER(), UPPER()
            -- 소문자, 대문자 처리 -> 일괄 변경
            ```
                -- 소문자, 대문자 변환
                -- 오직 알파벳문자만 대상 처리됨
                SELECT LOWER('abAB12가나!@');
                SELECT UPPER('abAB12가나!@');

                -- 테이블 컬럼에서 적용
                SELECT NAME, LOWER(NAME), UPPER(NAME)
                FROM city;
            ```

        - REPLACE()
            - 특정 문자열 대체
            ```
                -- 원본 데이터 기준 특정 문자열을 다른 문자열로 교체
                SELECT REPLACE('abAB12가나!@','bAB','-비ab-');

                -- 수치형 적용
                -- 1780000 => 178****
                SELECT c.Population, REPLACE(c.Population, '0', '*')
                FROM city2 AS c
            ```
        - TRIM()
            - 공백(노이즈) 제거
                - 앞, 뒤, 내부
            - 지정자
                - BOTH
                    - 양쪽, 공백 이외의 특정 문자 제거
                - LEADING
                    - 앞쪽 공백 이외의 특정 문자 제거
                - TRAILING
                    - 뒤쪽 공백 이외의 특정 문자 제거
            ```
                -- 공백제거, 특정 문자 제거
                SELECT TRIM('      ab    cd     ') -- 좌우 공백 제거 ok
                , TRIM('   ab')
                , TRIM('ab   ')
                -- 대상 문자열에서 @제거
                -- 시작문자, 끝문자 중요 - 연속성 중요
                , TRIM(LEADING '@' FROM '@@@ A @@@')
                , TRIM(TRAILING '@' FROM '@@@ A @@@')
                , TRIM(BOTH '@' FROM '@@@ A @@@')
                , TRIM(BOTH '@' FROM '[@@@ A @@@]');

                -- 테이블 적용
                -- 대소문자 구분
                SELECT c.`Name`, TRIM(LEADING 'S' FROM c.`Name`)
                FROM city2 AS c
                WHERE c.CountryCode = 'KOR';
            ```
        - FORMAT()
            - 포멧팅, 형식을 갖춰준다!!
            - 수치형 => n자리마다 , 삽입
            ```
                -- 포멧
                -- format (수치형 데이터, 소수부 자리수 지정)
                -- 정수부는 무조건 3자리 단위로 , 삽입
                -- 소수부는 자리수에 맞춰서 남김 -> 반올림 처리
                SELECT FORMAT(32324342424424243.2455432432432,3), -- xxx.246
                        FORMAT(32324342424424243.2432432432432,4);

                -- 테이블 적용
                -- 1780000 => 1,780,000 문자열로 처리해서 표현
                SELECT c.Population, FORMAT(c.Population,0)
                FROM city2 AS c
            ```
        - SUBSTRING()
            - 타겟 문자열의 특정 위치에서 특정 길이만큼 추출
            ```
                -- 문자열 자르기, left: 왼쪽기준, right: 오른쪽기준,
                -- substring(): 원하는 위치에서 자르기
                -- 012 가 아니라 123 번째로 위치를 봐야 한다
                -- 위치 정보는 1부터 출발
                -- substring(타겟 문자열, 시작 위치, 길이)
                SELECT SUBSTRING('ABCDEFG', 2, 3);

                -- 포멧팅된 로그 잘라서 추출, 문자열 분할처리 유용
            ```
    - 수학
        - 수치형 데이터 => 내림, 올림, 반올림, 버림, 수학계산(제곱근, 자연로그, ...)
                        => 통계 (표준편차, 분산, 합산, 최소, 최대, 평균)
                        => 절대값, 난수, ...
        - floor(), cell(), round()
        - 내림, 올림, 반올림
        ```
            -- 수학 함수
            -- 부동소수 데이터를 정수로 변환 -> 정보손실 동반함
            SELECT
                FLOOR(3.95), -- 내림
                CEIL(3.95), -- 올림
                CEIL(3.11), -- 올림
                ROUND(3.5),	-- 반올림
                ROUND(3.4);	-- 반올림
                
            SELECT
                CEILING(1.56) -- 올림과 유사, 큰값 쪽으로 지향하는 의미
        ```
        - 기타 수학함수
            - 특수 케이스, 수학적 연산 필요 시 사용
            ```
                -- 수학 계산
                -- 참고
                -- 데이터가 특정구간에 밀집해 있다면 => 데이터 흐트러 놔야 한다
                -- LOG()를 이용하여 데이터를 분산시켜서 분석 => 결론 => exp() 원복
                SELECT
                    SQRT(4), -- 루트 처리
                    POW(2,3), -- 2*2*2 => 2의 3제곱근, 거급제곱
                    EXP(3), -- e^3, e의 3 거듭제곱
                    LOG( EXP(3) ) ;  -- EXP() <-> 역함수 <-> LOG()
                    
                -- 삼각함수
                -- 거리계산시 사용 !! 데이터가 GPS존재한다면
                -- 두 지점의 직선거리 계산시 유용
                SELECT PI(), 
                    SIN( PI()/2 ),
                    COS( PI() ),
                    TAN( PI()/4 );
                    

                -- 절대값, 난수
                -- 0.0 <= RAND() <= 1.0
                SELECT
                    ABS(-1), -- 절대값 => 양으로 표현!!
                    ABS(1),
                    RAND();

                -- 난수 응용, 0<= 난수 <=10 구현하시오 -> 정수!!
                -- 0.0*10 <= RAND()*10 <= 1.0*10
                -- 0.0 <= RAND() <= 10.0
                -- 반올림으로 임의로 정수  처리
                SELECT ROUND(RAND()*10, 0);
                -- 임의값에 의해서 이벤트, 추첨 
                -- => 주의 (확률 세팅) => 프로그램에서 해결
                -- 간단하게 난수로 값들 조정할때 사용

                -- 표준편차, 분산()
                -- 데이터가 얼마나 서로 떨어져 있는가?
                -- 데이터 분포를 파악, 설명하는 용도 => 분석분야
                SELECT STD(city.Population)
                FROM city
                
                -- car_product 테이블
                -- price 컬럼에서 ,를 제거(replace)하여 price_int 라는 컬럼 생성
                -- 모든컬럼, price_int 이렇게 출력되게 구성
                SELECT
                    COUNT( price_int ) AS 주문수,
                    SUM( price_int ) AS 주문금액합산,
                    AVG( price_int ) AS 주문금액평균,
                    STD( price_int ) AS 주문금액표준편차
                FROM (
                    SELECT 
                        *, 
                        REPLACE( price, ',', '') AS price_int
                    FROM car_product
                ) AS A
                -- ( 48, 6 )
            ```
    - 시간
        - NOW(), CURDATE(), CURTIME()
            - 현재 시간, YYYYMMDD HHMMSS
            ```
                -- yyyymmdd hhmmss 정보 각각 획득
                SELECT NOW(), CURDATE(), CURTIME();

                -- 시간값 자를수 있는가?
                SELECT left(CURDATE(), 4);
                -- 활용용도 -> 회원(쇼핑몰) -> 가입일, 수정일, 탈퇴일, 구매시간
                -- 고객 분류 -> 마케팅, 서비스등제공, 고객등급결정 
                -- 가입월일 계산 : (현재시간 - 가입시간) => 월(주,년)로 환산

                -- 세부적인 시간 정보
                SELECT NOW(),
                    YEAR(   NOW() ), 
                    DATE(   NOW() ),
                    MONTH(  NOW() ),
                    DAY(    NOW() ),
                    HOUR(   NOW() ),
                    MINUTE( NOW() ),
                    SECOND( NOW() );
                    
                -- 기타 정보 -> 월의 이름, 요일의 이름
                -- 시간 -> 요일 -> 주간 매출 분석 -> 어떤 요일에 ...
                SELECT NOW(), 
                    MONTHNAME( NOW() ),
                    DAYNAME( NOW() );	

                -- 기타 정보, 주간, 월간, 년간 단위 현재 시간의 위치
                SELECT NOW(),
                    DAYOFWEEK(  NOW() ),
                    DAYOFMONTH( NOW() ),
                    DAYOFYEAR(  NOW() );
                    
                -- 포멧 -> 시간의 형식을 자유롭게 구성!!
                SELECT DATE_FORMAT( NOW(), '%D %y %s %d %m %j' );
                -- 일 : %D, %d
                -- 년 : %y
                -- 초 : %s
                -- 월 : %m
                -- DAYOFYEAR : %j

                -- 가장 많이 사용!! -> 시간 차이 계산!!
                -- 시간차이 => ex) 가입한지 몇일 되었지?
                -- ex) 2024/12/3 - 대통령 취임일  = 1000
                -- DATEDIFF( 시간, 상대적으로 과거 ) => 양수로 나온다
                -- 양으로 표현 => ABS(), 무조건양수
                SELECT 
                    -- 만약 시간의 양(일수만 체크하고 싶다면) -> 무조건 양수
                    ABS(DATEDIFF( NOW(), '2024-12-01')), -- 과거시간
                    ABS(DATEDIFF( NOW(), '2024-12-20')), -- 미래시간
                    ABS(DATEDIFF( '2024-12-20' , NOW())); -- 미래시간
                    
                -- 시간 기입은 직접 가능 (형식 일치해야함)
                SELECT ABS(DATEDIFF( '2024-12-01', '2024-12-18'));

            ```
    - 기타 부가 기능
        - 형변환
            - 타입 변경
                - 디비에는 다양한 유형의 데이터를 담는 그릇(타입)이 존재
                - 그릇은 크기가 모두 다르다(담을 수 있는 양이 다름)
            - A 그릇에 담긴 데이터를 B 그릇으로 변경하는 행위
                - CAST() 함수, CONVERT()
                    - BINARY(), CHAR(), DATE(), DATETIME(), DECIAML()
                    - JSON(), NCHAR(), SIGNED(), TIME(), UNSIGNED()
                    ```
                        -- 형변환 함수
                        -- cast() 함수
                        -- 문자 -> 수치
                        -- UNSIGNED 부호가 없는 수치 => 양수
                        SELECT '123', CAST('123' AS UNSIGNED);

                        -- 문자|숫자 -> 날짜
                        SELECT CAST('20241218' AS DATE); -- 2024-12-18
                        SELECT CAST(20241218 AS DATE); -- 2024-12-18

                        -- 숫자 -> 문자
                        SELECT CONVERT(457398348, CHAR);
                    ```
                    - 변경이 가능한 데이터만 변경됨!
        - 일반
            - 함수, 키워드
            - raw 데이터에서 데이터를 범위(band)를 지정하여 해당되는 데이터에 특정 컬럼값 부여하는 방식
                - 고객: 기본 등급, 골드, 실버, VIP, VVIP 등급책정
                -   CASE
                    WHEN 조건 THEN '값1'
                    WHEN 조건 THEN '값1'
                    WHEN 조건 THEN '값1'
                    ...
                    ELSE '값4' END
                    as 컬럼병(별칭칭)
                    ```
                        -- 홍콩 면적을 기준으로 '홍콩보다 작은 면적', '홍콩보다 큰면적(>=)'
                        -- 대상 country 테이블 대상
                        -- 새로운 컬럼 sf_band
                        -- 출력값  code, name, 면적, sf_band 출력
                        -- 기존 데이터를 기반으로 새로운 컬럼 생성!! 
                        -- -> case -when ~ then ~ else ~ end

                        -- 홍콩의 국가코드는 HKG
                        SELECT
                            co.`Code`, co.`Name`, co.SurfaceArea,
                            
                            case
                            -- when 조건식 then '값1' 
                            -- when co.SurfaceArea < 홍콩의면적  then '홍콩보다 작은 면적' 
                            when co.SurfaceArea < (
                                -- 서브 쿼리를 조건식에 사용 -> 값이 1개 or 값이 n개(ANY,SOME, ALL)
                                SELECT SurfaceArea FROM country WHERE CODE='HKG'
                            )  then '홍콩보다 작은 면적' 
                            ELSE '홍콩보다 큰면적' END
                            AS sf_band
                            
                        FROM country AS co;

                        -- case when => 컬럼추가(파생변수) 
                        -- =>집계(group by) => 통계 => 시각화(대시보드)
                        SELECT
                            co.`Code`, co.`Name`, co.SurfaceArea,
                            case
                            when co.SurfaceArea < (
                                SELECT SurfaceArea FROM country WHERE CODE='HKG'
                            )  then '홍콩보다 작은 면적' 
                            ELSE '홍콩보다 큰면적' END
                            AS sf_band
                        FROM country AS co;

                        -- car_member 테이블 대상
                        -- age_band라는 컬럼 동적 추가
                        -- 해당 컬럼은 age 컬럼보고 판단
                        -- 20대미만(<20), 20대(20~29), 30대, 40대, 50대, 60대이상(60~)
                        -- 출력, car_member 모든 컬럼 + age_band 로 출력
                        -- 실습 5분 - 고객 데이터를 기반으로 연령대별로 분류(군집(그룹)해라)
                        SELECT *,
                            case
                            when cm.age < 20 then '20대미만' 
                            when cm.age BETWEEN 20 AND 29 then '20대' 
                            when cm.age BETWEEN 30 AND 39 then '30대' 
                            when cm.age BETWEEN 40 AND 49 then '40대' 
                            when cm.age BETWEEN 50 AND 59 then '50대'
                            ELSE '60대이상' END
                            AS age_band
                        FROM car_member AS cm;

                    ```
            - like 키워드 - 검색
                - WHERE에서 사용, 검색 시 사용
                - 특정 단어가 들어가 있는 모든 데이터를 가져오시오! => LIKE
                - where | having 컬럼명 like '%검색어' | '검색어%' | '%검색어%'
                -- '_pp%' => _는 자리수 표현
                    - 첫번째 글자는 아무문자든 존재, 두/세번째가 pp가 되는 문자열 찾아라
                ```
                    SELECT *
                    FROM (
                            SELECT *,
                                case
                                when co.age<20 then '20대 미만'
                                when co.age<30 then '20대'
                                when co.age<40 then '30대'
                                when co.age<50 then '40대'
                                when co.age<60 then '50대'
                                ELSE '60대 이상' end
                                AS age_band
                            FROM car_member AS co) AS A
                    -- WHERE A.age_band LIKE '20대%'; -- 20대로 시작하는 문자열
                    -- WHERE A.age_band LIKE '%이상'; -- 이상으로 끝나는 문자열
                    WHERE A.age_band LIKE '%이%'; -- 이 가 들어가있는 문자열
                ```

        - 랭킹
            - 랭킹 표현
            ```
            -- 랭킹
            -- 자동차 주문 날짜 <- 오름차순 정렬 후
            SELECT co.mem_no, co.order_date
                ,ROW_NUMBER()	OVER (ORDER BY co.order_date ASC) AS RANK1 -- 공동이 없음
                ,RANK()			OVER (ORDER BY co.order_date ASC) AS RANK2 -- 공동이 있으면 다음 등수는 밀림
                ,DENSE_RANK()	OVER (ORDER BY co.order_date ASC) AS RANK3 -- 공동 9등이어도 10등이 옴
            FROM car_order AS co

            ```

# DDL
    - Data Definition Language
    - 데이터 정의어
    - SQL
        - create    : 테이블 생성
        - alter     : 테이블 수정
        - index     : 테이블 색인(인덱스) 작성 -> 검색
        - drop      : 테이블/인덱스 삭제
        - view      : 가상 테이블 => 데이터 마트
    - 특징
        - 실행 즉시 반영됨
    
    - create table as select
        - 특정 테이블을 조회하여, 결과셋을 기반으로 동일한 테이블 생성
        ```
            -- DDL
            -- create table as select
            -- city 테이블과 동일한 구조와 데이터를 가진 테이블
            -- city_copy 만드시오
            CREATE TABLE city_copy
            AS SELECT * FROM city2;

            -- 카피된 테이블 확인
            SELECT COUNT(*) FROM city_copy;

            -- city_sub 테이블 생성
            -- city 테이블 기반
            -- 국가코드 한국, 미국, 일본만 대상
            CREATE TABLE city_sub
            AS (
                SELECT c.`Name`, c.CountryCode, c.Population
                FROM city2 AS c
                WHERE c.CountryCode IN ('KOR', 'USA', 'JPN'))


            SELECT *
            FROM city_sub
        ```

    - create database
        - 새로운 데이터베이스 생성
            - create database 데이터베이스명
        - 데이터베이스 삭제
            - drop database 데이터베이스명
        ```
            -- 데이터베이스
            -- 인코딩 utf8mb4_general_ci <= 한글 정상적 처리
            CREATE DATABASE A1;

            SHOW DATABASES;

            DROP DATABASE A1;
        ```
        - 요구사항 분석 -> ERD + 모델링(생략가능능)
        - 데이터베이스 생성 -> 테이블생성 -> 더미데이터 추가 -> 프로젝트 시작
        - SQL 준비

    - create table
        - 테이블 생성
        - GUI 진행
        ```
            -- 테이블 생성 코드 - GUI
            CREATE TABLE `guiusers` (
                `id` INT NOT NULL AUTO_INCREMENT COMMENT '회원고유번호',
                `uid` VARCHAR(32) NOT NULL COMMENT '회원고유아이디' COLLATE 'utf8mb4_general_ci',
                `upw` VARCHAR(256) NOT NULL COMMENT '비밀번호-암호화' COLLATE 'utf8mb4_general_ci',
                `age` TINYINT NULL DEFAULT NULL COMMENT '나이',
                `email` VARCHAR(128) NULL DEFAULT NULL COMMENT '이메일' COLLATE 'utf8mb4_general_ci',
                `regdate` TIMESTAMP NOT NULL DEFAULT (now()) COMMENT '가입일',
                
                
                PRIMARY KEY (`id`) USING BTREE,
                UNIQUE INDEX `uid_upw` (`uid`, `upw`) USING BTREE
            )
            COMMENT='회원 테이블'
            COLLATE='utf8mb4_general_ci'
            ENGINE=InnoDB
            ;



            -- 테이블 생성
            -- 직접 작성 or  자바코드에서 자동 생성(SQL 몰라도 가능함)
            -- 간단한 회원테이블
            CREATE TABLE users (
                id INT NOT NULL PRIMARY KEY,
                uid VARCHAR(32) NOT NULL,
                upw VARCHAR(256) NOT NULL,
                age INT 	NULL,
                email VARCHAR(32) NULL,
                regdate TIMESTAMP NOT null
            );
            SHOW TABLES;

            DESC users;
        ```
        - PK, FK 관계
            - 게시글 : 댓글 = 1:N
                - 댓글 -> 게시글 참조
                - 게시글 삭제 -> 댓글 모두 삭제 : 통상적 관리
            - 스프링부트에서 게시판 작성 체크!!
                - 문법, 관계 설정정
            - country(국가), city(도시)
                - 국가가 존재해야 city 존재함
                - 국가가 사라지면 city 사라짐
                - country : city = 1 : N <= 참조키로 연동
    - alter table add | modify | drop
        - 기존 테이블 추가|수정|삭제 !!
        - 데이터가 없다면 삭제 후 새로 생성!!
        - 데이터가 많이 존재하면 alter 추천
        ```
          -- alter table
            DESC users;

            -- 컬럼 추가
            ALTER TABLE users
            ADD col INT NULL; 

            DESC users;

            -- 컬럼 수정
            -- 유투브 -> 조회수 최대 5억뷰 -> 강남스타일 -> 오류발생 -> 타입 확장
            -- 이미 데이터가 대량으로 존재함 -> 타입을 수정하는등 수정 조치!!
            ALTER TABLE users
            MODIFY col VARCHAR(128); -- 타입 변경 처리

            DESC users;

            -- 컬럼 삭제
            -- 필요없는 컬럼 발생
            ALTER TABLE users
            DROP col;

            DESC users;
        ```
    - index
        - 목적
            - 빠른 검색!!
            - 다양한 알고리즘 적용
                - BTREE
                - ...
        - 장점
            - 검색 능력 향상
            - 정렬, 그룹화 성능 향샹
            - 고유한 제약 조건 간소화
        - 단점
            - 저장공간 소모, 캐싱등 메타 정보 저장
            - 데이터 갱신되면
                -> 인덱스 모두 업데이트(필요한만큼)
                -> 성능 저하를 가져올 수 있다
                    - 테이블 별로 업데이트가 빈번한지, 고정인지(변동없음) 체크
            - 관리 복잡하다!!
        - 종류
            - B-tree
                - 범위 쿼리, 정렬 데이터에 효과적인 방식
                - 주문 날짜, 사용자 아이디
            - hash
                - 정확히 일치된 내용을 찾을 때 효과적적
            - full text
                - 텍스트 검색에 효과적
                - 문서 내 키워드 검색
            - r-tree
                - GIS, 공간 데이터 검색
            - ...

    - (*)view
        - DQL -> 결과를 보관 -> 가상 테이블로 관리 -> 빠른 처리가 가능함
            - 데이터 마트!!
        - 특징 
            - 데이터베이스에 존재하는 가상 테이블
            - 실제 테이블처럼 행, 열 가지고 있지만 (구조만 존재), 데이터는 x
            - 데이터는 실제 테이블이 가지고 있음
            - 역할
                - 주로 조회용
                    - 특정 내용 -> 매번 조인 등 복잡한 쿼리로 결과를 획득
                    - 반복적인 내용 (변하지 않는) -> 유용(1회만 구축)
                    - 인덱스 x
                    - 자주 사용하는 쿼리문 결과 => view 구성 => 빠르게 사용가능
        - create view 뷰이름 as 데이터셋
            ```
                -- view 
                -- 뷰 생성
                -- city 테이블에서 한국 데이터만 가져와서 가상테이블 view로 생성
                -- 왜? 가정 한국 도시 데이터를 주로 자주 사용하더라!!
                -- 한국 도시 데이터를 가상 테이블로 생성 => 직접 사용
                CREATE VIEW city_view
                AS
                SELECT city.`Name`, city.Population
                FROM city
                WHERE city.CountryCode='KOR';

                -- city 테이블에서 한국 데이터만 가져와서 => view 사용
                -- SQL 단계가 축소됨
                SELECT *
                FROM city_view;

                -- city, country, countryLanguage 조인
                -- test_all_data.sql 에는 ERD 파일이 없음
                -- ERD 확인 -> 관계성 확인!!
                -- city <-> country <-> countryLanguage
                -- 조인 : 
                -- ERD 확인 : PK, FK 확인하여 조인 => ON ~ 
                -- ERD 부재시 : (동일한 컬럼|동일한 값) 이 존재하는가?
                -- 오직 한국에 대한 정보만 뷰로 생성
                -- where 국가코드='KOR'
                -- 컬럼 : 도시명, 면적, 인구수, 랭귀지
                -- select ~ 
                -- 뷰의 이름은 total_kor_view
                CREATE VIEW total_kor_view
                AS
                SELECT A.`Name`, B.SurfaceArea, A.Population, C.`Language`
                FROM city AS A
                JOIN country AS B ON A.CountryCode = B.`Code`
                JOIN countrylanguage AS C ON A.CountryCode = C.CountryCode
                WHERE A.CountryCode = 'KOR';

                SELECT COUNT(*) FROM total_kor_view;

                -- alter view
                -- view 수정
                -- city_view : 도시명, 인구수 : 교체전
                -- city_view : 국가코드, 인구수 : 교체후
                ALTER VIEW city_view
                AS SELECT countrycode, population FROM city;

                SELECT * FROM city_view;

                -- drop view
                -- view 삭제 -> 가상 테이블 삭제
                -- 원본 테이블 데이터 보전됨
                DROP VIEW city_view;
            ```
        - alter view
        - drop view
# DML
    - Data Mainpulation Language
        - 데이터 조작어
        - SQL
            - 번외
                - (*)SELECT, CREATE, (*)INSERT, (*)UPDATE, (*)DELETE : 가장 많이 사용용
                - CRUD <- 스프링부트 (백엔드 서버 구성 시)에서 디비 연동에 대한 기본 작업
                    - CREATE
                    - READ
                    - UPDATE
                    - DELETE
            - INSERT    : 데이터 추가
            - UPDATE    : 데이터 수정
                - 필수적으로 조건 부여 중요!! -> 누락되면 사고 위험이 있음 (관리개념, 개발 시)
            - DELETE    : 데이터 삭제
                - 필수적으로 조건 부여!!
                - 커밋이라는 단계를 거치지 않았다면, 롤백 가능함!!
            - TRUNCATE
                - 테이블의 데이터를 한번에 삭제 -> 최초 형태로 유지
                - 복구 불가
            - DROP
                - 테이블 삭제
        - INSERT
            - 데이터를 테이블에 입력
            ```
                -- 데이터 직접 입력
                -- users 테이블 대상
                INSERT INTO users
                -- 대상 컬럼 나열
                -- 생략 가능 : 1. 자동 삽입( ex)id )되는 컬럼 2. null 허용 컬럼
                ( uid, upw, age, email, regdate) 
                VALUES ( 'guest', '1234', '25', 'a@a.com', NOW());

                SELECT * FROM users;

                -- 중볷된 데이터가 존재 -> KEY 오류 발생
                -- 이미 가입된 아이디가 잇다? 아이디가 중복된다!!
                -- (*)가장 기본적 형태를 가장 많이 사용한다!!
                INSERT INTO users
                ( uid, upw, age, email, regdate) 
                VALUES ( 'guest', '1234', '25', 'a@a.com', NOW());

                -- uid에 대한 unique 처리가 않되 있어서 통과된다!! => 테이블 수정
                -- uid unique 인텍스 처리 필요!! => 오전학습 정리때 시도
                INSERT INTO users
                ( uid, upw, age, email, regdate) 
                VALUES ( 'guest', '12345', '25', 'a@a.com', NOW());

                -- 컬럼 파트는 생략 가능함 , 통째로 -> 데이터는 순서대로 배치해야함
                -- 가급적 풀버전으로 sql 구성 => 관리상 유리
                INSERT INTO users
                VALUES ( 4, 'guest1', '12345', '35', 'b@b.com', NOW());

                -- 멀티 데이터 밀어 넣기
                -- 1개 이상 데이터 넣기
                -- 데이터 파트를 , 구분하여 나열
                INSERT INTO users
                ( uid, upw, age, email, regdate) 
                VALUES 
                    ( 'guest5', '123450', '35', 'a1@a.com', NOW()),
                    ( 'guest6', '123451', '45', 'a2@a.com', NOW()),
                    ( 'guest7', '123452', '55', 'a3@a.com', NOW()) ;		


                SELECT * FROM users;

                -- 구조가 동일한 테이블이 있다면
                -- 오전 학습 정리시 users 테이블과 동일한 구조의 users_copy 생성후
                -- 아래 쿼리 실행!!
                INSERT INTO users_copy SELECT * FROM users;
            ```
        - UPDATE
            - 기존 내용(데이터) 업데이트
            - 조건을 사용!(사고 에방)
            - 작성한 글/외훤 정보/... 수정하기!! <= 예시
            ```
                -- 회원 정보 수정
                UPDATE users
                SET email='c@c.com', age=age+5; -- 전제 수정

                -- 5번 회원의 정보를 수정!!!
                UPDATE users
                SET email='d@d.com', age=age+5
                WHERE id=5; -- 특정 대상만 수정

                SELECT * FROM users;
            ```
        - Delete
            - 행(데이터) 삭제
            - 조건식 사용
            - 복구 가능함
            ```
                -- 회원 탈퇴,..
                DELETE FROM users
                WHERE id=4;

                -- 글-댓글, country-city
                -- 기본키, 참조키 연관
                -- 1:N(일대 다 개념)
                -- 1개 국가에는 여러개의 도시가 존재한다, 부모대 자식 관계

                -- 더미 테이블 - 국가
                CREATE TABLE country2 (
                    country_id INTEGER,
                    NAME VARCHAR(64),
                    population INTEGER,
                    PRIMARY KEY (country_id)
                );
                -- 더미 데이터 추가
                INSERT INTO country2
                VALUES (1, '서울', 10000000),(2,'부산', 5000000); -- 멀티 데이터 입력

                -- FK : FOREIGN KEY (외래키, 참조키)

                FOREIGN KEY (컬럼)
                REFERENCES 테이블명 (참조할컬럼명)
                [ON DELETE CASCADE ] -- [] 생략가능,  
                -- CASCADE : 국가데이터삭제->참조하는 모든 도시도 삭제

                -- 더미 테이블 - 도시
                CREATE TABLE city2 (
                    city_id INTEGER,
                    NAME VARCHAR(64),	
                    country_id INTEGER,  -- country2 를 참조!! -> FK
                    
                    PRIMARY KEY (city_id),
                    FOREIGN KEY (country_id) -- city2.country_id 임
                    REFERENCES country2 (country_id)
                    ON DELETE CASCADE -- 참조하는 데이터가 삭제되면 같이 모두 삭제된다!!
                );

                -- 도시(자치구) 더미 데이터 
                INSERT INTO city2
                VALUES 
                    (1, '성북구', 1), -- (고유번호, 자치구명, 서울고유값(1))
                    (2, '강남구', 1),
                    (3, '부산진구', 2); -- (고유번호, 자치구명, 부산고유값(2))

                SELECT * FROM country2;
                SELECT * FROM city2;

                -- 삭제 처리
                -- 서울 삭제 -> 서울을 참조하고 있는 모든 자치구도 삭제
                DELETE FROM country2 WHERE country_id=1;

                -- 서울내 자치구는 모두 삭제되었고, 부산만 남았음
                SELECT * FROM city2; 
            ```
        - TRUNCATE
            - 복구 불가, 모든 데이터/인덱스 삭제 -> 최초 테이블 상태로 구성
            - DB 용량 줄어듬
            ```
                -- 완전삭제
                TRUNCATE TABLE city2;
            ```
        - DROP
            - 테이블 삭제, 데이터, 사용공간 모두 삭제
            ```
                -- 테이블 삭제
                DROP TABLE city2;
                DROP TABLE country2;
                -- 데이터베이스 삭제
                DROP DATABASES 디비명;
            ```
# DCL
    - 데이터 제어어, 접근 관리
        - 보안
        - 계정생성/삭제 와 권한 부여/제거
    - SQL
        - 계정
            - create user
            - drop user
        - 권한
            - grant
            - revoke
        - 관련 데이터베이스
            - mysql 데이터베이스가 담당 (기본적으로 설치된 DB)
            ```
                -- 실습1 = 계정 만들고 삭제하기
                -- 1. 계정 생성 : 아이디만 부여
                CREATE user guest1;

                -- 2. 계정 확인
                SELECT HOST, user, authentication_string FROM user;

                -- 3. 계정 삭제
                DROP user guest1;

                -- 4. 계정 확인
                SELECT HOST, user, authentication_string FROM user;

                -- 실습2 - 아이디, 비번, host(접속위치) 넣어서 생성
                -- 5. localhost 라는 것은 디비가 물리적으로 설치된  PC에서 접속하겠다!!
                CREATE user guest1@localhost IDENTIFIED BY '1234';

                -- 6. 계정 확인
                SELECT HOST, user, authentication_string FROM user;

                -- 7. 계정 삭제
                DROP user guest1@localhost;

                -- 실습3 - 아이디, 비번, host(접속위치):모든 곳에서 접근가능하게!!
                -- 8. 원격 접속가능하게!! -> % <- 보안에 좋지 않음, 전세계 접근 가능함
                CREATE user guest1@'%' IDENTIFIED BY '1234';

                -- 9. 계정 확인
                SELECT HOST, user, authentication_string FROM user;

                -- 10. 세션 관리자에서 접근해봄
                -- xxxx_sh... 디비만 보임, 접속은된다!!, 업무  x => 권한 부여 필요

                -- 실습 4- 권한부여
                GRANT [권한] ON [db].[테이블] TO [유저아이디]@[호스트]
                -- 11. t1 디비에 모든 권한 부여!!
                GRANT ALL PRIVILEGES ON t1.* TO 'guest1'@'%';

                -- 12. 메모리 저장
                FLUSH PRIVILEGES;

                -- 13. 세션관리자 확인
                -- guest1 / 1234 => t1 확인됨

                -- 14. 권한 확인
                SHOW GRANTS FOR guest1;

                -- 15. 권한 삭제
                -- 루트 권한에서만 승인됨 (작업)
                REVOKE ALLmysql PRIVILEGES ON t1.* FROM 'guest1'@'%';
            ```

