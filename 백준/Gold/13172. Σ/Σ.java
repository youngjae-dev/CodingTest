import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 1_000_000_007;

    static long InverseModular(long N, long pow) {
        if(pow == 0) return 1;
        if(pow == 1) return N;

        long half = InverseModular(N, pow / 2);

        long result = (half * half) % MOD;

        if(pow % 2 == 1) {
            result = (result * (N % MOD)) % MOD;
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long M = Long.parseLong(br.readLine());

        long sum = 0;
        for(int i = 0; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());
            sum += ((S % MOD) * (InverseModular(N, MOD-2) % MOD)) % MOD;
            sum %= MOD;
        }
        System.out.println(sum);
    }
}