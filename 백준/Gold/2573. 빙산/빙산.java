import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int M;
    static int count;

    static void meltIce(int[][] map, int[][] prev_map, int row, int col) {
        for(int i = 0; i < 4; ++i) {
            int nx = row + dx[i];
            int ny = col + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(prev_map[nx][ny] == 0 && map[row][col] > 0) map[row][col]--;
        }
    }

    static int after1Year(int[][] map, int[][] prev_map, boolean[][] visited) {
        count = 0;

        for(int i = 1; i < N; ++i) {
            for(int j = 1; j < M; ++j) {
                if(prev_map[i][j] == 0 || visited[i][j]) continue;

                ++count;
                Queue<int[]> q = new LinkedList<>();
                visited[i][j] = true;
                q.offer(new int[] {i, j});
                while(!q.isEmpty()) {
                    int[] start = q.poll();
                    int r = start[0];
                    int c = start[1];
                    meltIce(map, prev_map, r, c);
                    for(int k = 0; k < 4; ++k) {
                        int nx = r + dx[k];
                        int ny = c + dy[k];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || prev_map[nx][ny] == 0) continue;
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] prev_map = new int[N][M];
        for(int i = 0; i < N; ++i) prev_map[i] = map[i].clone();


        int answer = 0;
        while(true) {
            boolean[][] visited = new boolean[N][M];
            int count = after1Year(map, prev_map, visited);
            if(count == 0) {
                bw.write("0");
                break;
            }
            else if(count >= 2) {
                bw.write(answer + "");
                break;
            }
            ++answer;
            for(int i = 0; i < N; ++i) prev_map[i] = map[i].clone();
        }
        bw.flush();
    }
}