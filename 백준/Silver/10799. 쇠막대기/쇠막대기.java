import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        char[] arr = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        int answer = 0;
        char prev_c = arr[0];
        stack.push(prev_c);
        for(int i = 1; i < arr.length; ++i) {
            if(arr[i] == '(') stack.push(arr[i]);
            else if(arr[i] == ')') {
                stack.pop();
                if(prev_c == '(') answer += stack.size();
                else answer++;
            }

            prev_c = arr[i];
        }

        bw.write(answer + "");
        bw.flush();
    }
}