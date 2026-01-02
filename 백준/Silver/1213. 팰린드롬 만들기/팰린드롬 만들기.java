import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        String s = input.next();

        HashMap<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Character> alphabet = new ArrayList<>(map.keySet());
        Collections.sort(alphabet);

        StringBuilder sb = new StringBuilder();
        StringBuilder sb_reverse = new StringBuilder();

        int odd_count = 0;
        String odd_char = "";

        String answer = "";
        boolean flag = true;

        for(char c : alphabet) {
            int num = map.get(c);

            if(num == 1){
                odd_char = String.valueOf(c);
                ++odd_count;
            }
            else if(num % 2 != 0 && num > 1) {
                while(num > 1) {
                    sb.append(c);
                    sb_reverse.append(c);
                    num -= 2;
                    odd_char = String.valueOf(c);
                }
                ++odd_count;
            }
            else {
                while(num > 0){
                    sb.append(c);
                    sb_reverse.append(c);
                    num -= 2;
                }
            }
            if(odd_count > 1) {
                flag = false;
                break;
            }
        }

        if(flag) {
            answer = sb.toString();
            answer += odd_char;
            answer += sb_reverse.reverse().toString();
        }
        else {
            answer = "I'm Sorry Hansoo";
        }
        
        System.out.println(answer);
    }
}