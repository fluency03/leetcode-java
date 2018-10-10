/**
 * Given a non-negative integer N, find the largest number that is less than or
 * equal to N with monotone increasing digits.
 * 
 * (Recall that an integer has monotone increasing digits if and only if each
 * pair of adjacent digits x and y satisfy x <= y.)
 * 
 * Example 1:
 * Input: N = 10
 * Output: 9
 * 
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * 
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 */

public class MonotoneIncreasingDigits738 {
    public int monotoneIncreasingDigits(int N) {
        String ns = Integer.toString(N);
        int i = 0;
        char[] chars = ns.toCharArray();
        while (i + 1 < chars.length && chars[i] <= chars[i+1]) {
            i++;
        }
        while (i > 0 && chars[i-1] == chars[i]) {
            i--;
        }
        if (i == chars.length - 1) return N;
        chars[i] = (char) ((int) chars[i] - 1);
        for (int j=i+1; j<chars.length; j++) {
            chars[j] = '9';
        }
        return Integer.valueOf(new String(chars));
    }

}

