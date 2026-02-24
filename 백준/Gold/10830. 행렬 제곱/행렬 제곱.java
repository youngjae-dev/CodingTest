import java.util.*;
import java.io.*;

public class Main {
    static long[][] matrix;
    static int N;
    static long B;

    static long[][] multipleMatrix (long[][] A, long[][] B) {
        long[][] answer = new long[N][N];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                for(int k = 0; k < N; ++k) {
                    answer[i][j] = (answer[i][j] + A[i][k] * B[k][j]) % 1000;
                }
            }
        }
        return answer;
    }

    static long[][] pow(long[][] matrix, long exp) {
        if(exp == 1) return matrix;

        long[][] A = pow(matrix, exp / 2);
        long[][] B = multipleMatrix(A, A);

        if(exp % 2 == 1) {
            B = multipleMatrix(B, matrix);
        }
        return B;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        matrix = new long[N][N];

        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j) {
                matrix[i][j] = Long.parseLong(st.nextToken()) % 1000;
            }
        }

        long[][] answer = pow(matrix, B);
        StringBuilder sb = new StringBuilder();
        for(long[] arr : answer) {
            for(long x : arr) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}