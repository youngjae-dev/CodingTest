import java.util.*;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[] length;
    static int[] width;
    static boolean[][] existed;
    static int answer = -1;

    static void placeLadder(int r, int c, int cnt, int limit) {
        if(cnt == limit) {
            if(isValid()) {
                System.out.println(cnt);
                System.exit(0);
            }
            return;
        }

        if(cnt > 3) return;

        for(int i = r; i <= H; ++i) {
            int start = c;
            if(i != r) start = 1;
            for(int j = start; j < N; ++j) {
                if(existed[i][j] || (j - 1 >= 1 && existed[i][j-1]) || existed[i][j+1]) continue;

                existed[i][j] = true;
                placeLadder(i, j + 2, cnt + 1, limit);
                existed[i][j] = false;
            }
        }
    }

    static boolean isValid() {
        for(int i = 1; i <= N; ++i) {
            int currNum = i;
            for(int j = 1; j <= H; ++j) {
                if(currNum - 1 >= 1 && existed[j][currNum - 1]) currNum = currNum - 1;
                else if(currNum + 1 <= N && existed[j][currNum]) currNum = currNum + 1;
            }
            if(currNum != i) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();
        H = input.nextInt();

        length = new int[N + 1];
        width = new int[H + 1];
        existed = new boolean[H+1][N+1];

        for(int i = 0; i < M; ++i) {
            int a = input.nextInt();
            int b = input.nextInt();
            width[a] = b;
            existed[a][b] = true;
        }

        for(int i = 0; i <= 3; ++i) {
            placeLadder(1, 1, 0, i);
        }
        System.out.println(-1);
    }
}