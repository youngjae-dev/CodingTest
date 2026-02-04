import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static int L;
    static boolean checkPath(int[] road) {
        boolean[] bridge = new boolean[N];

        for(int i = 0; i < N - 1; ++i) {
            int diff = road[i] - road[i + 1];

            if(diff == 0) continue;

            if(Math.abs(diff) > 1) return false;

            if(diff == -1) {
                for(int j = 0; j < L; ++j) {
                    if(i - j < 0 || road[i] != road[i - j] || bridge[i - j]) return false;
                    bridge[i-j] = true;
                }
            }

            if(diff == 1) {
                for(int j = 1; j <= L; ++j) {
                    if(i + j >= N || road[i + 1] != road[i + j] || bridge[i +j]) return false;
                    bridge[i + j] = true;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        L = input.nextInt();
        map = new int[N][N];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) map[i][j] = input.nextInt();
        }

        int answer = 0;
        for(int i = 0; i < N; ++i) if(checkPath(map[i])) ++answer;

        for(int i = 0; i < N; ++i) {
            int[] road = new int[N];
            for(int j = 0; j < N; ++j) road[j] = map[j][i];
            if(checkPath(road)) ++answer;
        }
        System.out.println(answer);
    }
}