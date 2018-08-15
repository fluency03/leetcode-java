/**
 * There are N objects and a bag with capacity of V. Evey type of object can be
 * used with unlimited number of times.
 * The ith object is having costs[i] and weights[i].
 * Find out which objects can be put into the bag in order to have maximum value.
 */


public class CompleteKnapsack {

    /**
     * dp[i][v] = max{dp[i-1][v], dp[i][v-c[i]] + w[i]}
     */
    public int maxValue(int[] costs, int[] weights, int V) {
        if (costs == null || weights == null) return 0;
        int N = costs.length;
        int[][] dp = new int[N + 1][V + 1];

        for (int i=1; i<=N; i++) {
            for (int v=1; v<=V; v++) {
                if (v < costs[i-1]) {
                    dp[i][v] = dp[i-1][v];
                } else {
                    dp[i][v] = Math.max(dp[i-1][v], dp[i][v - costs[i-1]] + weights[i-1]);
                }
            }
        }
        return dp[N][V];
    }


    /**
     * dp[v] = max{dp[v], dp[v-c[i]] + w[i]}
     */
    public int maxValue2(int[] costs, int[] weights, int V) {
        if (costs == null || weights == null) return 0;
        int N = costs.length;
        int[] dp = new int[V + 1];

        for (int i=0; i<N; i++) {
            for (int v=costs[i]; v<=V; v++) {
                dp[v] = Math.max(dp[v], dp[v - costs[i]] + weights[i]);
            }
        }
        return dp[V];
    }


    public static void main(String[] args) {
        CompleteKnapsack slt = new CompleteKnapsack();

        int[] costs = new int[]{};
        int[] weights = new int[]{};
        System.out.println(slt.maxValue(costs, weights, 10));
        System.out.println(slt.maxValue2(costs, weights, 10));

        costs = new int[]{2};
        weights = new int[]{2};
        System.out.println(slt.maxValue(costs, weights, 2));
        System.out.println(slt.maxValue2(costs, weights, 2));

        costs = new int[]{2};
        weights = new int[]{2};
        System.out.println(slt.maxValue(costs, weights, 3));
        System.out.println(slt.maxValue2(costs, weights, 3));

        costs = new int[]{2};
        weights = new int[]{2};
        System.out.println(slt.maxValue(costs, weights, 1));
        System.out.println(slt.maxValue2(costs, weights, 1));





    }


    // procedure CompletePack(cost,weight)
    // for v=cost..V
    //     f[v]=max{f[v],f[v-c[i]]+w[i]}

}
