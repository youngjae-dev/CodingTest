import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());

        int[] dpUp = new int[N];
        for(int i = 0; i < N; ++i) {
            dpUp[i] = 1;
            for(int j = 0; j < i; ++j) {
                if(arr[i] > arr[j]) dpUp[i] = Math.max(dpUp[i], dpUp[j] + 1);
            }
        }

        int[] dpDown = new int[N];
        for(int i = N-1; i >= 0; --i) {
            dpDown[i] = 1;
            for(int j = N-1; j > i; --j) {
                if(arr[i] > arr[j]) dpDown[i] = Math.max(dpDown[i], dpDown[j] + 1);
            }
        }

        int answer = 0;
        for(int i = 0; i < N; ++i) {
            answer = Math.max(answer, dpUp[i] + dpDown[i] - 1);
        }
        System.out.println(answer);
    }
}