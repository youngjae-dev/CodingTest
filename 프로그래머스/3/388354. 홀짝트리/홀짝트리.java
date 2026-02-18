import java.util.*;

class Solution {
    int[] head = new int[1000001];
    int[] to = new int[2000001];
    int[] next = new int[2000001];
    int[] degree = new int[1000001];
    boolean[] visited = new boolean[1000001];
    int edgeCount = 1;
    
    public void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount;
        degree[u]++;
        ++edgeCount;
    }
    
    public void checkTree(int startNum, int[] answer) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(startNum);
        visited[startNum] = true;
        
        int oddEvenNode = 0;
        int reverseOddEvenNode = 0;
        
        while(!q.isEmpty()) {
            int num = q.poll();
            
            if(num % 2 == degree[num] % 2) ++oddEvenNode;
            if(num % 2 != degree[num] % 2) ++reverseOddEvenNode;
            
            for(int i = head[num]; i != 0; i = next[i]) {
                int nextNode = to[i];
                if(visited[nextNode]) continue;
                visited[nextNode] = true;
                q.offer(nextNode);
            }
        }
        if(oddEvenNode == 1) ++answer[0];
        if(reverseOddEvenNode == 1) ++answer[1];
    }
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        for(int[] arr : edges) {
            int a = arr[0];
            int b = arr[1];
            addEdge(a, b);
            addEdge(b, a);
        }
        for(int x : nodes) {
            if(visited[x]) continue;
            checkTree(x, answer);
        }
        return answer;
    }
}