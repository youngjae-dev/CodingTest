import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] canVisit;
    static int count = 0;

    static void dfs(int row, int column, int direction) {
        if(row == N - 1 && column == N - 1) {
            ++count;
            return;
        }

        if(direction == 0) {
            if(column + 1 < N && canVisit[row][column + 1]) dfs(row,column + 1,0);
            if(column + 1 < N && row + 1 < N
                    && canVisit[row][column+1] && canVisit[row+1][column] && canVisit[row+1][column+1]) {
                dfs(row+1,column+1,1);
            }
        }
        else if(direction == 1) {
            if(column + 1 < N && canVisit[row][column + 1]) dfs(row,column + 1,0);
            if(column + 1 < N && row + 1 < N
                    && canVisit[row][column + 1] && canVisit[row+1][column] && canVisit[row+1][column+1]) {
                dfs(row+1,column+1,1);
            }
            if(row+1 < N && canVisit[row+1][column]) dfs(row+1,column,2);
        }
        else {
            if(column + 1 < N && row + 1 < N
                    && canVisit[row][column + 1] && canVisit[row+1][column] && canVisit[row+1][column+1]) {
                dfs(row+1,column+1,1);
            }
            if(row+1 < N && canVisit[row+1][column]) dfs(row+1,column,2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        canVisit = new boolean[N][N];
        for(int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j) {
                if(st.nextToken().equals("1")) canVisit[i][j] = false;
                else canVisit[i][j] = true;
            }
        }

        dfs(0,1,0);
        bw.write(count+"");

        bw.flush();
    }
}