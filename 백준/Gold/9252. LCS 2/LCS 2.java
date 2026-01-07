import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();
        char[] arr_A = A.toCharArray();
        char[] arr_B = B.toCharArray();
        int[][] LCS_length = new int [A.length() + 1][B.length() + 1];

        for(int i = 1; i <= A.length(); ++i) {
            for(int j = 1; j <= B.length(); ++j) {
                if(arr_A[i-1]==arr_B[j-1]) LCS_length[i][j] = LCS_length[i-1][j-1] + 1;
                else LCS_length[i][j] = Math.max(LCS_length[i-1][j], LCS_length[i][j-1]);
            }
        }

        int size = LCS_length[A.length()][B.length()];
        bw.write(size + "\n");

        StringBuilder sb = new StringBuilder();
        int index_A = A.length();
        int index_B = B.length();
        while(index_A > 0 && index_B > 0) {

            if(LCS_length[index_A][index_B] == LCS_length[index_A][index_B - 1]) {
                index_B--;
            }
            else if(LCS_length[index_A][index_B] == LCS_length[index_A - 1][index_B]) {
                index_A--;
            }

            else if(LCS_length[index_A][index_B] == (LCS_length[index_A - 1][index_B - 1] + 1)) {
                sb.append(arr_A[index_A - 1]);
                index_A--;
                index_B--;
            }
        }

        String answer = sb.reverse().toString();
        bw.write(answer);

        bw.flush();
    }
}