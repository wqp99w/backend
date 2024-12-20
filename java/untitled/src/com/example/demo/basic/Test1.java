/**
 * 자바 프로그램 구조
 *      - 클래스 단위 개발 (통상)
 *      - main 함수가 엔트리 포인트(진입로, 가장 먼저 호출되는 메소드)
 *          - A.java 파일을 실행
 *          - 1. 컴파일
 *              - javac A.java
 *              - 컴파일의 결과를 => A.class (생성됨)
 *              - 자바는 컴파일 언어 (소스코드 -> 컴파일 -> 실행)
 *              - 자바 스크립트는 인터프린터 언어 (소스코드 -> 실행)
 *          - 2. 실행
 *              - java A.class
 *          - 3. 가장 먼저 main() 함수를 찾는다!! -> 호출하면서 실행됨
 */
public class Test1 {
    public static void main(String[] args) {
        // 여기서 자바 코드 작성!!
        // 1. 변수, 타입 !!
        //      변수 규칙 : 카멜표기법
        //      타입 변수명;
        //      타입 변수명 = 값;
        int a = 10;
        // int (그릇, 정수만 담을 수 있다)
        // 정수값을 담을 수 있는 그릇(변수) a를 선언 및 초기화한 것임

        // 그릇의 종류 -> 타입
        // 자바의 타입 = 원시(primitive) 타입: 8 + 참조(reference) 타입: 무한대

        // 정수형
        byte i1 = 1;
        short i2 = 1;
        int i3 = 1; // 정수밗을 담을 그릇은 정수 타입으로 지정하며 됨
        long i4 = System.currentTimeMillis();
        //출력
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        // 블린
        boolean b1 = true;
        System.out.println(b1);
        // 문자 (1개) , 문자열 X
        char c1 = 'a';
        System.out.println(c1);
        // 부동 소수형
        float f1 = 1.1f;
        // float f2 = 1.2; // float는 반드시 f를 붙여야 한다
        double d1 = 1.1d;
        double d2 = 1.1d;
    }
}
