import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> adjacent_list = new ArrayList<>();
        for(int i = 0; i <= n; ++i) adjacent_list.add(new ArrayList<>());
        for(int[] arr : edge) {
            adjacent_list.get(arr[0]).add(arr[1]);
            adjacent_list.get(arr[1]).add(arr[0]);
        }
        
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Queue <Integer> queue = new LinkedList<> ();
        
        queue.add(1);
        visited[1] = true;
        int level = 1;
        int max_distance = -1;
        while(!queue.isEmpty()) {
            int start_num = queue.poll();
            level = distance[start_num] + 1;
            
            for(int tmp : adjacent_list.get(start_num)) {
                if(!visited[tmp]){
                    queue.add(tmp);
                    distance[tmp] = level;
                    visited[tmp] = true;
                    if(max_distance < level) max_distance = level;
                }
            }
        }
        for(int tmp : distance) {
            if(tmp == max_distance) ++answer;
        }
        return answer;
    }
}