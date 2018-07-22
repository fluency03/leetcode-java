/**
 * Given an m x n matrix of positive integers representing the height of each
 * unit cell in a 2D elevation map, compute the volume of water it is able to
 * trap after raining.
 * 
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than
 * 0 and is less than 20,000.
 * 
 * Example:
 * 
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 * 
 * Return 4.
 * 
 * https://leetcode.com/static/images/problemset/rainwater_empty.png
 * 
 * The above image represents the elevation map
 * [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 * 
 * https://leetcode.com/static/images/problemset/rainwater_fill.png
 * 
 * After the rain, water is trapped between the blocks. The total volume of
 * water trapped is 4.
 */

public class TrappingRainWaterII407 {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) return 0;
        int M = heightMap.length;
        int N = heightMap[0].length;
        boolean[][] visited = new boolean[M][N];
        // lambda function is much slower than anonymous Comparator class,
        // due to first-time initialization overhead of lambda expressions
        PriorityQueue<Pair> pq = new PriorityQueue<>(1, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.value - b.value;
            }
        });
        for (int j=0; j<N; j++) {
            pq.add(new Pair(0, j, heightMap[0][j]));
            visited[0][j] = true;
            pq.offer(new Pair(M-1, j, heightMap[M-1][j]));
            visited[M-1][j] = true;
        }
        for (int i=1; i<M-1; i++) {
            pq.add(new Pair(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            pq.offer(new Pair(i, N-1, heightMap[i][N-1]));
            visited[i][N-1] = true;
        }

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int res = 0;
        int max = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            max = Math.max(max, top.value);
            for (int[] d: directions) {
                int x = top.x + d[0];
                int y = top.y + d[1];
                if (x < 0 || y < 0 || x >= M || y >= N || visited[x][y]) continue;
                if (max > heightMap[x][y]) res += max - heightMap[x][y];
                pq.offer(new Pair(x, y, heightMap[x][y]));
                visited[x][y] = true;
            }

        }

        return res;
    }

    class Pair {
        int x;
        int y;
        int value;
        Pair(int i, int j, int v) {
            x = i;
            y = j;
            value = v;
        }
    }


    /**
     * https://leetcode.com/problems/trapping-rain-water-ii/discuss/89477/Java-solution-beating-100
     */
    private static class Cell implements Comparable<Cell> {
        private int row;
        private int col;
        private int value;
        public Cell(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.value = v;
        }
        @Override
        public int compareTo(Cell other) {
            return value - other.value;
        }
    }
    private int water;
    private boolean[][] visited1;
    public int trapRainWater2(int[][] heightMap) {
        if (heightMap.length == 0) return 0;
        PriorityQueue<Cell> walls = new PriorityQueue<Cell>();
        water = 0;
        visited1 = new boolean[heightMap.length][heightMap[0].length];
        int rows = heightMap.length, cols = heightMap[0].length;
        //build wall;
        for (int c = 0; c < cols; c++) {
          walls.add(new Cell(0, c, heightMap[0][c]));
          walls.add(new Cell(rows - 1, c, heightMap[rows - 1][c]));
          visited1[0][c] = true;
          visited1[rows - 1][c] = true;
        }
        for (int r = 1; r < rows - 1; r++) {
          walls.add(new Cell(r, 0, heightMap[r][0]));
          walls.add(new Cell(r, cols - 1, heightMap[r][cols - 1]));
          visited1[r][0] = true;
          visited1[r][cols - 1] = true;
        }
        //end build wall;
        while(walls.size() > 0) {
            Cell min = walls.poll();
            visit(heightMap, min, walls);
        }
        return water;
    }
    private void visit(int[][] height, Cell start, PriorityQueue<Cell> walls) {
        fill(height, start.row + 1, start.col, walls, start.value);
        fill(height, start.row - 1, start.col, walls, start.value);
        fill(height, start.row, start.col + 1, walls, start.value);
        fill(height, start.row, start.col - 1, walls, start.value);
    }
    private void fill(int[][] height, int row, int col, PriorityQueue<Cell> walls, int min) {
        if (row < 0 || col < 0) return;
        else if (row >= height.length || col >= height[0].length) return;
        else if (visited1[row][col]) return;
        else if (height[row][col] >= min) {
            walls.add(new Cell(row, col, height[row][col]));
            visited1[row][col] = true;
            return;
        } else {
            water += min - height[row][col];
            visited1[row][col] = true;
            fill(height, row + 1, col, walls, min);
            fill(height, row - 1, col, walls, min);
            fill(height, row, col + 1, walls, min);
            fill(height, row, col - 1, walls, min);
        }
    }


}
