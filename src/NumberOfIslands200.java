/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges
 * of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Answer: 1
 *
 * Example 2:
 *
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Answer: 3
 */


public class NumberOfIslands200 {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int res = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numIslands(grid, visited, i, j, n, m);
                    res++;
                }
            }
        }
        return res;
    }

    private void numIslands(char[][] grid, boolean[][] visited, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == '0' || visited[i][j]) return;
        visited[i][j] = true;
        numIslands(grid, visited, i-1, j, n, m);
        numIslands(grid, visited, i, j+1, n, m);
        numIslands(grid, visited, i+1, j, n, m);
        numIslands(grid, visited, i, j-1, n, m);
    }


    /**
     * https://leetcode.com/problems/number-of-islands/solution/
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }



    public int numIslands3(char[][] grid) {
        if (grid == null) return 0;
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        int res = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(grid, visited, i, j, n, m);
                    res++;
                }
            }
        }

        return res;
    }

    private void bfs(char[][] grid, boolean[][] visited, int i, int j, int n, int m) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i * m + j);
        visited[i][j] = true;
        while (!q.isEmpty()) {
            int v = q.remove();
            int ii = v / m;
            int jj = v % m;
            if (ii-1 >= 0 && grid[ii-1][jj] == '1' && !visited[ii-1][jj]) {
                visited[ii-1][jj] = true;
                q.add(v-m);
            }
            if (jj+1 < m && grid[ii][jj+1] == '1' && !visited[ii][jj+1]) {
                visited[ii][jj+1] = true;
                q.add(v+1);
            }
            if (ii+1 < n && grid[ii+1][jj] == '1' && !visited[ii+1][jj]) {
                visited[ii+1][jj] = true;
                q.add(v+m);
            }
            if (jj-1 >= 0 && grid[ii][jj-1] == '1' && !visited[ii][jj-1]) {
                visited[ii][jj-1] = true;
                q.add(v-1);
            }
        }
    }


    /**
     * https://leetcode.com/problems/number-of-islands/solution/
     */
    class UnionFind {
      int count; // # of connected components
      int[] parent;
      int[] rank;

      public UnionFind(char[][] grid) { // for problem 200
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; ++i) {
          for (int j = 0; j < n; ++j) {
            int val = i * n + j;
            if (grid[i][j] == '1') {
              parent[val] = val;
              ++count;
            }
            rank[val] = 0;
          }
        }
      }

      public int find(int i) { // path compression
        if (parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
      }

      public void union(int x, int y) { // union with rank
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
          if (rank[rootx] > rank[rooty]) {
            parent[rooty] = rootx;
          } else if (rank[rootx] < rank[rooty]) {
            parent[rootx] = rooty;
          } else {
            parent[rooty] = rootx;
            rank[rootx] += 1;
          }
          --count;
        }
      }

      public int getCount() {
        return count;
      }
    }

    public int numIslands4(char[][] grid) {
      if (grid == null || grid.length == 0) {
        return 0;
      }

      int nr = grid.length;
      int nc = grid[0].length;
      int num_islands = 0;
      UnionFind uf = new UnionFind(grid);
      for (int r = 0; r < nr; ++r) {
        for (int c = 0; c < nc; ++c) {
          if (grid[r][c] == '1') {
            int val = r * nc + c;
            grid[r][c] = '0';
            if (r - 1 >= 0 && grid[r-1][c] == '1') {
              uf.union(val, val - nc);
            }
            if (r + 1 < nr && grid[r+1][c] == '1') {
              uf.union(val, val + nc);
            }
            if (c - 1 >= 0 && grid[r][c-1] == '1') {
              uf.union(val, val - 1);
            }
            if (c + 1 < nc && grid[r][c+1] == '1') {
              uf.union(val, val + 1);
            }
          }
        }
      }

      return uf.getCount();
    }

}
