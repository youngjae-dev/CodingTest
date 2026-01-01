import java.util.*;

public class Main {
//    public static String solution(String str){
//
//    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int S = input.nextInt();
        int P = input.nextInt();
        String str = input.next();
        int[] require_count = new int [4];
        for(int i = 0; i < 4; ++i) {
            require_count[i] = input.nextInt();
        }

        int lt = 0;
        int rt = lt + P - 1;

        int answer = 0;

        int[] curr_count = new int [4];

        char[] str_to_array = str.toCharArray();

        boolean flag = true;

        for(int i = lt; i <= rt; ++i) {
            char c = str_to_array[i];
            if(c == 'A') ++curr_count[0];
            else if(c == 'C') ++curr_count[1];
            else if(c == 'G') ++curr_count[2];
            else if(c == 'T') ++curr_count[3];
            else flag = false;
        }
        if(curr_count[0] >=require_count[0]
                && curr_count[1] >= require_count[1]
                && curr_count[2] >= require_count[2]
                && curr_count[3] >= require_count[3]
                && flag) ++answer;

        ++lt;
        ++rt;

        flag = true;

        while(rt < S) {
            char prev_c = str_to_array[lt-1];
            char next_c = str_to_array[rt];

            if(prev_c == 'A') --curr_count[0];
            else if(prev_c == 'C') --curr_count[1];
            else if(prev_c == 'G') --curr_count[2];
            else if(prev_c == 'T') --curr_count[3];
            else flag = true;

            if(next_c == 'A') ++curr_count[0];
            else if(next_c == 'C') ++curr_count[1];
            else if(next_c == 'G') ++curr_count[2];
            else if(next_c == 'T') ++curr_count[3];
            else flag = false;

            if(curr_count[0] >=require_count[0]
                    && curr_count[1] >= require_count[1]
                    && curr_count[2] >= require_count[2]
                    && curr_count[3] >= require_count[3]
                    && flag) ++answer;
            ++lt;
            ++rt;
        }
        System.out.println(answer);
    }
}