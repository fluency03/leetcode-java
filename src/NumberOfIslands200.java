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


publicclass NumberOfIslands200 {
    public int numIslands(char[][] grid) {
        int lenY = grid.length;
        if (lenY == 0) return 0;

        int lenX = grid[0].length;
        if (lenX == 0) return 0;

        boolean[][] marked = new boolean[lenY][lenX];

        int res = 0;
        for (int j = 0; j < lenY; j++) {
            for (int i = 0; i < lenX; i++) {
                if (grid[j][i] == '0' || marked[j][i]) continue;
                findIsland(i, j, lenX, lenY, grid, marked);
                res++;
            }
        }

        return res;
    }

    private void findIsland(int x, int y, int lenX, int lenY, char[][] grid, boolean[][] marked) {
        if (x < 0 || x >= lenX || y < 0 || y >= lenY || grid[y][x] == '0' || marked[y][x]) return;

        marked[y][x] = true;
        findIsland(x, y-1, lenX, lenY, grid, marked);
        findIsland(x+1, y, lenX, lenY, grid, marked);
        findIsland(x, y+1, lenX, lenY, grid, marked);
        findIsland(x-1, y, lenX, lenY, grid, marked);
    }

}
