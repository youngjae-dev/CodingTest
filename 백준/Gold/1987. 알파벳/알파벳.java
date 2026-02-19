import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static char[][] board;
    static int R;
    static int C;
    static int answer = Integer.MIN_VALUE;
    static boolean[] alphabet = new boolean[26];
    static boolean[][] visited;

    static void DFS(int startR, int startC, int count) {
        answer = Math.max(answer, count);
        for(int i = 0; i < 4; ++i) {
            int nx = startR + dx[i];
            int ny = startC + dy[i];
            if(nx < 1 || nx > R || ny < 1 || ny > C) continue;
            if(alphabet[board[nx][ny] - 'A'] || visited[nx][ny]) continue;
            alphabet[board[nx][ny] - 'A'] = true;
            visited[nx][ny] = true;
            DFS(nx, ny, count + 1);
            alphabet[board[nx][ny] - 'A'] = false;
            visited[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R+1][C+1];
        visited = new boolean[R+1][C+1];
        for(int i = 1; i <= R; ++i) {
            String s = br.readLine();
            for(int j = 1; j <= C; ++j) board[i][j] = s.charAt(j-1);
        }
        alphabet[board[1][1] - 'A'] = true;
        visited[1][1] = true;
        DFS(1, 1, 1);

        System.out.println(answer);
    }
}