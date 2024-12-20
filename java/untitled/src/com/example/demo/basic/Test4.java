/**
 * 배열
 *  - 자바의 기본 타입 이외에 n개의 데이터를 다루는 자료구조
 *      - (*)배열 -> 아파트 형태, 각 호실에 데이터가 존재!!
 *          - 순서 : 0, 1, 2, ...
 *          - 동일한 타입만 멤버로 가질 수 있다!!
 *              - 정수 배열 -> 정수만
 *              - 문자열 배열 -> 문자열
 *              - 배열이 한 번 만들어지면 멤버를 추가/삭제등 행위 x
 *              - 고정크기!!, 최초 세팅된 크기가 최종
 *      - 컬렉션 계열
 *          - List -> 게시판 데이터 가져와서 받을 때 주로 사용 됨
 *          - Map
 *          - Set
 *          - ...
 */

public class Test4 {
    public static void main(String[] args) {
        // 1. 배열 선언 및 초기화
        {
            // 크기 5개짜리 정수형 배열 생성 -> 아파트 공사해라
            int [] nums = new int[5]; // 배열 껍대기만 존재, 데이터 x

            // 배열에 데이터 세팅
            nums[0] = 1;
            nums[1] = 2;
            nums[2] = 3;
            nums[3] = 4;
            nums[4] = 5;

            for (int i: nums){
                System.out.println(i);
            }
        }
        // 2. 자주 사용하는 유형, 배열 선언과 초기화
        {
            int [] nums = {10, 5, 6, 8, 29};
            for (int i: nums){
                System.out.println(i);
            }
        }
        // 배열 요소 찾기(검색)
        {
            int [] nums = {10, 5, 6, 8, 29};
            int target = 6;
            // 검색 데이터를 찾으면 반복문 중단
            for (int i: nums){
                if(i==target) {
                    System.out.println("탐색완료 : "+i);
                    break;
                }
            }
        }
        // 4. 배열 카피
        {
            int [] nums = {10, 5, 6, 8, 29};
            // 복사한 데어터를 담을 배열 준비
            int [] nums_copy = new int[nums.length]; // 배열의 크기만큼 공간 준비
            // api 지원
            // (원본 배열, 복사할 시작 위치, 타겟 배열, 타겟배열의 시작위치, 복사될 양)

            System.arraycopy(nums, 0, nums_copy, 0, nums.length);
        }
        {}
        {}

    }
}
