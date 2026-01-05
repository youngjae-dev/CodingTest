import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] cost = new int [n+1][n+1];
        int max_cost = 200 * 100000 + 1;
        
        for(int i = 1; i <= n; ++i) {
            Arrays.fill(cost[i], max_cost);
            cost[i][i] = 0;
        }
        
        for(int[] fare : fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }
        
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                for(int k = 1; k <= n; ++k) {
                    if(cost[j][k] > (cost[j][i] + cost[i][k])) {
                        cost[j][k] = cost[j][i] + cost[i][k];
                    }
                }
            }
        }
        
        int answer = cost[s][a] + cost[s][b];
        
        for(int i = 1; i <= n; ++i) {
            answer = Math.min(answer, cost[s][i] + cost[i][a] + cost[i][b]);
        }
        
        return answer;
    }
}