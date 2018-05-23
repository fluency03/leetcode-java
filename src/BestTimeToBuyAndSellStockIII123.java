/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most
 * two transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 *
 */

public class BestTimeToBuyAndSellStockIII123 {
    public int maxProfit(int[] prices) {
        int buy0 = Integer.MIN_VALUE;
        int sell0 = 0;
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        for (int p: prices) {
            sell1 = Math.max(sell1, buy1 + p);
            buy1 = Math.max(buy1, sell0 - p);
            sell0 = Math.max(sell0, buy0 + p);
            buy0 = Math.max(buy0, -p);
        }
        return sell1;
    }

}
