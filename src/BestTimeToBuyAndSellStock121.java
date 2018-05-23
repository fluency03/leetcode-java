/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 *
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 *
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 *
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 *
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 *
 * In this case, no transaction is done, i.e. max profit = 0.
 *
 */


public class BestTimeToBuyAndSellStock121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int minSoFar = Integer.MAX_VALUE;
        int res = 0;

        for (int p: prices) {
            if (p > minSoFar) {
                res = Math.max(res, p - minSoFar);
            } else {
                minSoFar = p;
            }
        }

        return res;
    }

    public int maxProfit2(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int p: prices) {
            int oldBuy = buy;
            buy = Math.max(buy, - p);
            sell = Math.max(sell, oldBuy + p);
        }
        return sell;
    }

}
