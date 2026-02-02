import java.io.*;
import java.util.*;

public class Main {
    static char[] arr;
    static int index = 0;
    static Stack<Character> stack = new Stack<>();
    static int DFS() {
        int sum = 0;

        while(index < arr.length) {
            char c = arr[index++];

            if(c == '(') {
                stack.push(c);
                int tmp = DFS();
                if(tmp == 0) tmp = 1;
                sum += tmp * 2;
            }
            else if(c == '[') {
                stack.push(c);
                int tmp = DFS();
                if(tmp == 0) tmp = 1;
                sum += tmp * 3;
            }
            else if(c == ')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    System.out.println(0);
                    System.exit(0);
                }
                return sum;
            }
            else if(c == ']') {
                if(stack.isEmpty() || stack.pop() != '[') {
                    System.out.println(0);
                    System.exit(0);
                }
                return sum;
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        arr = br.readLine().toCharArray();
        int answer = DFS();
        if(stack.isEmpty()) System.out.println(answer);
        else System.out.println(0);
    }
}