/**
 * We are playing the Guess Game. The game is as follows:
 * 
 * I pick a number from 1 to n. You have to guess which number I picked.
 * 
 * Every time you guess wrong, I'll tell you whether the number I picked is
 * higher or lower.
 * 
 * However, when you guess a particular number x, and you guess wrong, you
 * pay $x. You win the game when you guess the number I picked.
 * 
 * Example:
 * n = 10, I pick 8.
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * 
 * Game over. 8 is the number I picked.
 * You end up paying $5 + $7 + $9 = $21.
 * 
 * Given a particular n ≥ 1, find out how much money you need to have to
 * guarantee a win.
 */

public class GuessNumberHigherOrLowerII375 {
    // // LTE
    // public int getMoneyAmount(int n) {
    //     return getMoneyAmount(1, n);
    // }

    // public int getMoneyAmount(int l, int r) {
    //     if (l >= r) return 0;
    //     if (l + 1 == r) return l;
    //     if (l + 2 == r) return l + 1;

    //     int res = Integer.MAX_VALUE;
    //     for (int i=l; i<=r; i++) {
    //         int left = getMoneyAmount(l, i-1);
    //         int right = getMoneyAmount(i+1, r);
    //         res = Math.min(res, Math.max(left, right) + i);
    //     }
    //     return res;
    // }


    public int getMoneyAmount(int n) {
        return getMoneyAmount(1, n, new int[n][n]);
    }

    public int getMoneyAmount(int l, int r, int[][] memo) {
        if (l >= r) return 0;
        if (memo[l-1][r-1] != 0) return memo[l-1][r-1];
        int res = Integer.MAX_VALUE;
        for (int i=l; i<=r; i++) {
            int left = getMoneyAmount(l, i-1, memo);
            int right = getMoneyAmount(i+1, r, memo);
            res = Math.min(res, Math.max(left, right) + i);
        }
        memo[l-1][r-1] = res;
        return res;
    }


    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n+1][n+1];
        for (int l=1; l<=n; l++) {
            for (int i=1; i<=n-l; i++) {
                int j = i+l;
                int tmp = Integer.MAX_VALUE;
                for (int k=i; k<=j; k++) {
                    int left = i <= k-1 ? dp[i][k-1] : 0;
                    int right = k+1 <= j ? dp[k+1][j] : 0;
                    tmp = Math.min(tmp, Math.max(left, right) + k);
                }
                dp[i][j] = tmp;
            }
        }
        return dp[1][n];
    }


    public int getMoneyAmount3(int n) {
        int[][] dp = new int[n+1][n+1];
        for (int l=1; l<=n; l++) {
            for (int i=1; i<=n-l; i++) {
                int j = i+l;
                int tmp = Integer.MAX_VALUE;
                for (int k=i+(l-1)/2; k<=j; k++) {
                    int left = i <= k-1 ? dp[i][k-1] : 0;
                    int right = k+1 <= j ? dp[k+1][j] : 0;
                    tmp = Math.min(tmp, Math.max(left, right) + k);
                }
                dp[i][j] = tmp;
            }
        }
        return dp[1][n];
    }

}
