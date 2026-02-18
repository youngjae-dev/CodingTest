import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Edge>[] edgeList;
    static boolean[] visited;
    static int maxDist = 0;
    static int nodeNum = 0;

    static class Edge {
        int vertex;
        int weight;
        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static void DFS(int start, int sum) {
        if(sum > maxDist) {
            maxDist = sum;
            nodeNum = start;
        }
        for(Edge e : edgeList[start]) {
            if(visited[e.vertex]) continue;
            visited[e.vertex] = true;
            DFS(e.vertex, sum + e.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i <= N; ++i) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[a].add(new Edge(b, c));
            edgeList[b].add(new Edge(a, c));
        }

        visited[1] = true;
        DFS(1, 0);

        Arrays.fill(visited, false);
        maxDist = 0;
        visited[nodeNum] = true;
        DFS(nodeNum, 0);

        System.out.println(maxDist);
    }
}