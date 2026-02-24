import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int N;
    int M;
    
    char[][] map;
    Queue<int[]>[] alphabetPosition = new ArrayDeque[27];
    boolean[][] visited;
    
    int forkLift(char alphabet) {
        int cnt = 0;
        int length = alphabetPosition[alphabet - 'A'].size();
        ArrayList<int[]> eraseList = new ArrayList<>();
        
        for(int i = 0; i < length; ++i) {
            int[] tmp = alphabetPosition[alphabet - 'A'].poll();
            int r = tmp[0];
            int c = tmp[1];
            boolean flag = true;
            for(int dir = 0; dir < 4; ++dir) {
                int nx = r + dx[dir];
                int ny = c + dy[dir];
                if(nx < 0 || nx > N + 1 || ny < 0 || ny > M + 1) continue;
                if(visited[nx][ny]) {
                    ++cnt;
                    eraseList.add(new int[] {r, c});
                    flag = false;
                    break;
                }
            }
            if(flag) alphabetPosition[alphabet - 'A'].offer(tmp);
        }
        for(int[] arr : eraseList) {
            int r = arr[0];
            int c = arr[1];
            map[r][c] = '\0';
        }
        return cnt;
    }
    
    int crane(char alphabet) {
        int cnt = 0;
        while(!alphabetPosition[alphabet - 'A'].isEmpty()) {
            int[] tmp = alphabetPosition[alphabet-'A'].poll();
            int r = tmp[0];
            int c = tmp[1];
            if(map[r][c] == '\0') continue;
            map[r][c] = '\0';
            ++cnt;
        }
        return cnt;
    }
    
    void BFS() {
        for(int i = 0; i <= N+1; i++) Arrays.fill(visited[i], false);
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            for(int i = 0; i < 4; ++i) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if(nx < 0 || nx > N + 1 || ny < 0 || ny > M + 1 || visited[nx][ny] || map[nx][ny] != '\0') continue;
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        
        
        for(int i = 0; i <= 26; ++i) alphabetPosition[i] = new ArrayDeque<>();
        
        map = new char[N+2][M+2];
        for(int i = 1; i <= N; ++i) {
            String s = storage[i-1];
            for(int j = 1; j <= M; ++j) {
                map[i][j] = s.charAt(j-1);
                alphabetPosition[map[i][j] - 'A'].offer(new int[] {i, j});
            }
        }
        
        int answer = N * M;
        
        visited = new boolean[N+2][M+2];
        BFS();
        for(String s : requests) {
            char c = s.charAt(0);
            if(s.length() == 1) answer -= forkLift(c);
            else answer -= crane(c);
            BFS();
        }
        
        return answer;
    }
}