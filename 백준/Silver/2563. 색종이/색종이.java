import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] black = new int[N][4];
        boolean[][] white = new boolean[101][101];
        int answer = 0;
        for(int i = 0; i < N; ++i) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            black[i][0] = Integer.parseInt(st.nextToken());
            black[i][1] = black[i][0] + 10;
            black[i][2] = Integer.parseInt(st.nextToken());
            black[i][3] = black[i][2] + 10;

            for(int j = black[i][0]; j < black[i][1]; ++j) {
                for(int k = black[i][2]; k < black[i][3]; ++k) {
                    if(!white[j][k]) {
                        ++answer;
                        white[j][k] = true;
                    }
                }
            }
        }
        bw.write(answer+"");


        bw.flush();
    }
}