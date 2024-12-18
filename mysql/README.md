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
        - LOCATE()
        - LEFT(), RIGHT()
        - LOWER(), UPPER()
        - REPLACE()
        - TRIM()
        - FORMAT()
        - SUBSTRING()
    - 수학
    - 시간
    - 형변환
    - 일반
    - 랭킹

- DDL

- DML

- DQL