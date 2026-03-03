import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    int N, M;
    int[][] oilId;
    
    int BFS(int row, int col, int[][] land, int idCount) {
        int size = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        oilId[row][col] = idCount; 
        q.offer(new int[] {row, col});
        
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            ++size;
            
            int r = tmp[0];
            int c = tmp[1];
            
            for(int i = 0; i < 4; ++i) {
                int nx = r + dx[i];
                int ny = c + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || land[nx][ny] == 0 || oilId[nx][ny] != 0) continue;
                oilId[nx][ny] = idCount;
                q.offer(new int[] {nx, ny});
            }
        }
        return size;
    }
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        oilId = new int[N][M];
        int[] sizes = new int[N * M + 1];
        
        int idCount = 1;
        
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                if(land[i][j] == 1 && oilId[i][j] == 0) {
                    sizes[idCount] = BFS(i, j, land, idCount);
                    idCount++;
                }
            }
        }
        
        int[] columnSum = new int[M];
        boolean[] checked = new boolean[idCount];
        
        for(int col = 0; col < M; ++col) {
            
            int sum = 0;
            
            List<Integer> meetIdList = new ArrayList<>();
            
            for(int row = 0; row < N; ++row) {
                int id = oilId[row][col];
                if(id > 0 && !checked[id]) {
                    checked[id] = true;
                    sum += sizes[id];
                    meetIdList.add(id);
                }
            }
            columnSum[col] = sum;
            
            for(int id : meetIdList) {
                checked[id] = false;
            }
        }
        int answer = 0;
        for(int x : columnSum) answer = Math.max(answer, x);
        return answer;
    }
}