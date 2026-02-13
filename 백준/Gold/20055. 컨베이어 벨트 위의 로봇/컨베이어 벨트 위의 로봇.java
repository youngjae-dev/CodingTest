import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int K;
    static Space[] map;

    static class Space {
        int weight;
        boolean existed;
        Space(int weight) {
            this.weight = weight;
            existed = false;
        }
    }

    static void moveBelt() {
        Space tmp = map[2 * N];
        for (int i = 2 * N; i > 1; --i) {
            map[i] = map[i - 1];
        }
        map[1] = tmp;

        if (map[N].existed) {
            map[N].existed = false;
        }
    }

    static void moveRobot() {
        for(int i = N - 1; i >= 1; --i) {
            if(map[i].existed && map[i+1].weight > 0 && !map[i+1].existed) {
                if(--map[i+1].weight == 0) --K;
                if(i + 1 != N) map[i+1].existed = true;
                map[i].existed = false;
            }
        }
    }

    static void placeRobot() {
        if(!map[1].existed && map[1].weight > 0) {
            if(--map[1].weight == 0) --K;
            map[1].existed = true;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        K = input.nextInt();
        map = new Space[2*N+1];
        for(int i = 1; i <= 2*N; ++i) {
            map[i] = new Space(input.nextInt());
        }

        int step = 0;
        while(K > 0) {
            ++step;
            moveBelt();
            moveRobot();
            placeRobot();
        }
        System.out.println(step);
    }
}