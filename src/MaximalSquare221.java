/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 4.
 *
 */


public class MaximalSquare221 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int y = matrix.length;
        int x = matrix[0].length;

        int len = 0;
        int[][] dp = new int[y+1][x+1];
        for (int j=1; j<=y; j++) {
            for (int i=1; i<=x; i++) {
                if (matrix[j-1][i-1] == '0') continue;
                if (i == 1 || j == 1 || matrix[j-2][i-2] == '0') {
                    dp[j][i] = 1;
                    len = Math.max(len, 1);
                } else if (matrix[j-1][i-1] == '1') {
                    int preLen = dp[j-1][i-1];
                    int p = 1;
                    while (p <= preLen) {
                        if (matrix[j-1-p][i-1] == '0' || matrix[j-1][i-1-p] == '0') break;
                        p++;
                    }
                    int newLen = p;
                    dp[j][i] = newLen;
                    len = Math.max(len, newLen);
                }
            }
        }
        return len * len;
    }


    /**
     * https://leetcode.com/problems/maximal-square/solution/
     */
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }


    /**
     * https://leetcode.com/problems/maximal-square/solution/
     */
    public int maximalSquare3(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

}
