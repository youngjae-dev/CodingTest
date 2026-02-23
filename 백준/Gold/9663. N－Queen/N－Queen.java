import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, -1, -1};
    static int[] dy = {0, -1, 1};
    static boolean[][] visited;
    static int N;
    static int answer = 0;

    static void DFS(int r, int cnt) {
        if(cnt == N) {
            ++answer;
            return;
        }
        for(int i = 0; i < N; ++i) {
            if(!visited[r][i] && checkBoard(r, i)) {
                visited[r][i] = true;
                DFS(r + 1, cnt + 1);
                visited[r][i] = false;
            }
        }
    }
    static boolean checkBoard(int r, int c) {
        for(int i = 0; i < 3; ++i) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if(visited[nx][ny]) return false;
                nx += dx[i];
                ny += dy[i];
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        DFS(0, 0);
        System.out.println(answer);
    }
}