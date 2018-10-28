/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Find the maximum area of an island in the given 2D array. (If there is no
 * island, the maximum area is 0.)
 * 
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 
 * Given the above grid, return 6. Note the answer is not 11, because the
 * island must be connected 4-directionally.
 * 
 * Example 2:
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * 
 * Note: The length of each dimension in the given grid does not exceed 50.
 */

public class MaxAreaOfIsland695 {
    private static int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // BFS
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int res = 0;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, bfs(grid, visited, i, j));
                }
            }
        }
        return res;
    }

    private int bfs(int[][] grid, boolean[][] visited, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        int res = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            res++;
            for (int[] dir: DIRECTIONS) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] != 1) continue;
                q.add(new int[]{x, y});
                visited[x][y] = true;
            }
        }
        return res;
    }


    // DFS
    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] visited = new boolean[M][N];
        int res = 0;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(grid, visited, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 ||
            i>= grid.length || j >= grid[0].length ||
            grid[i][j] != 1 || visited[i][j]) return 0;

        visited[i][j] = true;
        int res = 1;
        for (int[] dir: DIRECTIONS) {
            int x = i + dir[0];
            int y = j + dir[1];
            res += dfs(grid, visited, x, y);
        }
        return res;
    }


    // Union-Find
    public int maxAreaOfIsland3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int M = grid.length;
        int N = grid[0].length;
        int res = 0;
        UinionFind uf = new UinionFind(grid);
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, link(grid, i, j, uf, M, N));
                }
            }
        }
        return res;
    }

    
    private int link(int[][] grid, int i, int j, UinionFind uf, int M, int N) {
        int pre = i * N + j;
        int res = uf.getSize(pre);
        for (int[] dir: DIRECTIONS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= M || y >= N || grid[x][y] != 1) continue;
            res = Math.max(res, uf.union(pre, x * N + y));
        }
        return res;
    }

    class UinionFind {
        int[] parent;
        int[] size;
        int[] rank;

        UinionFind(int[][] grid) {
            int M = grid.length;
            int N = grid[0].length;
            this.parent = new int[M * N];
            for (int i=0; i<M*N; i++) this.parent[i] = i;
            this.rank = new int[M * N];
            this.size = new int[M * N];
            Arrays.fill(this.size, 1);
        }

        int find(int x) {
            if (this.parent[x] != x) {
                this.parent[x] = find(this.parent[x]);
            }
            return this.parent[x];
        }

        int union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) return this.size[px];
            if (this.rank[px] > this.rank[py]) {
                this.parent[py] = px;
                this.size[px] = this.size[px] + this.size[py];
                return this.size[px];
            } else if (this.rank[px] < this.rank[py]) {
                this.parent[px] = py;
                this.size[py] = this.size[px] + this.size[py];
                return this.size[py];
            } else {
                this.parent[px] = py;
                this.rank[py]++;
                this.size[py] = this.size[px] + this.size[py];
                return this.size[py];
            }
        }

        int getSize(int x) {
            return this.size[find(x)];
        }
    }

}
