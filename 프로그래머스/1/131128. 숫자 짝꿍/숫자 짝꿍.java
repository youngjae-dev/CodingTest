import java.util.*;

// 이번 문제의 피드백
// String + String 연산을 통해 answer에 누적하는 방식을 사용했었지만, 이 경우 String 객체의 특성에 의해 매번 새로운 객체가 생성되므로 메모리와 시간 소모가 심함
// 이에 따라서 StringBuilder의 append 메소드를 사용해서 객체 하나에 계속해서 붙히도록 코드를 리팩토링


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
