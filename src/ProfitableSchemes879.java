/**
 * There are G people in a gang, and a list of various crimes they could commit.
 * 
 * The i-th crime generates a profit[i] and requires group[i] gang members to
 * participate.
 * 
 * If a gang member participates in one crime, that member can't participate in
 * another crime.
 * 
 * Let's call a profitable scheme any subset of these crimes that generates at
 * least P profit, and the total number of gang members participating in that
 * subset of crimes is at most G.
 * 
 * How many schemes can be chosen?  Since the answer may be very large, return
 * ∂it modulo 10^9 + 7.
 * 
 * Example 1:
 * Input: G = 5, P = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation: 
 * To make a profit of at least 3, the gang could either commit crimes 0 and 1,
 * or just crime 1.
 * In total, there are 2 schemes.
 * 
 * Example 2:
 * Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation: 
 * To make a profit of at least 5, the gang could commit any crimes, as long
 * as they commit one.
 * 
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 * 
 * Note:
 * 1 <= G <= 100
 * 0 <= P <= 100
 * 1 <= group[i] <= 100
 * 0 <= profit[i] <= 100
 * 1 <= group.length = profit.length <= 100
 */

public class ProfitableSchemes879 {

    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int K = group.length;
        int[][][] dp = new int[K + 1][P + 1][G + 1];
        dp[0][0][0] = 1;
        int mod = (int)1e9 + 7;
        for (int k=1; k<=K; k++) {
            int pk = profit[k-1];
            int gk = group[k-1];
            for (int i=0; i<=P; i++) {
                for (int j=0; j<=G; j++) {
                    dp[k][i][j] = (dp[k-1][i][j] +  (j < gk ? 0 : dp[k-1][Math.max(0, i-pk)][j - gk])) % mod;
                }
            }
        }
        
        int res = 0;
        for (int a: dp[K][P]) {
            res = (res + a) % mod;
        }
        return res;
    }


    public int profitableSchemes2(int G, int P, int[] group, int[] profit) {
        int K = group.length;
        int[][] dp = new int[P + 1][G + 1];
        dp[0][0] = 1;
        int mod = (int)1e9 + 7;
        for (int k=1; k<=K; k++) {
            int pk = profit[k-1];
            int gk = group[k-1];
            
            int[][] tmp = new int[P + 1][G + 1];
            for (int i=0; i<=P; i++) {
                for (int j=0; j<=G; j++) {
                    tmp[i][j] = dp[i][j];
                }
            }
            
            for (int i=0; i<=P; i++) {
                for (int j=0; j<=G; j++) {
                    dp[i][j] = (tmp[i][j] +  (j < gk ? 0 : tmp[Math.max(0, i-pk)][j - gk])) % mod;
                }
            }
        }
        
        int res = 0;
        for (int a: dp[P]) {
            res = (res + a) % mod;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/profitable-schemes/solution/
     */
    public int profitableSchemes3(int G, int P, int[] group, int[] profit) {
        int MOD = 1_000_000_007;
        int N = group.length;
        long[][][] dp = new long[2][P+1][G+1];
        dp[0][0][0] = 1;

        for (int i = 0; i < N; ++i) {
            int p0 = profit[i];  // the current crime profit
            int g0 = group[i];  // the current crime group size

            long[][] cur = dp[i % 2];
            long[][] cur2 = dp[(i + 1) % 2];

            // Deep copy cur into cur2
            for (int jp = 0; jp <= P; ++jp)
                for (int jg = 0; jg <= G; ++jg)
                    cur2[jp][jg] = cur[jp][jg];

            for (int p1 = 0; p1 <= P; ++p1) {  // p1 : the current profit
                // p2 : the new profit after committing this crime
                int p2 = Math.min(p1 + p0, P);
                for (int g1 = 0; g1 <= G - g0; ++g1) {  // g1 : the current group size
                    // g2 : the new group size after committing this crime
                    int g2 = g1 + g0;
                    cur2[p2][g2] += cur[p1][g1];
                    cur2[p2][g2] %= MOD;
                }
            }
        }

        // Sum all schemes with profit P and group size 0 <= g <= G.
        long ans = 0;
        for (long x: dp[N%2][P])
            ans += x;

        return (int) (ans % MOD);
    }


    /**
     * https://leetcode.com/problems/profitable-schemes/discuss/154617/C++JavaPython-DP
     */
    public int profitableSchemes4(int G, int P, int[] group, int[] profit) {
        int[][] dp = new int[P + 1][G + 1];
        dp[0][0] = 1;
        int res = 0, mod = (int)1e9 + 7;
        for (int k = 0; k < group.length; k++) {
            int g = group[k], p = profit[k];
            for (int i = P; i >= 0; i--)
                for (int j = G - g; j >= 0; j--)
                    dp[Math.min(i + p, P)][j + g] = (dp[Math.min(i + p, P)][j + g] + dp[i][j]) % mod;
        }
        for (int x : dp[P]) res = (res + x) % mod;
        return res;
    }


    /**
     * https://www.youtube.com/watch?v=MjOIR61txFc
     */
    public int profitableSchemes5(int G, int P, int[] group, int[] profit) {
        int K = group.length;
        int[][] dp = new int[P + 1][G + 1];
        dp[0][0] = 1;
        int mod = (int)1e9 + 7;
        for (int k=1; k<=K; k++) {
            int pk = profit[k-1];
            int gk = group[k-1];
            for (int i=P; i>=0; i--) {
                int ip = Math.min(P, i + pk);
                for (int j=G-gk; j>=0; j--) {
                    dp[ip][j+gk] = (dp[ip][j+gk] + dp[i][j]) % mod;
                }
            }
        }

        int res = 0;
        for (int a: dp[P]) {
            res = (res + a) % mod;
        }
        return res;
    }


}
