import java.util.*;

public class Main {
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int R;
    static int C;
    static Shark[][] sharks;
    static Fisher fisher = new Fisher();
    static ArrayList<Shark> list = new ArrayList<>();
    static Queue<Shark> eraseShark = new LinkedList<>();

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
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static void moveFisher() {
        fisher.c = fisher.c + 1;
        for(int i = 1; i <= R; ++i) {
            if(sharks[i][fisher.c] != null) {
                fisher.totalSize += sharks[i][fisher.c].z;
                list.remove(sharks[i][fisher.c]);
                sharks[i][fisher.c] = null;
                return;
            }
        }
    }

    static void moveSharks() {
        Shark[][] map = new Shark[R+1][C+1];
        for(Shark shark : list) {
            move(map, shark);
        }
        while(!eraseShark.isEmpty()) {
            list.remove(eraseShark.poll());
        }
        for(int i = 1; i <= R; ++i) sharks[i] = map[i].clone();
    }

    static void move(Shark[][] map, Shark shark){
        int velocity = shark.s;
        int dir = shark.d;
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
            eraseShark.offer(other);
        }
        else {
            eraseShark.offer(map[r][c]);
            map[r][c] = other;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        R = input.nextInt();
        C = input.nextInt();
        sharks = new Shark[R+1][C+1];

        int M = input.nextInt();
        for(int i = 0; i < M; ++i) {
            int r = input.nextInt();
            int c = input.nextInt();
            int s = input.nextInt();
            int d = input.nextInt();
            int z = input.nextInt();
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