/**
 * Given an m x n matrix of non-negative integers representing the height of
 * each unit cell in a continent, the "Pacific ocean" touches the left and top
 * edges of the matrix and the "Atlantic ocean" touches the right and bottom
 * edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a
 * cell to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific
 * and Atlantic ocean.
 * 
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * 
 * Example:
 * 
 * Given the following 5x5 matrix:
 * 
 *   Pacific ~   ~   ~   ~   ~ 
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 * 
 * Return:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with
 * parentheses in above matrix).
 */


public class PacificAtlanticWaterFlow417 {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        int M = matrix.length;
        int N = matrix[0].length;
        boolean[][] pacific = new boolean[M][N];
        boolean[][] visited = new boolean[M][N];
        Queue<int[]> q = new LinkedList<>();
        for (int j=0; j<N; j++) {
            q.add(new int[]{0, j});
            pacific[0][j] = true;
            visited[0][j] = true;
        }
        for (int i=1; i<M; i++) {
            q.add(new int[]{i, 0});
            pacific[i][0] = true;
            visited[i][0] = true;
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int[] d: dirs) {
                int x = now[0] + d[0];
                int y = now[1] + d[1];
                if (x >= 0 && y >= 0 && x < M && y < N && !visited[x][y] && matrix[x][y] >= matrix[now[0]][now[1]]) {
                    q.add(new int[]{x, y});
                    pacific[x][y] = true;
                    visited[x][y] = true;
                }
            }
        }

        boolean[][] atlantic = new boolean[M][N];
        visited = new boolean[M][N];
        q = new LinkedList<>();
        for (int j=0; j<N; j++) {
            q.add(new int[]{M-1, j});
            atlantic[M-1][j] = true;
            visited[M-1][j] = true;
        }
        for (int i=0; i<M-1; i++) {
            q.add(new int[]{i, N-1});
            atlantic[i][N-1] = true;
            visited[i][N-1] = true;
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int[] d: dirs) {
                int x = now[0] + d[0];
                int y = now[1] + d[1];
                if (x >= 0 && y >= 0 && x < M && y < N && !visited[x][y] && matrix[x][y] >= matrix[now[0]][now[1]]) {
                    q.add(new int[]{x, y});
                    atlantic[x][y] = true;
                    visited[x][y] = true;
                }
            }
        }

        List<int[]> res = new ArrayList<>();
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (atlantic[i][j] && pacific[i][j]) {
                    // System.out.println(i + ", " + j);
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }

}
