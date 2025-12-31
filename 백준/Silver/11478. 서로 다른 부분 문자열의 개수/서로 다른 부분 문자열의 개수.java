import java.util.*;

public class Main {
    public static int solution(String str){
        HashMap <String, Integer> map = new HashMap<> ();
        int length = str.length();
        for(int i = 1; i <= length; ++i) {
            StringBuilder tmp_sb = new StringBuilder(str);
            for(int j = 0; j < length -i + 1; ++j) {
                map.put(str.substring(j, j + i), 0);
            }
        }
        return map.size();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(solution(str));
    }
}