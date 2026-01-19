import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] id;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Union {
        int id;
        int sum;
        int size;
        Union(int id) {
            this.id = id;
            this.sum = 0;
            this.size = 0;
        }
    }

    static void bfs(int startR, int startC, Queue<int[]> q, Union union) {
        visited[startR][startC] = true;
        q.offer(new int[] {startR, startC});
        union.sum += map[startR][startC];
        id[startR][startC] = union.id;
        ++union.size;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            for(int i = 0; i < 4; ++i) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                int diff = Math.abs(map[r][c] - map[nx][ny]);
                if(diff >= L && diff <= R) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                    union.sum += map[nx][ny];
                    id[nx][ny] = union.id;
                    ++union.size;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        id = new int[N][N];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j) map[i][j] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            int count = 1;
            ArrayList<Union> list = new ArrayList<>();
            list.add(null);
            Queue<int[]> q = new LinkedList<>();
            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    if(visited[i][j]) continue;
                    Union union = new Union(count++);
                    list.add(union);
                    bfs(i, j, q, union);
                }
            }

            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    Union union = list.get(id[i][j]);
                    map[i][j] = union.sum / union.size;
                    visited[i][j] = false;
                    id[i][j] = 0;
                }
            }

            if(list.size() == N*N + 1) break;
            ++answer;
            list.clear();
        }

        bw.write(answer + "");
        bw.flush();
    }
}