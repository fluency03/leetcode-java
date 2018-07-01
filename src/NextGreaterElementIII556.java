/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1.
 * 
 * Example 1:
 * Input: 12
 * Output: 21
 *
 * Example 2:
 * Input: 21
 * Output: -1 
 */

public class NextGreaterElementIII556 {
    public int nextGreaterElement(int n) {
        int len = String.valueOf(n).length();
        int[] digits = new int[len];
        int p = n;
        int i = len-1;
        int pre = p % 10;
        int mark = -1;
        while (p != 0) {
            int now = p % 10;
            digits[i] = now;
            if (mark == -1 && now < pre) {
                int j = i+1;
                while (j < len && digits[j] > now) j++;
                swap(digits, i, j-1);
                mark = i;
            }
            pre = now;
            p = p / 10;
            i--;
        }
        if (mark == -1) return -1;
        Arrays.sort(digits, mark+1, len);
        
        long res = 0;
        long tens = 1;
        for (int j=len-1; j>=0; j--) {
            res += digits[j] * tens;
            tens = tens * 10;
        }

        return res > Integer.MAX_VALUE ? -1 : (int) res;
    }

    private void swap(int[] digits, int i, int j) {
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

}
