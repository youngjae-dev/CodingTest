import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        String tmp;
        while((tmp = input.readLine()) != null) {
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            ++count;
        }
        ArrayList<String> woods = new ArrayList<>(map.keySet());
        Collections.sort(woods);
        for(String s : woods) System.out.printf("%s %.4f\n", s, ((map.get(s)* 100.0)/count));
    }
}