import java.io.*;
import java.util.*;

public class Main {
    static int N = 0;
    static int M = 0;
    static int[][] field;
    static int answer = Integer.MIN_VALUE;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static void dfs(int count) {
        if(count == 3) {
            bfs();
            return;
        }
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                if(field[i][j] == 0) {
                    field[i][j] = 1;
                    dfs(count + 1);
                    field[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        int[][] map = new int [N][M];
        ArrayList<int[]> virus = new ArrayList<>();
        int area = 0;
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                map[i][j] = field[i][j];
                if(map[i][j] == 2) virus.add(new int[] {i,j});
                else if(map[i][j] == 0) ++area;
            }
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < virus.size(); ++i) {
            int[] tmp = virus.get(i);
            int row = tmp[0];
            int col = tmp[1];
            visited[row][col] = true;
            q.offer(new int[] {row, col});
        }
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int row = tmp[0];
            int col = tmp[1];
            for(int i = 0; i < 4; ++i) {
                int ny = row + dy[i];
                int nx = col + dx[i];
                if(ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if(map[ny][nx] == 0 && !visited[ny][nx]) {
                        visited[ny][nx]= true;
                        --area;
                        q.offer(new int[] {ny, nx});
                    }
                }
            }
        }
        answer = Math.max(answer, area);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int [N][M];
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; ++j) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        bw.write(answer + "");

        bw.flush();
    }
}