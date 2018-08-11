/**
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you
 * need to make up that amount. If that amount of money cannot be made up by
 * any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 */

public class CoinChange322 {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return helper(coins.length-1, coins, amount);
    }

    private int helper(int idx, int[] coins, int left) {
        if (idx < 0) return -1;

        int curr = coins[idx];
        int multi = left / curr;
        int currLeft = left % curr;
        if (currLeft == 0) return multi;

        int res = Integer.MAX_VALUE;
        int reduce = multi;
        int newLeft = currLeft;

        while(reduce >= 0) {
            int temp = helper(idx-1, coins, newLeft);
            if (temp != -1) res = Math.min(res, temp+reduce);
            reduce--;
            newLeft = newLeft + curr;
        }

        return (res == Integer.MAX_VALUE) ? -1 : res;
    }


    /**
     * https://leetcode.com/problems/coin-change/solution/
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return dp(coins, amount, new int[amount]);
    }

    private int dp(int[] coins, int left, int[] amounts) {
        if (left < 0) return -1;
        if (left == 0) return 0;
        if (amounts[left - 1] != 0) return amounts[left - 1];
        int res = Integer.MAX_VALUE;
        for (int i=0; i<coins.length; i++) {
            int next = dp(coins, left-coins[i], amounts);
            if (next >= 0 && next < res)
                res = next +1;
        }
        amounts[left - 1] = (res == Integer.MAX_VALUE) ? -1 : res;
        return amounts[left - 1];
    }


    /**
     * https://leetcode.com/problems/coin-change/solution/
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i=1; i<=amount; i++) {
            for (int j=0; j<coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int coinChange4(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i=1; i<=amount; i++) {
            int t = Integer.MAX_VALUE;
            for (int c: coins) {
                if (i >= c && dp[i - c] != -1) {
                    t = Math.min(t, dp[i - c] + 1);
                }
            }
            dp[i] = t == Integer.MAX_VALUE ? -1 : t;
        }
        return dp[amount];
    }


    public int coinChange5(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int c: coins) {
            for (int i=c; i<=amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }


    public int coinChange6(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] res = new int[]{amount + 1};
        coinChange(coins, amount, coins.length - 1, res, 0);
        return (res[0] == amount + 1) ? -1 : res[0];
    }

    public void coinChange(int[] coins, int amount, int idx, int[] res, int num) {
        if (amount == 0) {
            res[0] = Math.min(res[0], num);
            return;
        }
        if (idx < 0) return;
        int k = amount / coins[idx];
        for (int j=k; j>=0 && num + j < res[0]; j--) {
            coinChange(coins, amount - j * coins[idx], idx-1, res, num + j);
        }
    }

}
