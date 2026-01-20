import java.io.*;
import java.util.*;

public class Main {
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
            switch(dir) {
                // east
                case 1: {
                    if (dice.col + 1 >= M) continue;
                    ++dice.col;
                    int tmp = dice.numbers[3];
                    dice.numbers[3] = dice.numbers[5];
                    dice.numbers[5] = dice.numbers[2];
                    dice.numbers[2] = dice.numbers[0];
                    dice.numbers[0] = tmp;
                    update(board, dice);
                    break;
                }
                // west
                case 2: {
                    if (dice.col - 1 < 0) continue;
                    --dice.col;
                    int tmp = dice.numbers[2];
                    dice.numbers[2] = dice.numbers[5];
                    dice.numbers[5] = dice.numbers[3];
                    dice.numbers[3] = dice.numbers[0];
                    dice.numbers[0] = tmp;
                    update(board, dice);
                    break;
                }
                // north
                case 3: {
                    if (dice.row - 1 < 0) continue;
                    --dice.row;
                    int tmp = dice.numbers[4];
                    dice.numbers[4] = dice.numbers[5];
                    dice.numbers[5] = dice.numbers[1];
                    dice.numbers[1] = dice.numbers[0];
                    dice.numbers[0] = tmp;
                    update(board, dice);
                    break;
                }
                // south
                case 4: {
                    if (dice.row + 1 >= N) continue;
                    int tmp = dice.numbers[1];
                    dice.numbers[1] = dice.numbers[5];
                    dice.numbers[5] = dice.numbers[4];
                    dice.numbers[4] = dice.numbers[0];
                    dice.numbers[0] = tmp;
                    ++dice.row;
                    update(board, dice);
                    break;
                }
            }
            bw.write(dice.numbers[0] + "\n");
        }

        bw.flush();
    }
}