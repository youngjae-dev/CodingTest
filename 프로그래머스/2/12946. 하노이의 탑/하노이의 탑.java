import java.util.*;

class Solution {
    int[][] answer;
    int count;
    
    public int[][] solution(int n) {
        answer = new int[(int)Math.pow(2,n) - 1][2];
        
        towerOfHanoi(n, 1, 3);
        return answer;
    }
    void towerOfHanoi(int n, int start, int end) {
        if(n == 1) {
            answer[count][0] = start;
            answer[count][1] = end;
            ++count;
            return;
        }
        else {
            int left = 6 - start - end;
            towerOfHanoi(n - 1, start, left);
            towerOfHanoi(1, start, end);
            towerOfHanoi(n - 1, left, end);
        }
    }
}