//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
import java.util.*;
public class Main {
    public static int solution(HashMap<String, Integer> str_array, ArrayList<String> strings){
        int answer = 0;
        for(String s : strings) {
            if(str_array.containsKey(s)) ++answer;
        }
        return answer;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        String buffer = input.nextLine();
        HashMap<String, Integer> str_array = new HashMap<String, Integer>();
        for(int i = 0; i < N; ++i) {
            String tmp = input.nextLine();
            str_array.put(tmp, 0);
        }
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < M; ++i) {
            String tmp = input.nextLine();
            strings.add(tmp);
        }
        System.out.println(solution(str_array, strings));
    }
}