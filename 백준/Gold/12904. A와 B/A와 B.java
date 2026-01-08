import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());
        int N = T.length() - S.length();
        for(int i = 0; i < N; ++i) {
            if(T.charAt(T.length() - 1) == 'A') {
                T.delete(T.length() - 1, T.length());
            }
            else {
                T.delete(T.length() - 1, T.length());
                T.reverse();
            }
        }
        if(S.equals(T.toString())) bw.write("1");
        else bw.write("0");

        bw.flush();
    }
}