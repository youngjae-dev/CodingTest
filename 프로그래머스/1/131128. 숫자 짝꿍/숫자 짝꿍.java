import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] x = new int [10];
        int[] y = new int [10];
        for(char c : X.toCharArray()) {
            ++x[c - '0'];
        }
        for(char c : Y.toCharArray()) {
            ++y[c - '0'];
        }
        StringBuilder answer_sb = new StringBuilder();
        for(int i = 9; i >= 0; --i) {
            int min = Math.min(x[i], y[i]);
            while(min != 0) {
                answer_sb.append(i);
                --min;
            }
        }
        String answer = answer_sb.toString();
        if(answer.equals("")) return "-1";
        if(answer.charAt(0) == '0') return "0";
        return answer;
    }
}