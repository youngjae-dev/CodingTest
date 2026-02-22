import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void postOrderTraversal(int start, int end) {
        if(start > end) return;

        int root = list.get(start);
        int mid = start + 1;

        while(mid <= end && list.get(mid) < root) {
            ++mid;
        }

        postOrderTraversal(start+1, mid-1);
        postOrderTraversal(mid, end);
        sb.append(root).append('\n');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while((input = br.readLine()) != null) {
            list.add(Integer.parseInt(input));
        }

        postOrderTraversal(0, list.size()-1);
        System.out.println(sb);
    }
}