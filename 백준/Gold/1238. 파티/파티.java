import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int X;

    static int[] head;
    static int[] to;
    static int[] next;

    static int[] edgeTime;

    static int[][] startDist;
    static int[][] endDist;

    static int edgeCount = 1;
    static void addEdge(int u, int v, int time) {
        edgeTime[edgeCount] = time;
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    static void dijkstra(int start, int X, int[][] dist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int currNode = tmp[0];
            int time = tmp[1];

            if(dist[start][currNode] < time) continue;

            for(int i = head[currNode]; i != -1; i = next[i]) {
                int nextNode = to[i];
                int cost = time + edgeTime[i];
                if(dist[start][nextNode] > cost) {
                    dist[start][nextNode] = cost;
                    pq.offer(new int[] {nextNode, cost});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        head = new int[1000+1];
        to = new int[10000+1];
        next = new int[10000+1];

        startDist = new int[1000+1][1000+1];
        endDist = new int[1000+1][1000+1];

        edgeTime = new int[10000+1];

        for(int i = 1; i <= N; ++i){
            Arrays.fill(startDist[i], 1_000_000_000);
            Arrays.fill(endDist[i], 1_000_000_000);
            startDist[i][i] = 0;
            endDist[i][i] = 0;
            head[i] = -1;
        }

        for(int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            addEdge(start, end, time);
        }

        for(int i = 1; i <= N; ++i) dijkstra(i, X, startDist);
        for(int i = 1; i <= N; ++i) dijkstra(X, i, endDist);

        int answer = 0;
        for(int i = 1; i <= N; ++i) {
            answer = Math.max((startDist[i][X] + endDist[X][i]), answer);
        }
        System.out.println(answer);
    }
}