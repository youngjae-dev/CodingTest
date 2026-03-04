class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        int answer = 0;
        for(int t = startTime; t < endTime; ++t) {
            double curH = (t * 30.0 / 3600.0) % 360;
            double curM = (t * 6.0 / 60.0) % 360;
            double curS = (t * 6.0) % 360;
            
            double nextH = ((t + 1) * 30.0 / 3600.0) % 360;
            double nextM = ((t + 1) * 6.0 / 60.0) % 360;
            double nextS = ((t + 1) * 6.0) % 360;
            
            if(nextH == 0) nextH = 360;
            if(nextM == 0) nextM = 360;
            if(nextS == 0) nextS = 360;
            
            boolean hMatch = (curS < curH) && (nextS >= nextH);
            boolean mMatch = (curS < curM) && (nextS >= nextM);
            
            if(hMatch && mMatch) {
                ++answer;
                if(nextH != nextM) ++answer;
            }
            else if(hMatch || mMatch) ++answer;
            
        }
        
        if(startTime == 0 || startTime == 12 * 3600) ++answer;
        return answer;
    }
}