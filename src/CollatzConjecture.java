/**
 * 
 */

public class CollatzConjecture {

    // public int maxSteps(int n) {
    //     if (n < 1) return -1;
    //     int res = -1;
    //     for (int i=1; i<=n; i++) {
    //         res = Math.max(res, steps(i));
    //     }
    //     return res;
    // }

    // public int steps(int i) {
    //     int res = 0;
    //     while (i != 1) {
    //         if (i % 2 == 0) {
    //             i = i / 2;
    //         } else {
    //             i = i * 3 + 1;
    //         }
    //     }
    //     return res;
    // }

    public int maxSteps(int n) {
        if (n < 1) return -1;
        int res = -1;
        int[] memo = new int[n+1];
        for (int i=1; i<=n; i++) {
            res = Math.max(res, steps(i, memo));
        }
        return res;
    }

    public int steps(int i, int[] memo) {
        int res = 0;
        int p = i;
        while (p != 1) {
            if (memo[p] > 0) {
                memo[i] = res = memo[p];
                return memo[i];
            }
            if (p % 2 == 0) {
                p = p / 2;
            } else {
                p = p * 3 + 1;
            }
        }
        memo[i] = res;
        return res;
    }

}
