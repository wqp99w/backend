import java.util.Scanner;

/**
 * 흐름제어
 *  - 조건문 : if
 *  - 반복문
 *  - 게임제작 (간단하게)
 */

public class Test3 {
    public static void main(String[] args) {
        // 조건문 연습
        //flowcontroll();
        flowcontroll2();
    }

    private static void flowcontroll2() {
        int sum =0;
        for (int i = 1; i <= 100; i++) {
            sum+=i;
        }
        System.out.println(sum);

        int[] data = {10, 12, 15, 40, 50};
        for(int i:data){
            System.out.println(i);
        }
        int i=1;
        do{
            if(i%2==1) continue;
            System.out.println(i);
            i++;
            if(i>5) break;
        }while(true);
        // 출력값 : ... 혹은 무한루프
    }

    private static void flowcontroll() {
        // 1. 콘솔 입력
        Scanner sc = new Scanner(System.in); // 콘솔에서 사용자 입력 대기하는 코드
        while(true) {
            // 2. 사용자 입력 후 엔터 => 값이 변환
            int userInputValue = sc.nextInt();
            // 3. 만약 100을 입력했다면, 종료 혹은 반복문 탈출
            if (userInputValue == 100) break;
            // 4. 조건식 사용, JS 조건문 동일
        }
    }
}
