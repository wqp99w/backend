package com.example.demo.basic;

import java.util.Scanner;

/**
 * 흐름제어
 *  - 조건문 : if ~ else if ~ else ~
 *      - 콘솔 입력 사용
 *  - 반복문 : for ~,  do ~ while, while ~ , continue, break
 *  - 게임 제작(간단하게) - 스킵
 *      - 콘솔 입력 사용
 */
public class Test3 {
    public static void main(String[] args) {
        // 조건문 연습
        flowcontrol1();
        // 반복문-for 연습 : 지정된 횟수 반복
        //flowcontrol2();
        // 반복문-while 연습 : 무한루프(종료 시점 알수 없다)
        //flowcontrol3();
    }

    private static void flowcontrol3() {
        int i=1;
        // 0 ~ 무한대 반복
        while( true ) {
            System.out.println(i);
            // i 업데이트 필요
            i++;
            if( i > 5 ) break; // 나를 감싸고 있는 가장 가까운 반복문 탈출
        }
        // 출력값 : 1, 2, ....

        // 1 ~ 무한대 반복
        System.out.println("-----");
        i = 1;
        do{
            if( i % 2 == 1 ) continue; // 나머지는 0 or 1 만약 1이면(홀수면)
            System.out.println(i);
            i++;
            if( i > 5 ) break; // 탈출 조건
        }while( true );
        // 출력값 : ... 혹은 무한루프 (학습 정리 시간에 고민)
    }

    private static void flowcontrol2() {
        // for 문의 문법 => JS와 유사함
        // 기본 for문
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }
        // 응용
        // 1 ~ 100까지 총합계 구하시오 => 기본 for문 사용
        int sum = 0; // 합계값을 담을 그릇(정수형)
        for (int i = 1; i <= 100; i++) {
            // 누적합
            sum += i;
            //sum = sum + i;
        }
        System.out.println("누적합 : " + sum);

        // 역순 출력
        // 값 업데이트 파트  => i++, i--, i+=2, ... 여러 표현 가능(요구사항에 맞게)
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
        }

        // 배열 순환 for
        int[] arr = {3, 7, 4, 10};
        // arr.length : 배열의 길이, 현재 데이터 기준 4번 반복
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // 중첩 for문 => 구구단 => 5~7단, 3~6까지만 출력
        // 출력값 : 5 x 3 = 15, .... 7 x 6 = 42
        // 15, .... 42 <= 곱의 결과만 나오게 출력
        for (int i = 5; i <=7; i++) {     // i : 5, 6, 7
            for (int j = 3; j < 7; j++) { // j : 3, 4, 6
                // print() 줄바꿈 없는 출력
                System.out.print( i*j + "\t" );
            }
        }
        // 피라미드 출력 연습
        // 오후 학습 정리때 시도 !! => 2중(중첩) for문 처리
        /*
            순열식 값 시뮬레이션 쉽게
            for : 전체는 5번반복
                for
                    - : 4, 3, 2, 1, 0
                for
                    * : 1, 3, 5, 7, 9
           ----*
           ---***
           --*****
           -*******
           *********
         */

        // for-each 반복 처리 => 배열, 컬렉션 계열 데이터를 대상
        System.out.println();
        int[] data = {10, 12, 15, 40, 50};
        for(int i: data) { // JS의 for ~ of
            // 인덱스에 상관없이 값을 뽑아서(순서대로) 출력
            System.out.println(i);
        }


    }

    private static void flowcontrol1() {
        // 1. 콘솔 입력
        Scanner sc = new Scanner(System.in); // 콘솔에서 사용자 입력 대기하는 코드
        while(true)
        {
            try {
                System.out.println("Enter number: ");
                // 2. 사용자 입력후 엔터 => 값이 반환
                int userInputValue = sc.nextInt(); // 입력 대기
                System.out.println("Enter number: ");
                // 3. 만약 100을 입렷했다면, 종료 혹은 반복문 탈출
                if (userInputValue == 100) break;
                // 4. 조건식 사용, JS 조건문 동일, 조건이 2개? 3개?
                if (userInputValue > 10) {
                    System.out.println("10보다 초과한다");
                } else {
                    System.out.println("10보다 작거나 같다");
                }
            }catch (Exception e) {
                System.out.println("오류 : " + e.getLocalizedMessage() );
            }
        }
        // 입력 닫기
        sc.close();
    }
}