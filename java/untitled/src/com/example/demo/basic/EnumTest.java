package com.example.demo.basic;

/**
 * 열거형
 *  - 상수로 정의되는 여러 변수들을 묶어서 클래스처럼 관리하는 타입
 *  - 여러 상수를 그룹으로 묶어서 관리!!
 *  - 내부적인 변수들은 모두 public static final 적용됨
 */

// 1. 기본형
enum Level {
    VVIP, VIP, GOLD, SILVER, BRONZE; // 고객 등급
}
enum TrafficSignal {
    // 열겨형 변수에 값 부여 구조
    GREEN("출발신호"),
    YELLOW("정지대기"),
    RED("정지신호"),
    ARROW("방향전환신호");

    // 열거형 변수를 대변하는 변수 지정(값의 타입 고려)
    private final String signal;
    // 열거형 생성자에서 데이터 세팅 구조
    TrafficSignal(String signal) {
        this.signal = signal;
    }

    public String getSignal() {
        return signal;
    }
}
public class EnumTest {
    public static void main(String[] args) {
        // 1. 기본 사용
        {
            // 열거형 타입의 변수
            Level level = Level.VVIP; // 값은 5개 중에 한개만 가능
            // 등급값에 따라 동적으로 처리가 가능한 구조!!
            switch (level) {
                case VVIP:
                    System.out.println("VVIP");
                    break;
                case VIP:
                    System.out.println("VIP");
                    break;
                default:
                    System.out.println("기타 등급");
                    break;
            }
        }

        // 2. 열거형의 값 활용 -> 출력
        {
            // TrafficSignal.values() => 배열로 자료구조 전환
            for( TrafficSignal signal : TrafficSignal.values() ) {
                System.out.println(signal.getSignal());
            }
        }
    }
}
