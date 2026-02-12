import java.util.*;
import java.io.*;

// Shark 클래스에서 isDead를 추가해주고, 모든 상어의 이동이 끝날때마다 list를 갱신해주면서 처리했더니, 수행속도가 2000ms에서 400ms로 줄어든 것을 볼 수 있다.
// 비효율적인 list에서의 remove 사용을 없애고 반복문과 분기처리로 바꿨더니 수행속도가 무려 5배나 차이 나는 것을 보고, 비효율적인 메서드의 사용을 자제해야겠다고 느꼈다.

public class Main {
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int R;
    static int C;
    static Shark[][] sharks;
    static Fisher fisher = new Fisher();
    static ArrayList<Shark> list = new ArrayList<>();

    static class Fisher {
        int c;
        int totalSize;
        Fisher() {
            this.c = 0;
            this.totalSize = 0;
        }
    }
    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;
        boolean isDead;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.isDead = false;
        }
    }

    static void moveFisher() {
        fisher.c = fisher.c + 1;
        for(int i = 1; i <= R; ++i) {
            if(sharks[i][fisher.c] != null) {
                fisher.totalSize += sharks[i][fisher.c].z;
                sharks[i][fisher.c].isDead = true;
                sharks[i][fisher.c] = null;
                return;
            }
        }
    }

    static void moveSharks() {
        Shark[][] map = new Shark[R+1][C+1];

        for(Shark shark : list) {
            if(shark.isDead) continue;
            move(map, shark);
        }

        ArrayList<Shark> nextList = new ArrayList<>();
        for(int i = 1; i <= R; ++i) {
            for(int j = 1; j <= C; ++j) {
                if(map[i][j] != null) nextList.add(map[i][j]);
            }
        }
        sharks = map;
        list = nextList;
    }

    static void move(Shark[][] map, Shark shark){
        int dir = shark.d;

        int velocity = 0;
        if(dir == 1 || dir == 2) {
            velocity = shark.s % ((R - 1) * 2);
        }
        else {
            velocity = shark.s % ((C - 1) * 2);
        }

        int r = shark.r;
        int c = shark.c;
        for(int i = 0; i < velocity; ++i) {
            if(r + dx[dir] < 1 || r + dx[dir] > R || c + dy[dir] < 1 || c + dy[dir] > C) {
                dir = ((dir - 1) ^ 1) + 1;
                shark.d = dir;
                r += dx[dir];
                c += dy[dir];
            }
            else {
                r += dx[dir];
                c += dy[dir];
            }
        }
        shark.r = r;
        shark.c = c;
        if(map[r][c] != null) {
            collision(map, r, c, shark);
        }
        else {
            map[r][c] = shark;
        }
    }

    static void collision(Shark[][] map, int r, int c, Shark other) {
        if(map[r][c].z >= other.z) {
            other.isDead = true;
        }
        else {
            map[r][c].isDead = true;
            map[r][c] = other;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sharks = new Shark[R+1][C+1];

        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            Shark shark = new Shark(r, c, s, d, z);
            list.add(shark);
            sharks[r][c] = shark;
        }
        for(int i = 0; i < C; ++i) {
            moveFisher();
            moveSharks();
        }
        System.out.println(fisher.totalSize);
    }

}
