import java.util.*;

class Solution {
    final int MAX = 1_000_000;
    
    int[] inDegree = new int[MAX + 1];
    int[] outDegree = new int[MAX + 1];
    int maxNum = 0;
    
    public int[] solution(int[][] edges) {
        for(int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            
            inDegree[end]++;
            outDegree[start]++;
            
            maxNum = Math.max(maxNum, Math.max(start, end));
        }
        
        int[] answer = new int[4];
        for(int i = 1; i <= maxNum; ++i) {
            if(inDegree[i] == 0 && outDegree[i] == 0) continue;
            
            if(inDegree[i] == 0 && outDegree[i] >= 2) answer[0] = i;
            
            else if(outDegree[i] == 0) answer[2]++;
            
            else if(inDegree[i] >= 2 && outDegree[i] == 2) answer[3]++;
        }
        
        answer[1] = outDegree[answer[0]] - answer[2] - answer[3];
        
        return answer;
    }
}