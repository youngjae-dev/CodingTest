import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String boom = br.readLine();
        int boom_length = boom.length();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); ++i) {
            sb.append(str.charAt(i));

            if(sb.length() >= boom_length) {
                boolean combine = true;
                for(int j = 0; j < boom_length; ++j) {
                    if(sb.charAt(sb.length() -j -1) != boom.charAt(boom_length -j -1)) {
                        combine = false;
                        break;
                    }
                }
                if(combine) {
                    sb.delete(sb.length() - boom_length, sb.length());
                }
            }
        }

        if(sb.length() == 0) bw.write("FRULA");
        else bw.write(sb.toString());
        bw.flush();
    }
}