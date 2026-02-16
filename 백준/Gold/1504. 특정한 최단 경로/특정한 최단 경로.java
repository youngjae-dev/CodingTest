import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Edge>[] edgeList;
    static int N;
    static class Edge {
        int vertex;
        int distance;
        Edge(int v, int d) {
            this.vertex = v;
            this.distance = d;
        }
    }

    static int dijkstra(int start, int end) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, b1) -> a1[1] - b1[1]);
        q.offer(new int[] {start, 0});
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int startIndex = tmp[0];
            int sum = tmp[1];

            if(sum > dist[startIndex]) continue;

            if(startIndex == end) return sum;

            for(Edge e : edgeList[startIndex]) {
                if(dist[e.vertex] > dist[startIndex] + e.distance) {
                    dist[e.vertex] = dist[startIndex] + e.distance;
                    q.offer(new int[] {e.vertex, dist[e.vertex]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[N+1];
        int E = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; ++i) {
            edgeList[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[a].add(new Edge(b, c));
            edgeList[b].add(new Edge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int stopOver1 = Integer.parseInt(st.nextToken());
        int stopOver2 = Integer.parseInt(st.nextToken());
        int s1 = dijkstra(1, stopOver1);
        int s2 = dijkstra(1, stopOver2);
        int mid = dijkstra(stopOver1, stopOver2);
        int e1 = dijkstra(stopOver1, N);
        int e2 = dijkstra(stopOver2, N);

        int[] tmp1 = {s1, mid, e2};
        int sum1 = 0;
        for(int i = 0; i < 3; ++i) {
            if(tmp1[i] == -1) {
                sum1 = -1;
                break;
            }
            sum1 += tmp1[i];
        }

        int[] tmp2 = {s2, mid, e1};
        int sum2 = 0;
        for(int i = 0; i < 3; ++i) {
            if(tmp2[i] == -1) {
                sum2 = -1;
                break;
            }
            sum2 += tmp2[i];
        }

        int answer = 0;
        if(sum1 == -1) {
            if(sum2 == -1) answer = -1;
            else answer = sum2;
        }
        else {
            if(sum2 == -1) answer = sum1;
            else answer = Math.min(sum1, sum2);
        }
        System.out.println(answer);
    }
}