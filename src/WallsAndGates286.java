/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to
 * represent INF as you may assume that the distance to a gate is less
 * than 2147483647.
 * 
 * Fill each empty room with the distance to its nearest gate. If it is
 * impossible to reach a gate, it should be filled with INF.
 * 
 * Example: 
 * 
 * Given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * 
 * After running your function, the 2D grid should be:
 * 
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 */


public class WallsAndGates286 {

    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int n = rooms.length;
        int m = rooms[0].length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (rooms[i][j] == 0) {
                    for (int[] dir: directions) {
                        dfs(rooms, 1, i + dir[0], j + dir[1]);
                    }
                }
            }
        }
    }

    private void dfs(int[][] rooms, int distance, int i, int j) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length) return;
        if (rooms[i][j] == -1 || rooms[i][j] == 0) return;

        // can be optimized: early stop
        // rooms[i][j] = Math.min(rooms[i][j], distance);
        if (rooms[i][j] > distance) {
            rooms[i][j] = distance;
        } else if (rooms[i][j] <= distance) {
            return;
        }

        for (int[] dir: directions) {
            dfs(rooms, distance + 1, i + dir[0], j + dir[1]);
        }
    }

    /**
     * leetcode solution
     */
    public void wallsAndGates2(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) {
                    dfs2(rooms, i, j, 0);
                }
            }
        }
    }
    
    private void dfs2(int[][] rooms, int i, int j, int dis) {
        if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || dis > rooms[i][j] || rooms[i][j] == -1) return;
        rooms[i][j] = dis;
        
        dfs2(rooms, i + 1, j, dis+1);
        dfs2(rooms, i - 1, j, dis+1);
        dfs2(rooms, i, j - 1, dis+1);
        dfs2(rooms, i, j + 1, dis+1);
    }

    /**
     * https://leetcode.com/problems/walls-and-gates/solution/
     */
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );
    
    public void wallsAndGates4(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }

}
