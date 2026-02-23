import java.util.*;

class Solution {
    class Server{
        int createTime;
        int deadTime;
        Server(int createTime, int lifeTime) {
            this.createTime = createTime;
            this.deadTime = createTime + lifeTime;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Server> q = new ArrayDeque<>();
        for(int i = 0; i < 24; ++i) {
            while(!q.isEmpty() && q.peek().deadTime <= i) q.poll();
            
            int x = players[i];
            if(x >= m) {
                int cnt = x / m - q.size();
                for(int j = 0; j < cnt; ++j) {
                    q.offer(new Server(i, k));
                    ++answer;
                }
            }
        }
        return answer;
    }
}