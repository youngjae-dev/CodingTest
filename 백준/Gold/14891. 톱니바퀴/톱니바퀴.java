import java.io.*;
import java.util.*;

public class Main {
    static Wheel[] wheels = new Wheel[5];
    static boolean[] visited = new boolean[5];
    
    static class Wheel {
        int[] num;
        int top;
        int right;
        int left;
        Wheel(String s) {
            this.num = new int[8];
            for(int i = 0; i < 8; ++i) num[i] = s.charAt(i) - '0';
            this.top = 0;
            this.right = 2;
            this.left = 6;
        }
    }
    
    static void rotation(int num, int dir) {
        visited[num] = true;
        Wheel wheel = wheels[num];

        if(num - 1 > 0 && !visited[num - 1]) {
            if(wheel.num[wheel.left] != wheels[num - 1].num[wheels[num - 1].right])
                rotation(num - 1, dir * -1);
        }
        if(num + 1 < 5 && !visited[num + 1]) {
            if(wheel.num[wheel.right] != wheels[num + 1].num[wheels[num + 1].left])
                rotation(num + 1, dir * -1);
        }
        if(dir == 1) {
            wheel.top = (wheel.top + 7) % 8;
            wheel.right = (wheel.right + 7) % 8;
            wheel.left = (wheel.left + 7) % 8;
        }
        else {
            wheel.top = (wheel.top + 1) % 8;
            wheel.right = (wheel.right + 1) % 8;
            wheel.left = (wheel.left + 1) % 8;
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        wheels[1] = new Wheel(input.nextLine());
        wheels[2] = new Wheel(input.nextLine());
        wheels[3] = new Wheel(input.nextLine());
        wheels[4] = new Wheel(input.nextLine());

        int K = input.nextInt();
        for(int i = 0; i < K; ++i) {
            int num = input.nextInt();
            int dir = input.nextInt();
            rotation(num, dir);
            Arrays.fill(visited, false);
        }

        int answer = wheels[1].num[wheels[1].top] + 2 * wheels[2].num[wheels[2].top]
                + 4 * wheels[3].num[wheels[3].top] + 8 * wheels[4].num[wheels[4].top];
        System.out.println(answer);
    }
}