import java.util.*;

class Solution {
    ArrayList<Integer> candidates(String s, ArrayList<Integer> candidates) {
        ArrayList<Integer> nextCandidate = new ArrayList<>();
        for(int x : candidates) {
            if(checkExpression(s.split(" "), x)) nextCandidate.add(x);
        }
        return nextCandidate;
    }
    
    boolean checkExpression(String[] express, int x) {
        int num1 = Integer.parseInt(express[0], x);
        int num2 = Integer.parseInt(express[2], x);
        int result = Integer.parseInt(express[4], x);
        
        if(express[1].equals("+") && num1 + num2 == result) return true;
        else if(num1 - num2 == result) return true;
        
        return false;
    }
    
    String solutionExpress(String s, ArrayList<Integer> candidate) {
        String[] arr = s.split(" ");
        String answer = "";
        for(int x : candidate) {
            int num1 = Integer.parseInt(arr[0], x);
            int num2 = Integer.parseInt(arr[2], x);
            String candi = "";
            
            if(arr[1].equals("+")) {
                int tmp = num1 + num2;
                candi = Integer.toString(tmp, x);
            }
            else {
                int tmp = num1 - num2;
                candi = Integer.toString(tmp, x);
            }
            if(answer.equals("")) answer = candi;
            else if(!answer.equals(candi)) return "?";
        }
        return answer;
    }
        
    public String[] solution(String[] expressions) {
        
        ArrayList<Integer> equations = new ArrayList<>();
        ArrayList<Integer> others = new ArrayList<>();
        int idx = 0;
        for(String expression : expressions) {
            String[] arr = expression.split(" ");
            if(arr[4].equals("X")) equations.add(idx);
            else others.add(idx);
            ++idx;
        }
        
        int minBase = 2;
        for (String s : expressions) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    minBase = Math.max(minBase, (c - '0') + 1);
                }
            }
        }
        ArrayList<Integer> candidate = new ArrayList<>();
        for (int i = minBase; i <= 9; i++) {
            candidate.add(i);
        }
        
        for(int x : others) {
            candidate = candidates(expressions[x], candidate);
        }
        
        String[] answer = new String[equations.size()];
        for(int i = 0; i < equations.size(); ++i) {
            String s = expressions[equations.get(i)];
            answer[i] = s.replace("X", solutionExpress(s, candidate));
        }
        return answer;
    }
}