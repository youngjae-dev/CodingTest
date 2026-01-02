import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();
        StringTokenizer st = new StringTokenizer(s);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> voca = new HashMap<>();

        for(int i = 0; i < N; ++i) {
            s = input.readLine();
            if(s.length() < M) continue;
            voca.put(s, voca.getOrDefault(s, 0) + 1);
        }

        ArrayList<String> words = new ArrayList<>(voca.keySet());

        Collections.sort(words, (s1, s2) -> {
            int freq1 = voca.get(s1);
            int freq2 = voca.get(s2);
            if(freq1 != freq2) return freq2 - freq1;

            int length1 = s1.length();
            int length2 = s2.length();
            if(length1 != length2) return length2 - length1;

            return s1.compareTo(s2);
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(String word : words) {
            bw.write(word + "\n");
        }
        bw.flush();
    }
}