import java.util.*;

class Solution {
    ArrayList<int[]> candidates = new ArrayList<>();
    void combination(int start, int index, int n, int[] arr) {
        if(index == 5) {
            candidates.add(arr.clone());
            return;
        }
        for(int i = start; i <= n; ++i) {
            arr[index] = i;
            combination(i + 1, index + 1, n , arr);
        }
    }
    boolean possible(int[] arr, int[][] q, int[] ans) {
        boolean flag = true;
        HashSet<Integer> set = new HashSet<>();
        for(int x : arr) set.add(x);
        
        int m = ans.length;
        for(int i = 0; i < m; ++i) {
            int[] list = q[i];
            int correct = ans[i];
            int cnt = 0;
            for(int j = 0; j < 5; ++j) {
                if(set.contains(list[j])) ++cnt;
            }
            if(correct != cnt) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    public int solution(int n, int[][] q, int[] ans) {
        combination(1, 0, n, new int[5]);
        
        int answer = 0;
        for(int[] arr : candidates) {
            if(possible(arr, q, ans)) ++answer;
        }
        return answer;
    }
}