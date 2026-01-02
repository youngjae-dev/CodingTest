import java.util.*;

class Solution {
    public int networkSize(int start_num, boolean[] visited, ArrayList<ArrayList<Integer>> adjacent_list) {
        int size = 0;
        
        Queue<Integer> q = new LinkedList<>();
    
        q.add(start_num);
        visited[start_num] = true;
        ++size;
        
        while(!q.isEmpty()) {
            int num = q.poll();
            for(int adjacent_node : adjacent_list.get(num)) {
                if(!visited[adjacent_node]) {
                    q.add(adjacent_node);
                    ++size;
                    visited[adjacent_node] = true;
                }
            }
        }
        return size;
            
    }
    public int solution(int n, int[][] wires) {
        
        ArrayList<ArrayList<Integer>> adjacent_list = new ArrayList<>();
        
        for(int i = 0; i <= n; ++i) {
            adjacent_list.add(new ArrayList<>());
        }
        for(int i = 0; i < wires.length; ++i) {
            int start = wires[i][0];
            int end = wires[i][1];
            adjacent_list.get(start).add(end);
            adjacent_list.get(end).add(start);
        }
        
        
        int min_diff = Integer.MAX_VALUE;
        
        for(int i = 0; i < wires.length; ++i) {
            
            int useless_node1 = wires[i][0];
            int useless_node2 = wires[i][1];
            
            boolean[] visited = new boolean[n+1];
            
            adjacent_list.get(useless_node1).remove(Integer.valueOf(useless_node2));
            adjacent_list.get(useless_node2).remove(Integer.valueOf(useless_node1));
                
            int size1 = networkSize(1, visited, adjacent_list);
            int size2 = n - size1;
            int diff = Math.abs(size1 - size2);
            min_diff = Math.min(min_diff, diff);
                
            adjacent_list.get(useless_node1).add(useless_node2);
            adjacent_list.get(useless_node2).add(useless_node1);
        }
        
        return min_diff;
    }
}