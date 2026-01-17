import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[9][9];
        for(int i = 0; i < 9; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sudoku(0, 0);
        bw.flush();
    }

    static boolean sudoku(int row, int col) {
        if(col == 9) return sudoku(row + 1, 0);

        if(row == 9) {
            for(int[] arr : board) {
                for(int x : arr) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        if(board[row][col] == 0) {
            for(int i = 1; i <= 9; ++i) {
                if(isPossible(row, col, i)) {
                    board[row][col] = i;
                    if(sudoku(row, col + 1)) return true;
                    board[row][col] = 0;
                }
            }
            return false;
        }
        else return sudoku(row, col + 1);
    }
    static boolean isPossible(int row, int col, int val) {
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for(int i = 0; i < 9; ++i) {
            if(board[i][col] == val) return false;
            if(board[row][i] == val) return false;
            int startRow = boxRow + i / 3;
            int startCol = boxCol + i % 3;
            if(board[startRow][startCol] == val) return false;
        }
        return true;
    }
}