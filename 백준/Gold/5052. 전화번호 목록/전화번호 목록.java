import java.io.*;
import java.util.*;

// 문제에 대한 고찰 : 입력으로 들어오는 전화번호를 길이순이 아닌 사전순으로 배열함으로써 접두어 관계에 속하는 전화번호끼리 인접하도록 정렬한 후, startsWith로 접두어 여부를 확인하는 방법으로 코드를 리팩토링하였다.

public class Main {
    public static String solution(int N, String[] numbers) {
        for(int i = 0; i < N - 1; ++i) {
            if(numbers[i+1].startsWith(numbers[i])) return "NO";
        }
        return "YES";
    }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for(int i = 0; i < T; ++i) {
            int N = input.nextInt();
            String[] numbers = new String[N];
            for(int j = 0; j < N; ++j) {
                numbers[j] = input.next();
            }
            Arrays.sort(numbers);
            System.out.println(solution(N, numbers));
        }
    }

}
