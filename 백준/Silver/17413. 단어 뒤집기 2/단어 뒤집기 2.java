import java.util.*;

public class Main {
    public static String solution(String str){
        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        boolean flag = false;
        for(char c : str.toCharArray()) {
            if(c == '<') {
                flag = true;
                result.append(word.reverse());
                word.setLength(0);
                result.append('<');
            }
            else if(c == '>') {
                flag = false;
                result.append('>');
            }
            else if(flag) {
                result.append(c);
            }
            else {
                if(c == ' ') {
                    word = word.reverse();
                    result.append(word.toString() + ' ');
                    word.setLength(0);
                }
                else word.append(c);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(solution(str));
    }
}