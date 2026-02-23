import java.util.*;
import java.io.*;

public class Main {
    static boolean[] colCheck = new boolean[15];
    static boolean[] diagonalCheck = new boolean[30];
    static boolean[] reverseDiagonalCheck = new boolean[30];
    static int N;
    static int answer = 0;

    static void DFS(int r) {
        if(r == N) {
            ++answer;
            return;
        }
        for(int i = 0; i < N; ++i) {
            if(colCheck[i] || diagonalCheck[r - i + N] || reverseDiagonalCheck[r + i]) continue;

            colCheck[i] = diagonalCheck[r - i + N] = reverseDiagonalCheck[r + i] = true;
            DFS(r+1);
            colCheck[i] = diagonalCheck[r - i + N] = reverseDiagonalCheck[r + i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        DFS(0);
        System.out.println(answer);
    }
}