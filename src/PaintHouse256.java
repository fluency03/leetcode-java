/**
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a
 * n x 3 cost matrix. For example, costs[0][0] is the cost of painting house
 * 0 with color red; costs[1][2] is the cost of painting house 1 with
 * color green, and so on... Find the minimum cost to paint all houses.
 * 
 * Note:
 * All costs are positive integers.
 * 
 * Example:
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint
 * house 2 into blue. Minimum cost: 2 + 5 + 3 = 10.
 */

public class PaintHouse256 {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int N = costs.length;
        int[][] dp = new int[N + 1][3];
        for (int i=1; i<=N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i-1][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i-1][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i-1][2];
        }
        return Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
    }


    public int minCost2(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int N = costs.length;
        int red = 0;
        int blue = 0;
        int green = 0;
        for (int i=1; i<=N; i++) {
            int r = red;
            int b = blue;
            int g = green;
            red = Math.min(b, g) + costs[i-1][0];
            blue = Math.min(r, g) + costs[i-1][1];
            green = Math.min(r, b) + costs[i-1][2];
        }
        return Math.min(red, Math.min(blue, green));
    }

}
