/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */


public class MinimumPathSum64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = Integer.MAX_VALUE;

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int i = 1; i < n; i++)
            dp[0][i] = grid[0][i] + dp[0][i - 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }


    /**
     * https://discuss.leetcode.com/topic/22732/my-solution-beats-100-java-solutions
     */
    public int minPathSum1(int[][] grid) {
        if(grid.length == 0)  return 0;

        int r = grid.length;
        int c = grid[0].length;

        for(int i=0;i<r; i++) {
            for(int j=0; j<c; j++) {
                int leftSum = (j>0) ? grid[i][j-1] : Integer.MAX_VALUE;
                int topSum = (i>0) ? grid[i-1][j] : Integer.MAX_VALUE;
                if(i==0 && j==0) continue;

                grid[i][j] += Math.min(leftSum, topSum);
            }
        }
        return grid[r-1][c-1];
    }


    /**
     * https://discuss.leetcode.com/topic/38213/my-java-solution-using-dp-with-memorization-beats-about-98-submissions
     */
    public int minPathSum2(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        return minPathSumHelper(grid, 0, 0, memo);
    }

    public int minPathSumHelper(int[][] grid, int row, int col, int[][] memo) {
        if(row == grid.length-1 && col == grid[0].length-1) return grid[row][col];
        if(memo[row][col] != 0) return memo[row][col];

        int rowInc = Integer.MAX_VALUE, colInc = Integer.MAX_VALUE;
        if(row < grid.length-1) rowInc = minPathSumHelper(grid, row+1, col, memo);
        if(col < grid[0].length-1) colInc = minPathSumHelper(grid, row, col+1, memo);
        memo[row][col] = Math.min(rowInc, colInc) + grid[row][col];
        return memo[row][col];
    }


    /**
     * https://discuss.leetcode.com/topic/85826/java-solution-1ms-recursive-and-4ms-iterative
     */
    public int minPathSum3(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return minPathSum(grid, 0, 0, dp);
    }

    public int minPathSum(int[][] grid, int i, int j, int[][] dp) {
        if (i == grid.length || j == grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int min = grid[i][j];
        min += Math.min(minPathSum(grid, i, j + 1, dp), minPathSum(grid, i + 1, j, dp));
        dp[i][j] = min;
        return min;
    }


    public int minPathSum4(int[][] grid) {
        int M = grid.length;
        if (M == 0) return 0;
        int N = grid[0].length;
        if (N == 0) return 0; 
        for (int j=1; j<N; j++) grid[0][j] += grid[0][j-1];
        for (int i=1; i<M; i++) grid[i][0] += grid[i-1][0];
        for (int i=1; i<M; i++) {
            for (int j=1; j<N; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[M-1][N-1];
    }

}
