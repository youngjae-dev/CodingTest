import java.io.*;
import java.util.*;

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