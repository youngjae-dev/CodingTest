import java.util.*;
import java.io.*;

public class Main {
    static String isPossible(ArrayList<int[]>[] graph) {
        int N = graph.length - 1;

        int[] dist = new int[N+1];

        for(int i = 1; i < N; ++i) {
            for(int j = 1; j <= N; ++j) {
                for(int[] edge : graph[j]) {
                    int end = edge[0];
                    int time = edge[1];

                    if(dist[end] > dist[j] + time)
                        dist[end] = dist[j] + time;
                }
            }
        }

        for(int i = 1; i <= N; ++i) {
            for(int[] edge : graph[i]) {
                int end = edge[0];
                int time = edge[1];

                if(dist[end] > dist[i] + time) return "YES\n";
            }
        }
        return "NO\n";
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < TC; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            ArrayList<int[]>[] graph = new ArrayList[N+1];
            for(int num = 0; num <= N; ++num) graph[num] = new ArrayList<>();

            for(int j = 0; j < M; ++j) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                graph[start].add(new int[] {end, time});
                graph[end].add(new int[] {start, time});
            }
            for(int j = 0; j < W; ++j) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                graph[start].add(new int[] {end, time * -1});
            }

            answer.append(isPossible(graph));
        }

        System.out.println(answer);
    }
}