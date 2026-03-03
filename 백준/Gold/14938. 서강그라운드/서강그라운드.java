import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n+1];
        int[][] dist = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; ++i) {
            items[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dist[i], 1_000_000);
            dist[i][i] = 0;
        }

        for(int i = 0; i < r; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            dist[u][v] = pay;
            dist[v][u] = pay;
        }

        for(int mid = 1; mid <= n; ++mid) {
            for(int u = 1; u <= n; ++u) {
                for(int v = 1; v <= n; ++v) {
                    if(dist[u][v] > dist[u][mid] + dist[mid][v]) {
                        dist[u][v] = dist[u][mid] + dist[mid][v];
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= n; ++i) {
            int sum = 0;
            for(int j = 1; j <= n; ++j) {
                if(dist[i][j] <= m) sum += items[j];
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}