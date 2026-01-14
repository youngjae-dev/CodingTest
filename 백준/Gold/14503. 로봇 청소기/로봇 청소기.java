import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        int[][] board = new int [N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int answer = 0;
        while(true) {
            if(board[r][c] == 0) {
                board[r][c] = 2;
                ++answer;
            }
            boolean isClean = true;
            for(int i = 0; i < 4; ++i) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if(board[nx][ny] == 0) isClean = false;
                }
            }
            if(isClean) {
                if(dir == 0 && r + 1 < N && board[r+1][c] != 1) r++;
                else if(dir == 1 && c - 1 >= 0 && board[r][c-1] != 1) c--;
                else if(dir == 2 && r - 1 >= 0 && board[r-1][c] != 1) r--;
                else if(dir == 3 && c + 1 < M && board[r][c+1] != 1) c++;
                else break;
            }
            else {
                dir = (dir + 3) % 4;
                if(dir == 0 && r - 1 >= 0 && board[r-1][c] == 0) r--;
                else if(dir == 1 && c + 1 < M && board[r][c+1] == 0) c++;
                else if(dir == 2 && r + 1 < N && board[r+1][c] == 0) r++;
                else if(dir == 3 && c - 1 >= 0 && board[r][c-1] == 0) c--;
            }
        }

        bw.write(answer + "");

        bw.flush();
    }
}