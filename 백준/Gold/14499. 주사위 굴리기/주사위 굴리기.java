import java.io.*;
import java.util.*;

// 문제에 대한 회고 : 동서남북 각각 방향에 대해 swtich문으로 구현하였으나 코드의 가독성도 떨어지고 길이도 길어져서 이를 모듈화를 시킴으로써 가독성을 올리고 코드의 길이도 줄일 수 있었다

public class Main {
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static class Dice {
        int[] numbers;
        int row;
        int col;
        Dice(int row, int col) {
            this.numbers = new int[6];
            this.row = row;
            this.col = col;
        }
    }

    static void roll(int dir, Dice dice) {
        int[] d = dice.numbers;
        int tmp = d[0];
        // east
        if (dir == 1) {
            d[0] = d[3]; d[3] = d[5]; d[5] = d[2]; d[2] = tmp;
        }
        // west
        else if (dir == 2) {
            d[0] = d[2]; d[2] = d[5]; d[5] = d[3]; d[3] = tmp;
        }
        // north
        else if (dir == 3) {
            d[0] = d[4]; d[4] = d[5]; d[5] = d[1]; d[1] = tmp;
        }
        // south
        else {
            d[0] = d[1]; d[1] = d[5]; d[5] = d[4]; d[4] = tmp;
        }
    }

    static void update(int[][] board, Dice dice) {
        if(board[dice.row][dice.col] == 0) {
            board[dice.row][dice.col] = dice.numbers[5];
        }
        else {
            dice.numbers[5] = board[dice.row][dice.col];
            board[dice.row][dice.col] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; ++j) board[i][j] = Integer.parseInt(st.nextToken());
        }
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; ++i) order[i] = Integer.parseInt(st.nextToken());

        Dice dice = new Dice(x, y);
        for(int dir : order) {
            int nx = dice.row + dx[dir];
            int ny = dice.col + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            dice.row = nx;
            dice.col = ny;
            roll(dir, dice);
            update(board, dice);
            bw.write(dice.numbers[0] + "\n");
        }

        bw.flush();
    }

}
