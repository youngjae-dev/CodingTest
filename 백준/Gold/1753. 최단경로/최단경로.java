import java.util.*;
import java.io.*;

public class Main {
    static int V;
    static ArrayList<Edge>[] edgeList;
    static int[] dist;

    static class Edge {
        int vertex;
        int dist;
        Edge(int vertex, int dist){
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.offer(new int[] {start, 0});
        dist[start] = 0;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int num = tmp[0];
            int sum = tmp[1];

            if(dist[num] < sum) continue;

            for(Edge e : edgeList[num]) {
                if(dist[e.vertex] > dist[num] + e.dist) {
                    dist[e.vertex] = dist[num] + e.dist;
                    q.offer(new int[] {e.vertex, dist[e.vertex]});
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        dist = new int[V+1];
        edgeList = new ArrayList[V+1];
        for(int i = 0; i <= V; ++i) {
            dist[i] = Integer.MAX_VALUE;
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList[u].add(new Edge(v, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; ++i) {
            int x = dist[i];
            if(x == Integer.MAX_VALUE) {
                sb.append("INF\n");
            }
            else {
                sb.append(x + "\n");
            }
        }
        System.out.println(sb);
    }
}