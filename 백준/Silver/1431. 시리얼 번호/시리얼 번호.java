import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        ArrayList<String> guitars = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            guitars.add(input.next());
        }
        Collections.sort(guitars,(s1,s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            if(length1 != length2) return length1 - length2;

            int digit1 = 0;
            int digit2 = 0;
            for(char c : s1.toCharArray()) {
                if(c >= '0' && c <= '9') digit1 += (c - '0');
            }
            for(char c : s2.toCharArray()) {
                if(c >= '0' && c <= '9') digit2 += (c - '0');
            }
            if(digit1 != digit2) return digit1 - digit2;
            else return s1.compareTo(s2);
        });
        for(String s : guitars) System.out.println(s);
    }
}