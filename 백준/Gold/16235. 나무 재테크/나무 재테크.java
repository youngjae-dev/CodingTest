import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static int N;
    static Deque<Integer>[][] mapTrees;
    static int[][] food;

    static void springSummer() {
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                int len = mapTrees[i][j].size();
                int sum = 0;
                if(len == 0) continue;
                for(int k = 0; k < len; ++k) {
                    int tmp = mapTrees[i][j].pollFirst();
                    if(food[i][j] < tmp) {
                        sum += tmp/2;
                        continue;
                    }
                    food[i][j] -= tmp;
                    mapTrees[i][j].offerLast(tmp + 1);
                }
                food[i][j] += sum;
            }
        }
    }

    static void autumn() {
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                int len = mapTrees[i][j].size();
                if(len == 0) continue;
                for(int k = 0; k < len; ++k) {
                    int tmp = mapTrees[i][j].pollFirst();
                    if(tmp % 5 == 0) {
                        for(int dir = 0; dir < 8; ++dir) {
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];
                            if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                            mapTrees[nx][ny].offerFirst(1);
                        }
                    }
                    mapTrees[i][j].offerLast(tmp);
                }
            }
        }
    }

    static void winter(int[][] A) {
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                food[i][j] += A[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] A = new int[N+1][N+1];
        food = new int[N+1][N+1];
        mapTrees = new ArrayDeque[N+1][N+1];
        for(int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; ++j) {
                A[i][j] = Integer.parseInt(st.nextToken());
                mapTrees[i][j] = new ArrayDeque<>();
                food[i][j] = 5;
            }
        }

        for(int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            mapTrees[x][y].offerFirst(z);
        }

        for(int i = 0; i < K; ++i) {
            springSummer();
            autumn();
            winter(A);
        }
        int answer = 0;
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j<= N; ++j) {
                answer += mapTrees[i][j].size();
            }
        }
        System.out.println(answer);
    }
}