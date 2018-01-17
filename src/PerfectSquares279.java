/**
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13,
 * return 2 because 13 = 4 + 9.
 *
 */


public class PerfectSquares279 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            int sq = (int) Math.sqrt(i);
            int curr = Integer.MAX_VALUE;
            for (int j=sq; j>=1; j--) {
                curr = Math.min(curr, dp[i-j*j]+1);
            }
            dp[i] = curr;
        }
        return dp[n];
    }


    public static void main(String[] args) {
        PerfectSquares279 ps = new PerfectSquares279();
        System.out.println(ps.numSquares(12));
        System.out.println(ps.numSquares(13));

    }

}
