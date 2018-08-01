/**
 * Given a non-negative integer c, your task is to decide whether there're two
 * integers a and b such that a2 + b2 = c.
 * 
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * 
 * Example 2:
 * Input: 3
 * Output: False
 */

public class SumOfSquareNumbers633 {
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        int n = (int) Math.sqrt(c);
        for (int i=0; i<=n; i++) {
            int re = c - i * i;
            int sq = (int) Math.sqrt(re);
            if (sq * sq == re) return true;
        }
        return false;
    }
}
