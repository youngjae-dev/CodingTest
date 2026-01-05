import java.io.*;
import java.util.*;

public class Main {
    public static String solution(int N, String[] numbers) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < N; ++i) {
            StringBuilder sb = new StringBuilder();
            for(char c : numbers[i].toCharArray()) {
                sb.append(c);
                if(set.contains(sb.toString())) return "NO";
            }
            set.add(sb.toString());
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
            Arrays.sort(numbers, (s1, s2) -> s1.length()-s2.length());
            System.out.println(solution(N, numbers));
        }
    }
}