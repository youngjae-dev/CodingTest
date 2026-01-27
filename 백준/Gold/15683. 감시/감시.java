import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = Integer.MAX_VALUE;

    static void backTracking(int[][] board, ArrayList<int[]> cctv_pos, int start_index) {
        if(start_index == cctv_pos.size()) {
            int cnt = 0;
            for(int i = 0; i < board.length; ++i) {
                for(int j = 0; j < board[0].length; ++j) {
                    if(board[i][j] == 0) ++cnt;
                }
            }
            answer = Math.min(answer, cnt);
            return;
        }

        int[] arr = cctv_pos.get(start_index);
        int r = arr[0];
        int c = arr[1];

        if(board[r][c] == 1) {
            backTracking(cctv(board, 1, r, c, 0), cctv_pos, start_index + 1);
            backTracking(cctv(board, 1, r, c, 1), cctv_pos, start_index + 1);
            backTracking(cctv(board, 1, r, c, 2), cctv_pos, start_index + 1);
            backTracking(cctv(board, 1, r, c, 3), cctv_pos, start_index + 1);
        }
        else if(board[r][c] == 2) {
            backTracking(cctv(board, 2, r, c, 0), cctv_pos, start_index + 1);
            backTracking(cctv(board, 2, r, c, 1), cctv_pos, start_index + 1);
        }
        else if(board[r][c] == 3) {
            backTracking(cctv(board, 3, r, c, 0), cctv_pos, start_index + 1);
            backTracking(cctv(board, 3, r, c, 1), cctv_pos, start_index + 1);
            backTracking(cctv(board, 3, r, c, 2), cctv_pos, start_index + 1);
            backTracking(cctv(board, 3, r, c, 3), cctv_pos, start_index + 1);
        }
        else if(board[r][c] == 4) {
            backTracking(cctv(board, 4, r, c, 0), cctv_pos, start_index + 1);
            backTracking(cctv(board, 4, r, c, 1), cctv_pos, start_index + 1);
            backTracking(cctv(board, 4, r, c, 2), cctv_pos, start_index + 1);
            backTracking(cctv(board, 4, r, c, 3), cctv_pos, start_index + 1);
        }
        else if(board[r][c] == 5) {
            backTracking(cctv(board, 5, r, c, 0), cctv_pos, start_index + 1);
        }

    }
    static int[][] cctv(int[][] board, int num, int row, int col, int dir) {
        int[][] next = new int[board.length][];
        for(int j = 0; j < board.length; ++j) next[j] = board[j].clone();

        if(num == 1) {
            watch(next, row, col, dir);
        }
        else if(num == 2) {
            watch(next, row, col, dir);
            watch(next, row, col, (dir + 2) % 4);
        }
        else if(num == 3) {
            watch(next, row, col, dir);
            watch(next, row, col, (dir + 1) % 4);
        }
        else if(num == 4) {
            watch(next, row, col, dir);
            watch(next, row, col, (dir + 1) % 4);
            watch(next, row, col, (dir + 3) % 4);
        }
        else if(num == 5) {
            watch(next, row, col, dir);
            watch(next, row, col, (dir + 1) % 4);
            watch(next, row, col, (dir + 2) % 4);
            watch(next, row, col, (dir + 3) % 4);
        }
        return next;
    }
    static void watch(int[][] board, int row, int col, int dir) {
        while(row +dx[dir] >= 0 && row + dx[dir] < board.length
                && col + dy[dir] >= 0 && col + dy[dir] < board[0].length) {

            row = row + dx[dir];
            col = col + dy[dir];
            if(board[row][col] >= 1 && board[row][col] <= 5) continue;
            if(board[row][col] == 6) return;
            board[row][col] = '#';
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();

        int[][] board = new int[N][M];
        ArrayList<int[]> cctv_pos = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                board[i][j] = input.nextInt();
                if(board[i][j] >= 1 && board[i][j] <= 5) cctv_pos.add(new int[] {i ,j});
            }
        }
        backTracking(board, cctv_pos, 0);
        System.out.print(answer);
    }
}