import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] dist = new int[N+1][N+1];
        for(int i = 0; i <= N; ++i) {
            Arrays.fill(dist[i], 100000000);
            dist[i][i] = 0;
        }

        int[][] bus = new int[M][3];
        for(int i = 0; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; ++j) {
                bus[i][j] = Integer.parseInt(st.nextToken());
            }
            int depart = bus[i][0];
            int arrive = bus[i][1];
            dist[depart][arrive] = Math.min(dist[depart][arrive], bus[i][2]);
        }

        for(int m = 1; m <= N; ++m) {
            for(int s = 1; s <= N; ++s) {
                for(int e = 1; e <= N; ++e) {
                    if(dist[s][e] > dist[s][m] + dist[m][e]) {
                        dist[s][e] = dist[s][m] + dist[m][e];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; ++i) {
            for(int j = 1; j <= N; ++j) {
                if(dist[i][j] != 100000000) sb.append(dist[i][j]).append(" ");
                else sb.append(0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}