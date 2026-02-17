import java.util.*;

class Solution {
    long[] bansToNumber(String[] bans) {
        long[] stringToNumber = new long[bans.length];
        for(int i = 0; i < bans.length; ++i) {
            stringToNumber[i] = toNumber(bans[i]);
        }
        return stringToNumber;
    }
    
    long toNumber(String s) {
        long res = 0;
        for(int i = 0; i < s.length(); ++i) {
            res = res * 26 + (s.charAt(i) - 'a' + 1);
        }
        return res;
    }
    
    String numToString(long n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            --n;
            sb.append((char)('a' + (n % 26)));
            n /= 26;
        }
        return sb.reverse().toString();
    }
    
    public String solution(long n, String[] bans) {
        long[] bansToNumber = bansToNumber(bans);
        Arrays.sort(bansToNumber);
        
        for(long l : bansToNumber) {
            if(l <= n) ++n;
            else break;
        }
        
        return numToString(n);
    }
}