import java.util.*;

class Solution {
    
    class Process {
        int weight;
        char name;
        
        Process(int weight, char name) {
            this.weight = weight;
            this.name = name;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; ++i) {
            q.add(new Process(priorities[i], (char)(i+'A')));
        }
        Arrays.sort(priorities);
        
        int index = priorities.length - 1;
        int count = 0;
        while(true) {
            Process process = q.poll();
            if(process.weight == priorities[index]) {
                if(process.name == location + 'A') {
                ++count;
                break;
                }
                else {
                    ++count;
                    --index;
                }
            }
            else q.add(process);
        }
        
        return count;
    }
}