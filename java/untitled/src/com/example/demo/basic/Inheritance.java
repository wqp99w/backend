package com.example.demo.basic;

// 코드 [모두] 접기 : ctrl + [shift] + -
// 코드 [모두] 펼치기 : ctrl + [shift] + +

/**
 * 상속성 과 다형성 문법 체크 -> 클레스 마무리
 * - 상속
 *  - 부모 클레스의 모든 유산(맴버 변수/메소드/생성자)을 그대로 상속받고
 *  - 자식이 재정의(업그레이드)
 *  - 데이터(변수), 기능(메소드) 추가
 *  - 용도
 *      - 버전업!!(안드로이드 2009년 -> 현재 계속 상속받으면서 확장)
 *      - s/w 업그레이드 관리
 *      - 빠른 개발(생산성 높아짐)
 */
// 1. 상속 (Inheritance)
// 부모 클레스
class Animal {
    // 부모의 유산 맴버 변수/메소드 준비
    String name;
    void eat() {
        // this.은 생략 가능함 => 컴파일러 자동 삽입해줌
        System.out.println( this.name + " eat");
    }
}
// 자식 클레스
class Cat extends Animal {}
class Dog extends Animal {
    // 부모의 유산을 재정의(업그레이드) 할수 있다
    // 재정의(오버라이드),
    @Override // 어노테이션,
    void eat() {
        // super : 부모 객체를 지칭하는 이름, this: 자기자신
        //super.eat(); // 부모의 원래 유산(맴버 매소드)호출
        System.out.println( this.name + " 잘 먹는다");// 업그레이드
    }
    // 어노테이션
    // "자바에 메타데이터를 제공하는 기법"
    // 컴파일러 | 런타임관리자등 어노테이션 확인후에
    // 코드를 삽입, 기능을 부가 등등 처리!!
    // 스프링부트에서 진행시 엄청 많이 사용!! -> 사용시 체크 !!

    // 자식이 맴버 추가할수 있다!! (
    String kind; // 품종
    void info() {
        System.out.println( this.name + " 의 품종은 " + this.kind );
    }
}
/**
 * Cat은 Animal 이다 - OK : 자식 is a 부모
 * Animal은 Cat 이다 - Fail
 * Animal은 Cat을 자식으로 가진다 - 부모 has a 자식
 */

/**
 * 다형성 (Polymorphism)
 * 하나의 객체가 여러 형태를 가질수 있다!
 */
class XMan {
    void attack(){
        System.out.println("공격 능력");
    }
}
class 울버린 extends XMan {
    // XMan의 유산을 받아서 자식은 업그레이드
    @Override
    void attack() {
        System.out.println("갈고리 공격 능력");
    }
}
class 매그니토 extends XMan {
    @Override
    void attack() {
        System.out.println("금속/자기장 공격 능력");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        // 상속
        Cat cat = new Cat();
        // 부모로 상속받은 유산(맴버..) 사용
        cat.name = "고양이";
        cat.eat();

        Dog dog = new Dog();
        dog.name = "강아지";
        dog.eat(); // 자식 세대에서 업그레이드된 메소드
        // 기능 추가
        dog.kind = "진돗개";
        dog.info();

        // 다형성 : 자식 객체를 부모가 받을 수 있다
        // 부모는 여러 모습을 띨수 있다 => 다형성
        // 부모형 변수 = 자식 객체; 자식 객체를 부모형 변수가 받을 수 있다
        XMan xman; // 참조형 변수
        xman = new 울버린();
        xman.attack();

        xman = new 매그니토();
        xman.attack();


    }
}