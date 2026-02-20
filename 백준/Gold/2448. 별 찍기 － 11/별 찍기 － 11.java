import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static char[][] board;

    static void solution(int row, int col, int n) {
        if(n == 3) {
            printStar(row, col);
            return;
        }
        solution(row, col, n/2);
        solution(row + n/2, col + n/2, n/2);
        solution(row + n/2, col - n/2, n/2);
    }

    static void printStar(int row, int col) {
        board[row][col] = '*';
        board[row+1][col-1] = '*';
        board[row+1][col+1] = '*';
        for(int i = col-2; i <= col +2; ++i) {
            board[row+2][i] = '*';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new char[N][2*N-1];
        for(int i = 0; i < N; ++i) Arrays.fill(board[i], ' ');
        solution(0, N - 1, N);
        StringBuilder sb = new StringBuilder();
        for(char[] arr : board) {
            for(char c : arr) {
                sb.append(c);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}