import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static int M;
    static int[][] board;

    static void connect(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int row = tmp[0];
            int col = tmp[1];
            for(int i = 0; i < 4; ++i) {
                int nx = row + dx[i];
                int ny = col + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] != 0) continue;
                board[nx][ny] = 2;
                q.offer(new int[] {nx, ny});
            }
        }
    }

    static Queue<int[]> afterOneHour(Queue<int[]> cheese) {
        Queue<int[]> meltCheese = new LinkedList<>();
        int size = cheese.size();
        for(int i = 0; i < size; ++i) {
            int[] tmp = cheese.poll();
            int r = tmp[0];
            int c = tmp[1];
            int cnt = 0;
            for(int dir = 0; dir < 4; ++dir) {
                int nx = r + dx[dir];
                int ny = c + dy[dir];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(board[nx][ny] == 2) ++cnt;
            }
            if(cnt >= 2) meltCheese.offer(tmp);
            else cheese.offer(tmp);
        }
        return meltCheese;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();
        board = new int[N][M];
        Queue<int[]> cheese = new LinkedList<>();
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                board[i][j] = input.nextInt();
                if(board[i][j] == 1) cheese.offer(new int[] {i, j});
            }
        }
        
        board[0][0] = 2;
        connect(0, 0);
        Queue<int[]> meltCheese;
        int answer = 0;
        while(!(meltCheese = afterOneHour(cheese)).isEmpty()) {
            int startR = meltCheese.peek()[0];
            int startC = meltCheese.peek()[1];
            while(!meltCheese.isEmpty()) {
                int[] tmp = meltCheese.poll();
                int r = tmp[0];
                int c = tmp[1];
                board[r][c] = 2;
                connect(r, c);
            }
            ++answer;
        }
        System.out.println(answer);
    }
}