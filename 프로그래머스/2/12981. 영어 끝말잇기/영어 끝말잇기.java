import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        HashSet<String> voca = new HashSet<>();
        int[] user = new int [n];
        
        int user_num = 0;
        char last_alphabet = words[0].charAt(0);
        for(String s : words) {
            if(voca.contains(s) ||
              last_alphabet != s.charAt(0)){
                answer[0] = user_num + 1;
                answer[1] = user[user_num] + 1;
                break;
            }
            else {
                voca.add(s);
                ++user[user_num];
                user_num = (user_num + 1) % n;
                last_alphabet = s.charAt(s.length()-1);
            }
        }
        return answer;
    }
}