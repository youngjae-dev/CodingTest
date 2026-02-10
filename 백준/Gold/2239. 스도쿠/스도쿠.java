import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static ArrayList<int[]> list = new ArrayList<>();
    static boolean isValid(int row, int col, int num) {
        for(int i = 0; i < 9; ++i) {
            if(board[row][i] == num) return false;
            if(board[i][col] == num) return false;

            int r = (row / 3) * 3 + i / 3;
            int c = (col / 3) * 3 + i % 3;
            if(board[r][c] == num) return false;
        }
        return true;
    }

    static void sudoku(int index) {
        if(index == list.size()) {
            for(int i = 0; i < 9; ++i) {
                for(int j = 0; j < 9; ++j) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        }
        int r = list.get(index)[0];
        int c = list.get(index)[1];
        for(int i = 1; i <= 9; ++i) {
            if(isValid(r, c, i)) {
                board[r][c] = i;
                sudoku(index + 1);
                board[r][c] = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < 9; ++i) {
            String s = input.next();
            for(int j = 0; j < 9; ++j) {
                board[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
                if(board[i][j] == 0) list.add(new int[] {i, j});
            }
        }
        sudoku(0);
    }
}