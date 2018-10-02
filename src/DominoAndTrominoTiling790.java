/**
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape.
 * These shapes may be rotated.
 * 
 * XX  <- domino
 * 
 * XX  <- "L" tromino
 * X
 * Given N, how many ways are there to tile a 2 x N board? Return your answer
 * modulo 10^9 + 7.
 * 
 * (In a tiling, every square must be covered by a tile. Two tilings are
 * different if and only if there are two 4-directionally adjacent cells on
 * the board such that exactly one of the tilings has both squares occupied
 * by a tile.)
 * 
 * Example:
 * Input: 3
 * Output: 5
 * Explanation: 
 * The five different ways are listed below, different letters indicates
 * different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 */

public class DominoAndTrominoTiling790 {

    private static int MOD = 1000000007;

    public int numTilings(int N) {
        long[][] dp = new long[N+1][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i=2; i<=N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-2][0] + dp[i-1][1]) % MOD;
        }
        return (int) dp[N][0];
    }


    public int numTilings2(int N) {
        long[][] dp = new long[N+1][2];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i=2; i<=N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + 2 * dp[i-1][1]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-1][1]) % MOD;
        }
        return (int) dp[N][0];
    }


    public int numTilings3(int N) {
        long[][] dp = new long[3][2];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i=2; i<=N; i++) {
            dp[i%3][0] = (dp[(i+2)%3][0] + dp[(i+1)%3][0] + 2 * dp[(i+2)%3][1]) % MOD;
            dp[i%3][1] = (dp[(i+1)%3][0] + dp[(i+2)%3][1]) % MOD;
        }
        return (int) dp[N%3][0];
    }


    public int numTilings4(int N) {
        if (N == 0) return 1;
        if (N == 1 || N == 2) return N; 
        long[] dp = new long[N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i=3; i<=N; i++) {
            dp[i] = (dp[i - 3] + dp[i - 1] * 2) % MOD;
        }
        return (int) dp[N];
    }


    public int numTilings5(int N) {
        if (N == 0) return 1;
        if (N == 1 || N == 2) return N; 
        long[] dp = new long[4];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i=3; i<=N; i++) {
            dp[i%4] = (dp[(i+1)%4] + dp[(i+3)%4] * 2) % MOD;
        }
        return (int) dp[N%4];
    }


    /**
     * https://leetcode.com/problems/domino-and-tromino-tiling/solution/
     */
    public int numTilings6(int N) {
        long[] dp = new long[]{1, 0, 0, 0};
        for (int i = 0; i < N; ++i) {
            long[] ndp = new long[4];
            ndp[0b00] = (dp[0b00] + dp[0b11]) % MOD;
            ndp[0b01] = (dp[0b00] + dp[0b10]) % MOD;
            ndp[0b10] = (dp[0b00] + dp[0b01]) % MOD;
            ndp[0b11] = (dp[0b00] + dp[0b01] + dp[0b10]) % MOD;
            dp = ndp;
        }
        return (int) dp[0];
    }

}
