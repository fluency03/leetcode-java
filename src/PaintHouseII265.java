/**
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color.
 * 
 * The cost of painting each house with a certain color is represented by a
 * n x k cost matrix. For example, costs[0][0] is the cost of painting house
 * 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2,
 * and so on... Find the minimum cost to paint all houses.
 * 
 * Note:
 * All costs are positive integers.
 * 
 * Example:
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
 *           Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
 * 
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */

public class PaintHouseII265 {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int N = costs.length;
        int K = costs[0].length;
        int[][] dp = new int[N + 1][K];
        
        for (int i=1; i<=N; i++) {
            int[] mins = minIndex(dp, i-1, K);
            for (int k=0; k<K; k++) {
                dp[i][k] = mins[k] + costs[i-1][k];
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int c: dp[N]) {
            if (c < res) res = c;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    private int[] minIndex(int[][] dp, int i, int K) {
        int[] mins = new int[K];
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int k=0; k<K; k++) {
            if (dp[i][k] <= min1) {
                min2 = min1;
                min1 = dp[i][k];
            } else if (dp[i][k] > min1 && dp[i][k] < min2) {
                min2 = dp[i][k];
            }
        }
        if (min2 == Integer.MAX_VALUE) min2 = min1;
        for (int k=0; k<K; k++) {
            if (dp[i][k] == min1) {
                mins[k] = min2;
            } else {
                mins[k] = min1;
            }
        }
        return mins;
    }
  

    public int minCostII2(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int N = costs.length;
        int K = costs[0].length;
        int[] dp = new int[K];
        
        for (int i=1; i<=N; i++) {
            minIndex(dp, K);
            for (int k=0; k<K; k++) {
                dp[k] = dp[k] + costs[i-1][k];
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int c: dp) {
            if (c < res) res = c;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private void minIndex(int[] dp, int K) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int k=0; k<K; k++) {
            if (dp[k] <= min1) {
                min2 = min1;
                min1 = dp[k];
            } else if (dp[k] > min1 && dp[k] < min2) {
                min2 = dp[k];
            }
        }
        if (min2 == Integer.MAX_VALUE) min2 = min1;
        for (int k=0; k<K; k++) {
            if (dp[k] == min1) {
                dp[k] = min2;
            } else {
                dp[k] = min1;
            }
        }
    }


    public int minCostII3(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int N = costs.length;
        int K = costs[0].length;
        int[] dp = new int[K];
        int min1 = 0;
        int min2 = 0;
        for (int i=0; i<N; i++) {
            int pre1 = min1;
            int pre2 = min2;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for (int k=0; k<K; k++) {
                dp[k] = (dp[k] == pre1 ? pre2 : pre1) + costs[i][k];
                if (dp[k] <= min1) {
                    min2 = min1;
                    min1 = dp[k];
                } else if (dp[k] > min1 && dp[k] < min2) {
                    min2 = dp[k];
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int c: dp) {
            if (c < res) res = c;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    public int minCostII4(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int N = costs.length;
        int K = costs[0].length;
        int[] dp = new int[K];
        int min1 = 0;
        int min2 = 0;
        for (int i=0; i<N; i++) {
            int pre1 = min1;
            int pre2 = min2;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for (int k=0; k<K; k++) {
                dp[k] = (dp[k] == pre1 ? pre2 : pre1) + costs[i][k];
                if (dp[k] <= min1) {
                    min2 = min1;
                    min1 = dp[k];
                } else if (dp[k] > min1 && dp[k] < min2) {
                    min2 = dp[k];
                }
            }
        }
        return min1;
    }


    public int minCostII5(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int N = costs.length;
        int K = costs[0].length;
        int min1 = 0;
        int minIdx = -1;
        int min2 = 0;
        for (int i=0; i<N; i++) {
            int pre1 = min1;
            int pre2 = min2;
            int preIdx = minIdx;
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            int tmp = 0;
            for (int k=0; k<K; k++) {
                tmp = (preIdx == k ? pre2 : pre1) + costs[i][k];
                if (tmp <= min1) {
                    min2 = min1;
                    min1 = tmp;
                    minIdx = k;
                } else if (tmp > min1 && tmp < min2) {
                    min2 = tmp;
                }
            }
        }
        return min1;
    }

}
