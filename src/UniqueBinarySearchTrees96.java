/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */


public class UniqueBinarySearchTrees96 {
    public int numTrees(int n) {
        int[][] dp = new int[n][n];
        for (int s=0; s<n; s++) dp[s][s] = 1;

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                helper(i, j, dp);
            }
        }

        return dp[0][n-1];
    }

    private int helper(int i, int j, int[][] dp) {
        if (i > j) return 1;
        if (dp[i][j] != 0) return dp[i][j];

        int u = 0;
        for (int r=i; r<=j; r++) {
            int left = helper(i, r-1, dp);
            int right = helper(r+1, j, dp);
            u += left * right;
        }
        dp[i][j] = u;

        return u;
    }


    public int numTrees2(int n) {
        int[] dp = new int[n];
        return helper2(n-1, dp);
    }

    private int helper2(int i, int[] dp) {
        if (i <= 0) return 1;
        if (dp[i] != 0) return dp[i];

        int u = 0;
        for (int j=0; j<=i; j++) {
            int left = helper2(j-1, dp);
            int right = helper2(i-j-1, dp);
            u += left * right;
        }
        dp[i] = u;

        return u;
    }


    /**
     * https://discuss.leetcode.com/topic/37310/fantastic-clean-java-dp-solution-with-detail-explaination
     */
    public int numTrees3(int n) {
       int [] dp = new int[n+1];
       dp[0]= 1;
       dp[1] = 1;
       for(int level = 2; level <=n; level++)
           for(int root = 1; root<=level; root++)
               dp[level] += dp[level-root]*dp[root-1];
       return dp[n];
   }

}
