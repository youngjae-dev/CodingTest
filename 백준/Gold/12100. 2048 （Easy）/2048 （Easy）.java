import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int answer = 0;

    static void play(int[][] board, int moveCount) {
        if(moveCount == 5) {
            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    answer = Math.max(answer, board[i][j]);
                }
            }
            return;
        }

        for(int dir = 0; dir < 4; ++dir) {
            int[][] next = move(dir, board);
            play(next, moveCount + 1);
        }
    }

    static int[][] move(int dir, int[][] board) {
        int[][] nextBoard = new int[N][N];
        // up
        if(dir == 0) {
            for(int col = 0; col < N; ++col) {
                Queue<Integer> q = new LinkedList<>();
                for(int row = 0; row < N; ++row) {
                    if(board[row][col] != 0) q.offer(board[row][col]);
                }
                int index = 0;
                while(!q.isEmpty()) {
                    int value = q.poll();
                    if(!q.isEmpty() && q.peek() == value) {
                        nextBoard[index++][col] = value * 2;
                        q.poll();
                    }
                    else nextBoard[index++][col] = value;
                }
            }
        }
        // down
        else if(dir == 1) {
            for(int col = 0; col < N; ++col) {
                Queue<Integer> q = new LinkedList<>();
                for(int row = N - 1; row >= 0; --row) {
                    if(board[row][col] != 0) q.offer(board[row][col]);
                }
                int index = N - 1;
                while(!q.isEmpty()) {
                    int value = q.poll();
                    if(!q.isEmpty() && q.peek() == value) {
                        nextBoard[index--][col] = value * 2;
                        q.poll();
                    }
                    else nextBoard[index--][col] = value;
                }
            }
        }
        // left
        else if(dir == 2) {
            for(int row = 0; row < N; ++row) {
                Queue<Integer> q = new LinkedList<>();
                for(int col = 0; col < N; ++col) {
                    if(board[row][col] != 0) q.offer(board[row][col]);
                }
                int index = 0;
                while(!q.isEmpty()) {
                    int value = q.poll();
                    if(!q.isEmpty() && q.peek() == value) {
                        nextBoard[row][index++] = value * 2;
                        q.poll();
                    }
                    else nextBoard[row][index++] = value;
                }
            }
        }
        // right
        else if(dir == 3) {
            for(int row = 0; row < N; ++row) {
                Queue<Integer> q = new LinkedList<>();
                for(int col = N - 1; col >= 0; --col) {
                    if(board[row][col] != 0) q.offer(board[row][col]);
                }
                int index = N - 1;
                while(!q.isEmpty()) {
                    int value = q.poll();
                    if(!q.isEmpty() && q.peek() == value) {
                        nextBoard[row][index--] = value * 2;
                        q.poll();
                    }
                    else nextBoard[row][index--] = value;
                }
            }
        }
        return nextBoard;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        int[][] board = new int[N][N];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                board[i][j] = input.nextInt();
            }
        }
        play(board, 0);
        System.out.println(answer);
    }
}