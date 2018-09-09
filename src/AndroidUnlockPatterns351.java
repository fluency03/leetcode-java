/**
 * Given an Android 3x3 key lock screen and two integers m and n, where
 * 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android
 * lock screen, which consist of minimum of m keys and maximum n keys.
 * 
 * Rules for a valid pattern:
 * - Each pattern must connect at least m keys and at most n keys.
 * - All the keys must be distinct.
 * - If the line connecting two consecutive keys in the pattern passes through
 * any other keys, the other keys must have previously selected in the pattern.
 * No jumps through non selected key is allowed.
 * - The order of keys used matters.
 * 
 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6 
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * 
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * 
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * 
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * 
 * Example:
 * Given m = 1, n = 1, return 9.
 */

public class AndroidUnlockPatterns351 {
    private int[][] points = new int[][]{{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}};
    
    public int numberOfPatterns(int m, int n) {
        boolean[][] visited = new boolean[3][3];
        // corner
        int[] res0 = new int[1];
        dfs(visited, 0, 0, m, n, 1, res0);
    
        // edge
        int[] res1 = new int[1];
        dfs(visited, 0, 1, m, n, 1, res1);
    
        // center
        int[] res2 = new int[1];
        dfs(visited, 1, 1, m, n, 1, res2);
    
        return res0[0] * 4 + res1[0] * 4 + res2[0];
    }
    
    
    private void dfs(boolean[][] visited, int i, int j, int m, int n, int level, int[] res) {
        if (level > n) return;
        if (level >= m && level <=n) res[0]++;
        visited[i][j] = true;
        for (int[] p: points) {
            if (!visited[p[0]][p[1]] && canGo(i, j, p[0], p[1], visited)) {
                dfs(visited, p[0], p[1], m, n, level+1, res);
            }
        }
        visited[i][j] = false;
    }
    
    private boolean canGo(int i1, int j1, int i2, int j2, boolean[][] visited) {
        if (!isJumping(i1, j1, i2, j2)) return true;
        return visited[(i1+i2)/2][(j1+j2)/2];
    }
    
    private boolean isJumping(int i1, int j1, int i2, int j2) {
        return (i1 == i2 && Math.abs(j1-j2) == 2) ||
                (j1 == j2 && Math.abs(i1-i2) == 2) ||
                (Math.abs(i1-i2) == 2 && Math.abs(j1-j2) == 2);
    }


    public int numberOfPatterns2(int m, int n) {
        // Skip array represents number to skip between two pairs
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean vis[] = new boolean[10];
        int rst = 0;
        // DFS search each length from m to n
        for(int i = m; i <= n; ++i) {
            rst += DFS(vis, skip, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
            rst += DFS(vis, skip, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
            rst += DFS(vis, skip, 5, i - 1);        // 5
        }
        return rst;
    }

    // cur: the current position
    // remain: the steps remaining
    int DFS(boolean vis[], int[][] skip, int cur, int remain) {
        if(remain < 0) return 0;
        if(remain == 0) return 1;
        vis[cur] = true;
        int rst = 0;
        for(int i = 1; i <= 9; ++i) {
            // If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
            if(!vis[i] && (skip[cur][i] == 0 || (vis[skip[cur][i]]))) {
                rst += DFS(vis, skip, i, remain - 1);
            }
        }
        vis[cur] = false;
        return rst;
    }


    public int numberOfPatterns3(int m, int n) {
        boolean[][] visited = new boolean[3][3];
        int[] res = new int[1];
        
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                dfs(i, j, visited, m, n, 1, res);
            }
        }
        return res[0];
    }
    
    private void dfs(int i, int j, boolean[][] visited, int m, int n, int len, int[] res) {
        if (visited[i][j] || len > n) return;
        if (len >= m) res[0]++;
        visited[i][j] = true;
        for (int ii=0; ii<3; ii++) {
            int di = Math.abs(i - ii);
            for (int jj=0; jj<3; jj++) {
                int dj = Math.abs(j - jj);
                if (di == 0 && dj == 0) continue;
                if ((di == 2 && dj != 1) || (dj == 2 && di != 1)) {
                    if (!visited[(i+ii)/2][(j+jj)/2]) continue;
                }
                dfs(ii, jj, visited, m, n, len+1, res);
            }
        }
        visited[i][j] = false;
    }

}
