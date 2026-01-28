import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static void spreadDust(int[][] board) {
        int[][] dustCount = new int[R][C];
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                if(board[i][j] == 0 || board[i][j] == -1) continue;

                int spread = board[i][j] / 5;
                int count = 0;

                for(int dir = 0; dir < 4; ++dir) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C || board[nx][ny] == -1) continue;
                    dustCount[nx][ny] += spread;
                    ++count;
                }
                board[i][j] -= spread * count;
            }
        }
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) board[i][j] += dustCount[i][j];
        }
    }
    static void operationMachine(int[][] board, ArrayList<int[]> machine_pos) {
        int upRow = machine_pos.get(0)[0];

        for(int i = upRow - 1; i > 0; --i) board[i][0] = board[i-1][0];
        for(int i = 0; i < C - 1; ++i) board[0][i] = board[0][i+1];
        for(int i = 0; i < upRow; ++i) board[i][C-1] = board[i+1][C-1];
        for(int i = C - 1; i > 1; --i) board[upRow][i] = board[upRow][i-1];
        board[upRow][1] = 0;

        int downRow = machine_pos.get(1)[0];
        for(int i = downRow + 1; i < R - 1; ++i) board[i][0] = board[i+1][0];
        for(int i = 0; i < C - 1; ++i) board[R-1][i] = board[R-1][i+1];
        for(int i = R-1; i > downRow; --i) board[i][C-1] = board[i-1][C-1];
        for(int i = C - 1; i > 1; --i) board[downRow][i] = board[downRow][i-1];
        board[downRow][1] = 0;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        R = input.nextInt();
        C = input.nextInt();
        int T = input.nextInt();
        ArrayList<int[]> machine_pos = new ArrayList<>();

        int[][] board = new int[R][C];
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                board[i][j] = input.nextInt();
                if(board[i][j] == -1) machine_pos.add(new int[] {i, j});
            }
        }

        for(int i = 0; i < T; ++i) {
            spreadDust(board);
            operationMachine(board, machine_pos);
        }
        int answer = 0;
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                if(board[i][j] == -1) continue;
                answer += board[i][j];
            }
        }
        System.out.println(answer);
    }
}