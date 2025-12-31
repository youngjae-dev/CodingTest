import java.util.*;

// 문제 고찰
// HashMap와 StringBuilder를 사용했지만, HashMap보다는 HashSet을 사용하는것이 메모리를 덜 차지하기 때문에 변경하였고
// StringBuilder는 substring을 사용하게 되면 어쩔 수 없이 객체가 매번 생성되기때문에 StringBuilder 객체를 따로 생성하지 않고 입력으로 들어온 str만을 사용하는 방식으로 코드를 변경하였다

public class Main {
    public static int solution(String str){
        HashSet <String> set = new HashSet<> ();
        int length = str.length();
        for(int i = 1; i <= length; ++i) {
            for(int j = 0; j < length -i + 1; ++j) {
                set.add(str.substring(j, j + i));
            }
        }
        return set.size();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(solution(str));
    }

}
