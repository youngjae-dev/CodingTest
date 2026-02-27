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
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);

        while(!q.isEmpty()) {
            int cur = q.poll();

            int[] nextPos = {cur - 1, cur + 1, 2 * cur};
            for(int next : nextPos) {
                if(next < 0 || next > MAX_POS) continue;

                if(dist[next][0] > dist[cur][0] + 1) {
                    dist[next][0] = dist[cur][0] + 1;
                    dist[next][1] = dist[cur][1];
                    q.offer(next);
                }

                else if(dist[next][0] == dist[cur][0] + 1) {
                    dist[next][1] += dist[cur][1];
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