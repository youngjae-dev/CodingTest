import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        
        HashMap <String, String> users = new HashMap<String,String> ();
        
        ArrayList<String> user_log = new ArrayList<>();
        ArrayList<String> active_log = new ArrayList<>();
        
        for(String s : record) {
            String[] s_split = s.split(" ");
            
            if(s_split[0].equals("Enter")){
                users.put(s_split[1],s_split[2]);
                active_log.add("E");
                user_log.add(s_split[1]);
            }
            else if(s_split[0].equals("Leave")) {
                active_log.add("L");
                user_log.add(s_split[1]);
            }
            else if(s_split[0].equals("Change")) users.put(s_split[1], s_split[2]);
        }
        
        ArrayList<String> answer_arr = new ArrayList<>();
        for(int i = 0; i < active_log.size(); ++i) {
            if(active_log.get(i).equals("E")) {
                answer_arr.add(users.get(user_log.get(i))+"님이 들어왔습니다.");
            }
            else {
                answer_arr.add(users.get(user_log.get(i))+"님이 나갔습니다.");
            }
        }
        
        answer = answer_arr.toArray(new String[0]);
        return answer;
    }
}