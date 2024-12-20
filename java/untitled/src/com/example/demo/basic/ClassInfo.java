// 패키지 내부에서 클레스 생성 -> 무조건 패키지 표식 자동추가됨
package com.example.demo.basic;

/**
 * 클레스 정의
 * - 클레스 (class)
 *      - 코드로 존재
 *      - 객체를 생성(구현)하기 위한 설계도면(청사진, 붕어빵기계,..., 틀)
 *      - 변수(필드), 메소드(함수), 생성자 로 구성
 *
 * - 객체 (Object)
 *      - 메모리상에 존재
 *      - 클레스를 기반으로 생성 (클레스와 유사하게 생겼음)
 *      - 클레스의 인스턴스가 객체이다 (붕어빵)
 *
 *  - 클레스 문법
 *  [모디파이어] class 클레스명(첫글자 대문자)
 *  [extends 부모클레스명(상속표현)]
 *  [implements 인터페이스명(다중상속표현)] <- 클레스 선언부
 *  {   <- 클레스 코드블럭
 *      [맴버 변수]
 *      [맴버 메소드]
 *      [생성자]
 *      [inner class(내부 클레스)] - 생략 !!
 *  }
 */
// 1. 클레스 정의 (간단하게)
//    자바에서 클레스는 코드 작성 단위 -> 클레스 단위로 개발!!
class Person {}
// 2. 맴버 추가
class Person2 {
    // 맴버(클레스 내부에서 정의),  변수(변수 구성 문법 고려), 범위:전역변수
    String name;
    int age;
    // 맴버 메소드
    void info () {
        System.out.println("Person2 info " + name + " " + age);
    }
}
// 3. 생성자(constructor) 사용
//    문법 : 클레스명과 동일, 메소드 형식, 리턴타입 x, 객체를 생성 역활, 맴버변수 초기화
//    종류 : 기본생성자(default constructor), 생성자 재정의(overroading), 생략가능
//          생략한다면 -> 컴파일러(javac) 자동 삽입해줌, 필요시 개발자가 커스텀 생성!!
//          기본생성자(default constructor) : 파라미터 없는 생성자 (빈 껍대기)<->파라미터 존재
class Person3 {
    String name;
    int age;
    // 생성자
    // 기본 생성자
    public Person3() {
        // 커스텀 가능
    }
    // 생성자 오보로딩
    // 맴버변수 초기화에 집중한 생성자
    public Person3(String name, int age) {
        // this => 클레스 내에서 자기자신을 가르키는 키워드
        this.name = name; // 맴버변수 = 매개변수;
        this.age = age;
    }
}
// 객체 지향 프로그램(OOP)의 3대 특징 : 상속성, 다형성, 은닉성
// 4. 데이터 은닉(객체 지향 프로그램의 3대 특징중 한개), 클레스 내부 데이터는 외부에서 직접  접근 x
//    제공되는 메소드를 통해서 접근해라(설계 기법) -> 메소드 : getter, setter
//    데이터 종류가 너무 많다면 => get/setter 언데 다 만들지? => 라이브러리 => lombok(롬복)
//    스프링부트 개발시 필수 라이브러리 : 롬복
class Person4 {
    // 외부에서 직접  접근 x 설정 필요 => private 추가 : 데이터가 은닉(숨겨짐)됨 => 설계개념
    private String name;
    private int age;
    // Getter, Setter
    // public 지정해서 누구나 사용 가능하게 접근 제어를 조정
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    // setter 생성
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
}


// 1개의 자바 파일안에 여러개의 class가 존재할수 있다!!
// 파일명과 일치하는 클레스, 그안에 main 함수가 존재하면 문제 없다!!
public class ClassInfo {
    // 메인 메소드
    // J2SE(PC 기반 유저 대상 자바 개발시 사용하는 버전)
    // 반드시 main 메소드가 존재해야 한다 (규칙)
    // 가장 먼저 메모리에 상주, 호출되야 한다(규칙)
    // args : 자바 구동시 전달할 매개 변수가 있다면 받는다
    // 통상적으로는 전달 X
    // main 시작점, 엔트리포인트!!
    public static void main(String[] args) {
        // 클레스는 객체의 틀(청사진, ...) -> 메모리에 객체를 띠우는게 목표 -> 프로그램 작동
        // 객체 생성법(문법) => new 클레스명([ 매개변수 ]);

        // 객체 생성 -> 인텔리J 해석 -> 코드 가이드 : AI로 처리하는 패턴
        // Person p <- 참조형(타입) 변수!!, 클레스명=>내가만든 참조형 타입이 된다
        // 참조형 변수 = 객체!!
        Person p = new Person();
        Person2 p2 = new Person2();
        // p2는 맴버 존재 => 접근 => 객체명.맴버 : 도트(.) 연산자로 접근
        p2.name = "게스트";
        p2.age  = 30;
        // 맴버 메소드 사용
        p2.info();

        // Person3 객체 생성, 매개변수를 세팅해서 생성
        Person3 p3 = new Person3("게스트2", 10);
        System.out.println(p3.name+" "+p3.age); // 맴버 변수 출력

        // Person4 객체 생성
        Person4 p4 = new Person4();
        p4.setName("게스트3"); // p4.name="..."; <- 에러, 접근제어 오류
        System.out.println( p4.getName() );
    }
}