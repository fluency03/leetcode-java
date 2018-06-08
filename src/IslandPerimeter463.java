/**
 * You are given a map in form of a two-dimensional integer grid where 1
 * represents land and 0 represents water. Grid cells are connected
 * horizontally/vertically (not diagonally). The grid is completely surrounded
 * by water, and there is exactly one island (i.e., one or more connected land
 * cells). The island doesn't have "lakes" (water inside that isn't connected
 * to the water around the island). One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the
 * perimeter of the island.
 * 
 * Example:
 * 
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * 
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 *    https://leetcode.com/static/images/problemset/island.png
 */


public class IslandPerimeter463 {
    private static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += perimeter(grid, i, j, grid.length, grid[0].length);
                }
            }
        }
        return res;
    }

    private int perimeter(int[][] grid, int i, int j, int n, int m) {
        int res = 0;
        for (int[] d: dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
                res++;
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/island-perimeter/discuss/95001/clear-and-easy-java-solution
     */
    public int islandPerimeter2(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }

}
