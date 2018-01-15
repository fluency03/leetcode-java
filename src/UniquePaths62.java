/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start'
 * in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 *
 * How many possible unique paths are there?
 *
 * https://leetcode.com/static/images/problemset/robot_maze.png
 *
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 */


public class UniquePaths62 {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int s = Math.min(m, n);
        int l = Math.max(m, n);
        int[][] dp = new int[s][l];
        return uniquePaths(m, n, dp);
    }

    private int uniquePaths(int m, int n, int[][] dp) {
        if (m == 1 || n == 1) return 1;
        int s = Math.min(m, n);
        int l = Math.max(m, n);
        if (dp[s-1][l-1] != 0) return dp[s-1][l-1];

        int a = uniquePaths(s-1, l, dp);
        int b = uniquePaths(s, l-1, dp);
        dp[s-1][l-1] = a + b;
        return a + b;
    }

    public int uniquePaths2(int m, int n) {
        int[] row = new int[n];
        Arrays.fill(row,1);
        for ( int i = 1; i < m; i++){
            for ( int j = 1; j < n; j++){
                row[j]+=row[j-1];
            }
        }
        return row[n-1];
    }

    /**
     * https://discuss.leetcode.com/topic/31724/java-solution-0ms-4lines
     */
    public int uniquePaths3(int m, int n) {
        long result = 1;
        for(int i=0;i<Math.min(m-1,n-1);i++)
            result = result*(m+n-2-i)/(i+1);
        return (int)result;
    }

    /**
     * https://discuss.leetcode.com/topic/23866/clean-and-simple-dp-java
     */
    public int uniquePaths4(int m, int n) {
        int[][] grid = new int[m][n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(i==0||j==0)
                    grid[i][j] = 1;
                else
                    grid[i][j] = grid[i][j-1] + grid[i-1][j];
            }
        }
        return grid[m-1][n-1];
    }
}
