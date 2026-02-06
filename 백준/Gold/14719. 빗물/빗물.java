import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int H = input.nextInt();
        int W = input.nextInt();
        int[] height = new int[W];
        for(int i = 0; i < W; ++i) {
            height[i] = input.nextInt();
        }

        if(W == 1) {
            System.out.println(0);
            System.exit(0);
        }

        int answer = 0;
        for(int i = 1; i < W - 1; ++i) {
            int leftMax = 0;
            int rightMax = 0;
            for(int j = 0; j < i; ++j) {
                if(height[j] > height[i]) leftMax = Math.max(leftMax, height[j]);
            }
            for(int j = i + 1; j < W; ++j) {
                if(height[j] > height[i]) rightMax = Math.max(rightMax, height[j]);
            }
            int standard = Math.min(leftMax, rightMax);

            if(height[i] < standard) {
                answer += standard - height[i];
            }
        }
        
        System.out.println(answer);
    }
}