/**
 * There is a fence with n posts, each post can be painted with one of the k
 * colors.
 * 
 * You have to paint all the posts such that no more than two adjacent fence
 * posts have the same color.
 * 
 * Return the total number of ways you can paint the fence.
 * 
 * Note:
 * n and k are non-negative integers.
 * 
 * Example:
 * 
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * 
 *             post1  post2  post3      
 *  -----      -----  -----  -----       
 *    1         c1     c1     c2 
 *    2         c1     c2     c1 
 *    3         c1     c2     c2 
 *    4         c2     c1     c1  
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 */

public class PaintFence276 {
    public int numWays(int n, int k) {
        if (k == 0 || n == 0) return 0;
        int[][] dp = new int[n][2];
        dp[0][0] = k;
        dp[0][1] = 0;
        for (int i=1; i<n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) * (k - 1);
            dp[i][1] = dp[i-1][0];
        }
        return dp[n-1][0] + dp[n-1][1];
    }
  
    public int numWays2(int n, int k) {
        if (k == 0 || n == 0) return 0;
        int diff = k;
        int same = 0;
        for (int i=1; i<n; i++) {
            int s = same;
            int d = diff;
            diff = (s + d) * (k - 1);
            same = d;
        }
        return same + diff;
    }

}
