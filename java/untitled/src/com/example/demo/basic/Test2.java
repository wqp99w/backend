package com.example.demo.basic;

/**
 * 자바의 타입
 *  - 참조형 -> 객체타입
 *      - 대표적인 종류 : 문자열 (String), 정수형|문자열|... 배열
 *      - 참조형은 모두다 객체이다!!
 */
public class Test2 {
    public static void main(String[] args) {
        // 문자열 테스트
        // 타입 변수 = 값;
        String news = "크리스마스"; // 문자열 => "값"
        System.out.println(news);
        
        // 배열 => 아파트 :대표변수명 [위치정보:인덱스]
        // 정수형 배열이라고 표현, 맴버는 정수만 가질수 있다
        // 자바틑 타입을 강력하게 제한한다!!
        int[] numbers = {1,2,3,4,5,6,7,8,9,10}; // 배열 numbers의 구성원은 무조건 int타입만
        System.out.println( numbers[ 0 ]  ); // JS와 동일하게 값 추출
        System.out.println( numbers[ 5 ]  );

        // 문자열 배열
        String[] langs = {"Java","JS","Python"}; // 맴버는 무조건 문자열만 추가!!
        System.out.println( langs[ 0 ]  );
        System.out.println( langs[ 1 ]  );
        // JS의 배열과 문법적으로는 생성을 제외하고는 유사하다!!
    }
}