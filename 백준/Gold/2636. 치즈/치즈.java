import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int time;
    static int count;
    static int area;

    static int R;
    static int C;
    static int[][] board;
    static int[][] copy_board;

    static int BFS() {
        int cnt = 0;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            for(int i = 0; i < 4; ++i) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if(board[nx][ny] == 0) q.offer(new int[] {nx, ny});
                else if(board[nx][ny] == 1) {
                    copy_board[nx][ny] = 1;
                    ++cnt;
                }
            }
        }
        return cnt;
    }
    static void afterOneSecond() {
        ++time;
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                if(copy_board[i][j] == 1) {
                    copy_board[i][j] = 0;
                    board[i][j] = 0;
                    --area;
                }
            }
        }
    }
    static int play() {
        int answer = 0;
        while(true) {
            answer = BFS();
            if(area - answer == 0) break;
            afterOneSecond();
        }
        return answer;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        R = input.nextInt();
        C = input.nextInt();
        board = new int[R][C];
        copy_board = new int[R][C];
        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                board[i][j] = input.nextInt();
                if(board[i][j] == 1) ++area;
            }
        }
        int answer = play();
        System.out.println(time + 1);
        System.out.println(answer);
    }
}