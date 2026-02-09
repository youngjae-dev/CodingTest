import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101];

    static void dragonCurve(int x, int y, int d, int g) {
        ArrayList<Integer> cmd = new ArrayList<>();
        cmd.add(d);

        for(int i = 1; i <= g; ++i) {
            int size = cmd.size();
            for(int j = size - 1; j >= 0; --j) {
                cmd.add((cmd.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for(int dir : cmd) {
            x += dx[dir];
            y += dy[dir];
            if(x < 0 || x > 100 || y < 0 || y > 100) continue;
            map[y][x] = true;
        }
    }

    static int squareCount() {
        int cnt = 0;
        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) ++cnt;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        for(int i = 0; i < N; ++i) {
            int x = input.nextInt();
            int y = input.nextInt();
            int d = input.nextInt();
            int g = input.nextInt();
            dragonCurve(x, y, d, g);
        }
        System.out.println(squareCount());
    }
}