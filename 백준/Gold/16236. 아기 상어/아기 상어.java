import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Shark {
        int row;
        int col;
        int size;
        int count;
        Shark(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.count = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        Shark shark = new Shark(0, 0, 0);
        int fishCount = 0;
        for(int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    shark = new Shark(i, j, 2);
                    board[i][j] = 0;
                }
                else if(board[i][j] != 0) ++fishCount;
            }
        }

        int answer = 0;
        while(fishCount != 0) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{shark.row, shark.col, 0});

            boolean[][] visited = new boolean[N][N];
            visited[shark.row][shark.col] = true;

            ArrayList<int[]> list = new ArrayList<>();
            while (!q.isEmpty()) {
                int[] tmp = q.poll();
                int r = tmp[0];
                int c = tmp[1];
                int dist = tmp[2];
                for (int i = 0; i < 4; ++i) {
                    int nx = r + dx[i];
                    int ny = c + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            if (board[nx][ny] <= shark.size) {
                                if(board[nx][ny] != 0 && board[nx][ny] < shark.size) list.add(new int[]{nx, ny, dist + 1});
                                q.offer(new int[]{nx, ny, dist + 1});
                            }
                        }
                    }
                }
            }
            if(list.isEmpty()) break;
            Collections.sort(list, (a, b) -> {
                if(a[2] != b[2]) return a[2]-b[2];
                if(a[0] != b[0]) return a[0]-b[0];
                return a[1]-b[1];
            });
            int[] pos = list.get(0);
            shark.row = pos[0];
            shark.col = pos[1];
            answer += pos[2];
            --fishCount;
            board[pos[0]][pos[1]] = 0;
            if(++shark.count == shark.size) {
                shark.size++;
                shark.count = 0;
            }
        }

        bw.write(answer + "");
        bw.flush();
    }
}