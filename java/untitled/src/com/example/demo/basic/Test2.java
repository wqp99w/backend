/**
 * 자바의 타입
 *  - 참조형
 *      - 대표적인 종류 : 문자열 (String)
 */

public class Test2 {
    public static void main(String[] args) {
        // 문자열 테스트
        // 타입 변수 = 값;
        String news = "크리스마스"; // 문자열 => "값"
        System.out.println(news);

        // 배열 => 아파트 : 대표 변수명 [위치정보: 인덱스]
        // 정수형 배열이라고 표현, 멤버는 정수만 가질 수 있다
        // 자바를 타입을 강력하게 제한
        int []numbers = {1,2,3,4,5,6,7,8,9,10}; // 배열 numbers의 구성원은 무조건 int 타입만
        System.out.println(numbers[0]);
        System.out.println(numbers[5]);

        // 문자열 배열
        String[] langs = {"Java","Python","JS"};
        System.out.println(langs[0]);
        System.out.println(langs[1]);
    }
}
