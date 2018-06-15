/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at
 * the same time (ie, you must sell the stock before you buy again).
 *
 */


public class BestTimeToBuyAndSellStockII122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];

        buy[0] = -prices[0];

        for (int i=1; i<prices.length; i++) {
            buy[i] = Math.max(buy[i-1], sell[i-1]-prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+prices[i]);
        }

        return sell[prices.length-1];
    }


    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int buy = -prices[0];
        int preBuy = -prices[0];
        int sell = 0;
        int preSell = 0;

        for (int i=1; i<prices.length; i++) {
            preBuy = buy;
            preSell = sell;
            buy = Math.max(preBuy, preSell - prices[i]);
            sell = Math.max(preSell, preBuy + prices[i]);
        }

        return sell;
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
     */
    public int maxProfit3(int[] prices) {
        int profit=0;

        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                profit+= prices[i]-prices[i-1];
            }
        }
        return profit;
    }


}
