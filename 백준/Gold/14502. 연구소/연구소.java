import java.io.*;
import java.util.*;

// 원래는 dfs의 인자로 count만 넘겨줌으로써 함수 내부에서 2중 for문을 사용했었으나, 이 경우 1000ms를 초과해 코드가 비효율적이라고 판단하여 리팩토링하였다.
// 리팩토링을 할 때, 1000ms라는 긴 시간이 나온 원인이 dfs라고 판단하여 그 내부 함수를 어떻게 하면 더 최적화 시킬 수 있을까 고민하다가, 인자로 count뿐만이 아닌 start로 시작점도 함께 넘겨주며
// 중복 탐색을 방지할 수 있도록 리팩토링 하였다.

public class Main {
    static int N = 0;
    static int M = 0;
    static int[][] field;
    static int answer = Integer.MIN_VALUE;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static void dfs(int start, int count) {
        if(count == 3) {
            bfs();
            return;
        }
        for(int i = start; i < N * M; ++i) {
            int row = i / M;
            int col = i % M;
            if(field[row][col] == 0) {
                field[row][col] = 1;
                dfs(i + 1, count + 1);
                field[row][col] = 0;
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

        dfs(0, 0);
        bw.write(answer + "");

        bw.flush();
    }

}
