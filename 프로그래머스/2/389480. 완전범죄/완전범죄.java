import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int N = info.length;
        int[][] dp = new int[N+1][n];
        for(int i = 0; i <= N; ++i) Arrays.fill(dp[i], 100000);
        
        dp[0][0] = 0;
        for(int i = 1; i <= N; ++i) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            for(int j = 0; j < n; ++j) {
                if(dp[i-1][j] == 100000) continue;
                
                if(j + a < n) {
                    dp[i][j+a] = Math.min(dp[i][j+a], dp[i-1][j]);
                }
                
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
            }
        }
        
        for(int i = 0; i < n; ++i) {
            if(dp[N][i] < m) return i;
        }
        return -1;
    }
}