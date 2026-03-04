import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i) A.add(Integer.parseInt(st.nextToken()));


        int M = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; ++i) B.add(Integer.parseInt(st.nextToken()));

        List<Integer> result = new ArrayList<>();

        while(!A.isEmpty() && !B.isEmpty()) {
            int maxNum = -1;

            for(int x : A) {
                if(B.contains(x)) maxNum = Math.max(maxNum, x);
            }

            if(maxNum == -1) break;

            result.add(maxNum);

            int idxA = A.indexOf(maxNum);
            int idxB = B.indexOf(maxNum);

            A = A.subList(idxA + 1, A.size());
            B = B.subList(idxB + 1, B.size());
        }

        System.out.println(result.size());
        StringBuilder sb = new StringBuilder();
        if(!result.isEmpty()) {
            for(int x : result) sb.append(x).append(" ");
            System.out.println(sb);
        }
    }
}