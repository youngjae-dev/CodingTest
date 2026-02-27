import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int K;
    final static int MAX_POS = 100_000;

    // dist[R][0] : R까지 가는 데 이동한 횟수
    // dist[R][1] : R까지 가는데 이동한 횟수가 동일한 경우의 수
    static int[][] dist = new int[MAX_POS + 1][2];

    static void BFS() {
        for(int i = 0; i <= MAX_POS; ++i) Arrays.fill(dist[i], Integer.MAX_VALUE);

        dist[N][0] = 0;
        dist[N][1] = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {N, 0});

        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; ++i) {
                int[] tmp = q.poll();
                int pos = tmp[0];
                int move = tmp[1];

                if(pos - 1 >= 0) {
                    if(dist[pos-1][0] > move + 1) {
                        dist[pos-1][0] = move + 1;
                        dist[pos-1][1] = 1;
                        q.offer(new int[] {pos-1, move+1});
                    }
                    else if(dist[pos-1][0] == move + 1) {
                        dist[pos-1][1]++;
                        q.offer(new int[] {pos-1, move+1});
                    }
                }
                if(pos + 1 <= MAX_POS) {
                    if(dist[pos+1][0] > move + 1) {
                        dist[pos+1][0] = move + 1;
                        dist[pos+1][1] = 1;
                        q.offer(new int[] {pos+1, move+1});
                    }
                    else if(dist[pos+1][0] == move + 1) {
                        dist[pos+1][1]++;
                        q.offer(new int[] {pos+1, move+1});
                    }
                }
                if(2*pos <= MAX_POS) {
                    if(dist[2*pos][0] > move + 1) {
                        dist[2*pos][0] = move + 1;
                        dist[2*pos][1] = 1;
                        q.offer(new int[] {2*pos, move+1});
                    }
                    else if(dist[2*pos][0] == move + 1) {
                        dist[2*pos][1]++;
                        q.offer(new int[] {2*pos, move+1});
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BFS();
        System.out.println(dist[K][0] + "\n" + dist[K][1]);
    }
}