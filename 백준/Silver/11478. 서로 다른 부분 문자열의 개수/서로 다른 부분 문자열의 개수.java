import java.util.*;

public class Main {
    public static int solution(String str){
        HashSet <String> set = new HashSet<> ();
        int length = str.length();
        for(int i = 1; i <= length; ++i) {
            for(int j = 0; j < length -i + 1; ++j) {
                set.add(str.substring(j, j + i));
            }
        }
        return set.size();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(solution(str));
    }
}