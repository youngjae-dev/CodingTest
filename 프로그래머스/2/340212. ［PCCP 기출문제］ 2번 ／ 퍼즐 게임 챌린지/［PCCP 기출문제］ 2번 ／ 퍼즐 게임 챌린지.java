class Solution {
    long solvePuzzle(int diff, int level, int time_cur, int time_prev) {
        if(diff <= level) return time_cur;
        else return (long)(diff - level) * (time_cur + time_prev) + time_cur;
    }
    
    boolean solveAll(int[] diffs, int[] times, long limit, int level) {
        long sum = times[0];
        int N = diffs.length;
        
        for(int i = 1; i < N; ++i) {
            sum += solvePuzzle(diffs[i], level, times[i], times[i-1]);
        }
        if(sum <= limit) return true;
        else return false;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int maxLevel = 0;
        for(int x : diffs) maxLevel = Math.max(maxLevel, x);
        int minLevel = 1;
        
        int answer = maxLevel;
        while(minLevel <= maxLevel) {
            int mid = (minLevel + maxLevel) / 2;
            if(solveAll(diffs, times, limit, mid)) {
                answer = mid;
                maxLevel = mid - 1;
            }
            else minLevel = mid + 1;
        }
        
        return answer;
    }
}