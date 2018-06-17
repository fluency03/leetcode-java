/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may
 * perform an addLand operation which turns the water at position (row, col)
 * into a land. Given a list of positions to operate, count the number of
 * islands after each addLand operation. An island is surrounded by water and
 * is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 * 
 * Example:
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * 
 * Explanation:
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water
 * and 1 represents land).
 * 
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * 
 * Follow up:
 * Can you do it in time complexity O(k log mn), where k is the length of the
 * positions?
 */


public class NumberOfIslandsII305 {
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // time complexity too high
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        List<Integer> res = new ArrayList<>();
        
        int num = 0;
        for (int[] pos: positions) {
            num += addNumIslands(grid, pos[0], pos[1], m, n);
            res.add(num);
            grid[pos[0]][pos[1]] = 1;
        }
        
        return res;
    }

    private int addNumIslands(int[][] grid, int i, int j, int m, int n) {
        int neighbors = 0;
        boolean[][] visited = new boolean[m][n];
        for (int[] d: dirs) {
            if (isNeighbor(grid, i+d[0], j+d[1], visited, m, n)) neighbors++;
        }
        return neighbors == 0 ? 1 : 1 - neighbors;
    }
    
    private boolean isNeighbor(int[][] grid, int i, int j, boolean[][] visited, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0 || visited[i][j]) return false;
        travese(grid, i, j, visited, m, n);
        return true;
    }
    
    private void travese(int[][] grid, int i, int j, boolean[][] visited, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0 || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] d: dirs) {
            travese(grid, i+d[0], j+d[1], visited, m, n);
        }
    }


    // Disjoint Set (Union-Find)
    public List<Integer> numIslands22(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        DisjointSet ds = new DisjointSet(m * n);
        List<Integer> res = new ArrayList<>();

        int num = 0;
        for (int[] pos: positions) {
            
            Set<Integer> set = new HashSet<>();
            for (int[] d: dirs) {
                int i = pos[0] + d[0];
                int j = pos[1] + d[1];
                if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) continue;
                int idx = posToIndex(i, j, n);
                set.add(ds.find(idx));
            }
            int neighbors = set.size();
            int numDiff = neighbors == 0 ? 1 : 1 - neighbors;
            num += numDiff;
            res.add(num);
            grid[pos[0]][pos[1]] = 1;
            int curr = posToIndex(pos[0], pos[1], n);
            for (int nb: set) {
                ds.union(curr, nb);
            }
        }
        
        return res;
    }

    private int posToIndex(int i, int j, int n) {
        return i * n + j;
    }

    class DisjointSet {
        private int[] parent;
        public DisjointSet(int n) {
            parent = new int[n];
            for (int i=0; i<n; i++) parent[i] = i;
        } 

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xx = find(x);
            int yy = find(y);
            parent[xx] = yy;
        }
    }

    class DisjointSetWithRank {
        private int[] parent;
        private int[] rank;
        public DisjointSetWithRank(int n) {
            parent = new int[n];
            for (int i=0; i<n; i++) parent[i] = i;
            rank = new int[n];
        } 

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xx = find(x);
            int yy = find(y);

            if (rank[xx] < rank[yy]) {
                parent[xx] = yy;
            } else if (rank[xx] > rank[yy]) {
                parent[yy] = xx;
            } else {
                parent[xx] = yy;
                rank[yy]++;
            }
        }
    }


    /**
     * https://leetcode.com/problems/number-of-islands-ii/discuss/75470/Easiest-Java-Solution-with-Explanations
     */
    public List<Integer> numIslands23(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(m <= 0 || n <= 0) return result;
    
        int count = 0;                      // number of islands
        int[] roots = new int[m * n];       // one island = one tree
        Arrays.fill(roots, -1);            
    
        for(int[] p : positions) {
            int root = n * p[0] + p[1];     // assume new point is isolated island
            roots[root] = root;             // add new island
            count++;
    
            for(int[] dir : dirs) {
                int x = p[0] + dir[0]; 
                int y = p[1] + dir[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;
                
                int rootNb = findIsland(roots, nb);
                if(root != rootNb) {        // if neighbor is in another island
                    roots[root] = rootNb;   // union two islands 
                    root = rootNb;          // current tree root = joined tree root
                    count--;               
                }
            }
    
            result.add(count);
        }
        return result;
    }
    
    public int findIsland(int[] roots, int id) {
        while(id != roots[id]) {
            roots[id] = roots[roots[id]];   // only one line added
            id = roots[id];
        }
        return id;
    }

}
