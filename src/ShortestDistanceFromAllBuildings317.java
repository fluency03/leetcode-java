/**
 * You want to build a house on an empty land which reaches all buildings in
 * the shortest amount of distance. You can only move up, down, left and right.
 * You are given a 2D grid of values 0, 1 or 2, where:
 * 
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * 
 * Example:
 * 
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 
 * Output: 7 
 * 
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle
 *    at (0,2), the point (1,2) is an ideal empty land to build a house, as
 *    the total travel distance of 3+3+1=7 is minimal. So return 7.
 * 
 * Note:
 * There will be at least one building. If it is not possible to build such
 * house according to the above rules, return -1.
 */


public class ShortestDistanceFromAllBuildings317 {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        if (grid == null) return -1;
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0 || (m == 1 && n == 1)) return -1;

        int numBuildings = 0;
        int[][] reached = new int[m][n];
        int[][] allDists = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    numBuildings++;
                    int[][] dist = new int[m][n];
                    boolean[][] visited = new boolean[m][n];
                    dfs(grid, dist, allDists, visited, reached, 0, i, j, m, n);
                }
            }
        }
        if (numBuildings == 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 0 && reached[i][j] == numBuildings) {
                    res = Math.min(res, allDists[i][j]);
                }
            }
        }
        
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int[][] grid, int[][] dist, int[][] allDists, boolean[][] visited, int[][] reached, int d, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || (grid[i][j] == 1 && d != 0) || grid[i][j] == 2) return;
        if (visited[i][j] && dist[i][j] <= d) return;
        if (!visited[i][j]) {
            reached[i][j]++;
        } else {
            allDists[i][j] -= dist[i][j];
        }
        allDists[i][j] += d;
        dist[i][j] = d;
        visited[i][j] = true;
        for (int[] dir: dirs) {
            dfs(grid, dist, allDists, visited, reached, d+1, i+dir[0], j+dir[1], m, n);
        }
    }


    public int shortestDistance2(int[][] grid) {
        if (grid == null) return -1;
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0 || n == 0 || (m == 1 && n == 1)) return -1;

        int numBuildings = 0;
        int[][] reached = new int[m][n];
        int[][] allDists = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    numBuildings++;
                    bfs(grid, reached, allDists, i, j, m, n);
                }
            }
        }
        
        if (numBuildings == 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 0 && reached[i][j] == numBuildings) {
                    res = Math.min(res, allDists[i][j]);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void bfs(int[][] grid, int[][] reached, int[][] allDists, int x, int y, int m, int n) {
        boolean[][] added = new boolean[m][n];
        
        Queue<Point> q = new LinkedList<>();
        for (int[] dir: dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 1 || grid[i][j] == 2) continue;
            q.add(new Point(i, j));
            added[i][j] = true;
        }
        int d = 0;
        while (!q.isEmpty()) {
            d++;
            int size = q.size();
            for (int k=0; k<size; k++) {
                Point p = q.remove();
                int i = p.x;
                int j = p.y;
                allDists[i][j] += d;
                reached[i][j]++;
                for (int[] dir: dirs) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii < 0 || jj < 0 || ii >= m || jj >= n ||
                        grid[ii][jj] == 1 || grid[ii][jj] == 2 || added[ii][jj]) continue;
                    q.add(new Point(ii, jj));
                    added[ii][jj] = true;
                }
            }
        }
        
    }
    
    class Point {
        int x;
        int y;
        Point(int i, int j) {
            x = i;
            y = j;
        }
    }

}

