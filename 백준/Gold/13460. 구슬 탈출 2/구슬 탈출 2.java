import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int playGame(char[][] board, int[] hole, int[] redBall, int[] blueBall, boolean[][][][] visited) {
        int startRedR = redBall[0];
        int startRedC = redBall[1];
        int startBlueR = blueBall[0];
        int startBlueC = blueBall[1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startRedR, startRedC, startBlueR, startBlueC, 0});
        visited[startRedR][startRedC][startBlueR][startBlueC] = true;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int RR = tmp[0];
            int RC = tmp[1];
            int BR = tmp[2];
            int BC = tmp[3];
            int cnt = tmp[4];
            if(cnt >= 10) continue;
            for(int i = 0; i < 4; ++i) {
                int redDist = 0;
                int blueDist = 0;
                int Rnx = RR;
                int Rny = RC;
                int Bnx = BR;
                int Bny = BC;
                while(board[Rnx + dx[i]][Rny + dy[i]] != '#') {
                    Rnx += dx[i];
                    Rny += dy[i];
                    ++redDist;
                    if(Rnx == hole[0] && Rny == hole[1]) break;
                }
                while(board[Bnx + dx[i]][Bny + dy[i]] != '#') {
                    Bnx += dx[i];
                    Bny += dy[i];
                    ++blueDist;
                    if(Bnx == hole[0] && Bny == hole[1]) break;
                }
                if(Bnx == hole[0] && Bny == hole[1]) continue;
                if(Rnx == hole[0] && Rny == hole[1]) return cnt + 1;

                if(Rnx == Bnx && Rny == Bny) {
                    if(redDist > blueDist) {
                        Rnx -= dx[i];
                        Rny -= dy[i];
                    }
                    else if(redDist < blueDist) {
                        Bnx -= dx[i];
                        Bny -= dy[i];
                    }
                }

                if(!visited[Rnx][Rny][Bnx][Bny]) {
                    visited[Rnx][Rny][Bnx][Bny] = true;
                    q.offer(new int[] {Rnx, Rny, Bnx, Bny, cnt + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];
        boolean[][][][] visited = new boolean[N][M][N][M];
        int[] hole = new int[2];
        int[] redBall = new int[2];
        int[] blueBall = new int[2];
        for(int i = 0; i < N; ++i) {
            char[] arr = br.readLine().toCharArray();
            for(int j = 0; j < M; ++j) {
                board[i][j] = arr[j];
                if(board[i][j] == 'O') hole = new int[] {i, j};
                if(board[i][j] == 'R') redBall = new int[] {i, j};
                if(board[i][j] == 'B') blueBall = new int[] {i, j};
            }
        }

        System.out.println(playGame(board, hole, redBall, blueBall, visited));
    }
}