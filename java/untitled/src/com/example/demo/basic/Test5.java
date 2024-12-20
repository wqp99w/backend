package com.example.demo.basic;

import java.util.*;

/**
 * 컬렉션(*) API
 *      - 데이터베이스 연동후 가져오는 데이터는 => 컬렉션 으로 처리됨!!
 *      - 맴버를 언제든지 추가 삭제 가능함
 *      - List
 *          - 순서가 있다
 *          - (*)ArrayList, LinkedList, ...
 *      - Set
 *          - 중복 데이터가 없다(중복제거)
 *          - HashSet, TreeSet
 *      - Map
 *          - 키와 값 형태로 보관 -> JSON, JS 객체 , python dict
 *          - HashMap, TreeMap
 */
public class Test5 {
    public static void main(String[] args) {
        // 1.  (중요)List, 순서(*) 존재->인덱스(0, 1, ..) 존재!!, 중복 가능함
        {
            // 저장 공간 준비
            // <유형> =>  제네릭 표현 (범용성등 목적)
            // ArrayList : 자식, List: 부모
            // 크기 지정 한적 없다!!
            // 제네릭, 다형성, 컬렉션 적용
            // 제네릭을 통해서 컬렉션에서 담을 데이터의 타입 제약 -> 문자열만 받을수 있다
            // 다형성을 통해서 부모 List 가 자식객체  ArrayList를 받을 수 있게 정의
            List<String> temp = new ArrayList<>();    // new 클레스명() => 객체 생성
            System.out.println(temp); // []

            // 더미데이터 사용 <- 가변크기, 언제든지 맴버를 추가할수 있다!!
            temp.add("a");
            temp.add("b");
            temp.add("c");

            System.out.println(temp); // [a, b, c]

            // List 언제든지 가변적으로 맴버 추가 혹은 삭제 가능
            // 값 출력하기
            System.out.println( temp.get( 1 ) ); // 참고 배열은 변수[ 1 ]
            // 크기 확인 -> for문과 자주 사용
            System.out.println( temp.size() ); // 데이터 전체 크기
            // 요소 삭제
            System.out.println( temp.remove(1) ); // 삭제된 내용이 출력
            // 초기화 -> 데이터 모두 버림 -> 버퍼를 비운다, 모두 삭제
            temp.clear();
            System.out.println( temp.size() );
            System.out.println( temp );
        }

        // 2. Set - 중복 제거(허용 x)
        //    동일한 값 넣어서 확인, 순서는 X(보장되지 않음)
        {

            // 컬렉스 스타일의 그릇 생성
            Set<String> temp = new HashSet<>();

            // 요소추가
            temp.add("서울");
            temp.add("부산");
            temp.add("대전");
            temp.add("대구");
            temp.add("인천");
            temp.add("서울"); // 중복 추가

            // 중복이 제거 되었는가?
            System.out.println(temp);
            // 중복 제거 OK, 검사과정에서 순서가 바꼈음
            // 순서가 중요하지 않는 데이터 를 사용하여 중복 제거 혹은 이후 정렬
            System.out.println( temp.size() );
            // Set은 조연임, 특정 과정속에서 데이터 전처리에 사용됨
        }

        // 3. (중요)Map, 키와 값의 쌍으로 구성
        //    키는 고유값, 값은 중복 가능
        {
            // Integer는 int을 대변하는 클레스 (레퍼클레스) -> int
            Map<String, Integer> persons = new HashMap<>();

            // 컬렉션의 이름 => 복수형으로 구성
            // 요구사항 사람의 이름과 나이를 관리하는 자료구조 구성
            // 키는 이름, 값은 나이
            // 키-값 추가 (이름, 나이) <= 의미 부여
            persons.put("JJ", 40);
            persons.put("Kim", 30);
            persons.put("Park", 20);

            // 값 출력 : {JJ=40, Kim=30, Park=20}
            System.out.println(persons);
            // 개별값 : JJ의 나이는? 40
            System.out.println("JJ의 나이는? "+ persons.get("JJ"));

            // 크기
            System.out.println( persons.size() );
            // 새로 추가하는  내용의 키가 기존에 존재하는 키라면? (키 중복)
            persons.put("JJ", 41); // 추가 x,  수정 ok(키는 고유함)
            System.out.println(persons);

            // TODO Map for문을 이용하여 모든값 출력하기 -> 나중에 체크 Generic.java

            // 삭제
            persons.remove("JJ"); // JJ 제거
            System.out.println(persons); // {Kim=30, Park=20}
            // 초기화 -> 내용 비워라
            persons.clear();
            System.out.println(persons); // {}
        }
    }
}












