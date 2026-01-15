import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int currRow = 1;
    static int currCol = 1;
    static int currDir = 1;

    static class Log {
        int second;
        char rotation;
        Log(int second, char rotation) {
            this.second = second;
            this.rotation = rotation;
        }
    }

    static boolean moveForward(int row, int col, int dir) {
        if(dir == 0) {
            if(row - 1 <= 0 || board[row-1][col] == 1) return false;
            else currRow--;
        }
        else if(dir == 1) {
            if(col + 1 > N || board[row][col+1] == 1) return false;
            else currCol++;
        }
        else if(dir == 2) {
            if(row + 1 > N || board[row+1][col] == 1) return false;
            else currRow++;
        }
        else if(dir == 3) {
            if(col - 1 <= 0 || board[row][col-1] == 1) return false;
            else currCol--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        board = new int [N + 1][N + 1];
        for(int i = 0; i < K; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 2;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<Log> q = new LinkedList<>();
        for(int i = 0; i < L; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char rotation = st.nextToken().charAt(0);
            q.offer(new Log(second, rotation));
        }

        Deque<int[]> deque = new LinkedList<>();
        deque.offerFirst(new int[] {1, 1});
        board[1][1] = 1;

        int count = 0;
        Log log = q.poll();
        int logSecond = log.second;
        char logRotation = log.rotation;
        while(true) {
            ++count;
            boolean flag = moveForward(currRow, currCol, currDir);
            if(!flag) break;
            if(board[currRow][currCol] == 2) {
                deque.offerFirst(new int[] {currRow, currCol});
                board[currRow][currCol] = 1;
            }
            else{
                deque.offerFirst(new int[] {currRow, currCol});
                board[currRow][currCol] = 1;
                int[] tmp = deque.pollLast();
                board[tmp[0]][tmp[1]] = 0;
            }
            if(count == logSecond) {
                if(logRotation == 'D') {
                    currDir = (currDir + 1) % 4;
                }
                else if(logRotation == 'L') {
                    currDir = (currDir + 3) % 4;
                }
                if(!q.isEmpty()) {
                    log = q.poll();
                    logSecond = log.second;
                    logRotation = log.rotation;
                }
            }

        }

        bw.write(count + "");

        bw.flush();
    }
}