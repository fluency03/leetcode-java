/**
 * 
 */


public class ZeroOneKnapsack {

    /*
        Q1. V 的起始点为 Ci 还是 0？ Ci 起始是否有效？ 为什么？
        Q2. V 起始 bound 为 bound = max(V - sum(Ci ... Cn), Ci)。 为什么？
        Q3. 初始化值，恰好装满背包 vs 没有要求必须把背包装满？
    */

    /**
     * dp[i][v] = max{dp[i-1][v], dp[i-1][v-c[i]] + w[i]}
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
                    dp[i][v] = Math.max(dp[i-1][v], dp[i-1][v - costs[i-1]] + weights[i-1]);
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
            for (int v=V; v>=costs[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - costs[i]] + weights[i]);
            }
        }
        return dp[V];
    }


    public static void main(String[] args) {
        ZeroOneKnapsack slt = new ZeroOneKnapsack();

        int[] costs = new int[]{10, 20, 30};
        int[] weights = new int[]{60, 100, 120};
        System.out.println(slt.maxValue(costs, weights, 50));
        System.out.println(slt.maxValue2(costs, weights, 50));

        costs = new int[]{10, 20, 30};
        weights = new int[]{60, 100, 10};
        System.out.println(slt.maxValue(costs, weights, 50));
        System.out.println(slt.maxValue2(costs, weights, 50));

        costs = new int[]{2, 2, 6, 5, 4};
        weights = new int[]{6, 3, 5, 4, 6};
        System.out.println(slt.maxValue(costs, weights, 10));
        System.out.println(slt.maxValue2(costs, weights, 10));

        costs = new int[]{2, 2, 6, 5, 4};
        weights = new int[]{6, 3, 10, 4, 6};
        System.out.println(slt.maxValue(costs, weights, 10));
        System.out.println(slt.maxValue2(costs, weights, 10));

        costs = new int[]{5, 5, 5, 5, 4};
        weights = new int[]{6, 3, 5, 4, 6};
        System.out.println(slt.maxValue(costs, weights, 10));
        System.out.println(slt.maxValue2(costs, weights, 10));

        costs = new int[]{2, 2, 6, 5, 4};
        weights = new int[]{6, 3, 5, 4, 6};
        System.out.println(slt.maxValue(costs, weights, 10));
        System.out.println(slt.maxValue2(costs, weights, 10));

        costs = new int[]{};
        weights = new int[]{};
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


    // for i=1..N
    // ZeroOnePack(c[i],w[i]);
    // procedure ZeroOnePack(cost,weight)
    // for v=V..cost
    //     f[v]=max{f[v],f[v-cost]+weight}


}
