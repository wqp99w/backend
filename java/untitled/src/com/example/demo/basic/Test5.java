import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 컬렉션 API
 *      - 데이터베이스 연동 후 가져오는 데이터 => 컬렉션으로 처리됨!!
 *      - List
 *          - 순서가 있다
 *          - (*)ArrayList, LinkedList, ...
 *      - Set
 *          - 중복 데이터가 없다(중복제거)
 *          - HashSet, TreeSet
 *      - Map
 *          - 키와 값 형태로 보관
 *          - HashMap, TreeMap
 */

public class Test5 {
    public static void main(String[] args) {
        // 1. List
        {
            // 저장 공간 준비
            // <유형> => 제네릭 표현
            // ArrayList : 자식, List : 부모
            List<Integer> temp = new ArrayList<Integer>();

        }

        // 2. set - 중복 제어(허용x)
        //      동일한 값 넣어서 확인, 순서는 x(보장되지 않음)
        {
            Set<Integer> temp = new HashSet<Integer>();
        }
    }
}
