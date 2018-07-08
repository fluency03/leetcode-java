/**
 * Given a non-negative integer n, count all numbers with unique digits, x,
 * where 0 ≤ x < 10n.
 * 
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the
 * range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 */


public class CountNumbersWithUniqueDigits357 {
    public int countNumbersWithUniqueDigits(int n) {
        n = Math.min(n, 10);
        int res = 1;
        for (int i=1; i<=n; i++) {
            res += count(i);
        }
        return res;
    }
    private int count(int i) {
        int res = 9;
        int k = 1;
        while (k < i) {
            res *= (10-k);
            k++;
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/count-numbers-with-unique-digits/discuss/83037/Very-simple-15-line-backtrack-solution
     */
    public int countNumbersWithUniqueDigits2(int n) {
        return count(n, new boolean[10], 0);
    }
    
    private int count(int n, boolean[] used, int l) {
        if (l == n) return 1;

        int res = 0;
        for (int i = (l == 0 ? 1 : 0); i<=9; i++) {
            if (!used[i]) {
                used[i] = true;
                res += count(n, used, l+1);
                used[i] = false;
            }
        }
        return res;
    }
  
    public int countNumbersWithUniqueDigits3(int n) {
        int[] res = new int[1];
        count(n, new boolean[10], 0, res);
        return res[0];
    }
    
    private void count(int n, boolean[] used, int l, int[] res) {
        res[0]++;
        if (l == n) return;

        for (int i = (l == 0 ? 1 : 0); i<=9; i++) {
            if (!used[i]) {
                used[i] = true;
                count(n, used, l+1, res);
                used[i] = false;
            }
        }
    }

}
