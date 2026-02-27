import java.util.*;

class Solution {
    class Robot {
        ArrayList<int[]> route;
        
        Robot() {
            route = new ArrayList<>();
        }
        
        void addRoute(int[] start, int[] end, boolean isFirst) {
            int currR = start[0];
            int currC = start[1];
            int targetR = end[0];
            int targetC = end[1];
            
            if(isFirst) this.route.add(new int[] {currR, currC});
            
            while (currR != targetR) {
                if (currR < targetR) currR++;
                else currR--;
                this.route.add(new int[]{currR, currC});
            }
            
            while(currC != targetC) {
                if(currC < targetC) currC++;
                else currC--;
                this.route.add(new int[] {currR, currC});
            }
        }
    }
    public int solution(int[][] points, int[][] routes) {
        ArrayList<Robot> robots = new ArrayList<>();
        int maxLength = 0;
        for(int[] arr : routes) {
            Robot robot = new Robot();
            for(int i = 0; i < arr.length - 1; ++i) {
                int[] startPoint = points[arr[i] - 1];
                int[] endPoint = points[arr[i+1] - 1];
                if(robot.route.isEmpty()) robot.addRoute(startPoint, endPoint, true);
                else robot.addRoute(startPoint, endPoint, false);
            }
            robots.add(robot);
            maxLength = Math.max(maxLength, robot.route.size());
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int second = 0;
        int answer = 0;
        while(second < maxLength) {
            for(Robot robot : robots) {
                if(second >= robot.route.size()) continue;
                int[] pos = robot.route.get(second);
                int r = pos[0];
                int c = pos[1];
                int key = r*1000+c;
                map.put(key, map.getOrDefault(key, 0) + 1);
                if(map.get(key) == 2) ++answer;
            }
            map.clear();
            ++second;
        }
        return answer;
    }
}