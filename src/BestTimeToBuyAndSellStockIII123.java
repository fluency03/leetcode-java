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
        if (prices == null || prices.length <= 1) return 0;

        int buy = Integer.MIN_VALUE;
        int preBuy = Integer.MIN_VALUE;
        int sell = 0;
        int preSell = 0;

        for (int p: prices) {
            sell = Math.max(sell, buy + p);
            buy = Math.max(buy, preSell - p);
            preSell = Math.max(preSell, preBuy + p);
            preBuy = Math.max(preBuy, - p);
        }
        return sell;
    }


    public int maxProfit2(int[] prices) {
        return maxProfit(2, prices);
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k <= 0) return 0;
        
        if (k >= prices.length >>> 1) {
            int profit = 0;
            for(int i=1; i<prices.length; i++){
                if(prices[i] > prices[i-1]){
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit;
        }
        
        int[] buy = new int[k+1];
        int[] sell = new int[k+1];
        for (int j=0; j<=k; j++) {
            buy[j] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            for (int j=1; j<=k; j++) {
                buy[j] = Math.max(buy[j], sell[j-1] - price);
                sell[j] = Math.max(sell[j], buy[j] + price);
            }
        }
        return sell[k];
    }



}
