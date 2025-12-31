import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            }
            else if(c == ')') {
                if(stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        if(!stack.isEmpty()) answer = false;
        return answer;
    }
}