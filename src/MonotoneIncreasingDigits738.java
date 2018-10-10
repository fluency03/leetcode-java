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


    /**
     * https://leetcode.com/problems/monotone-increasing-digits/solution/
     */
    public int monotoneIncreasingDigits2(int N) {
        char[] S = String.valueOf(N).toCharArray();
        int i = 1;
        while (i < S.length && S[i-1] <= S[i]) i++;
        while (0 < i && i < S.length && S[i-1] > S[i]) S[--i]--;
        for (int j = i+1; j < S.length; ++j) S[j] = '9';

        return Integer.parseInt(String.valueOf(S));
    }


    /**
     * https://leetcode.com/problems/monotone-increasing-digits/discuss/144404/Simple-Java-from-back-to-front-no-extra-space-and-no-conversion-to-char
     */
    public int monotoneIncreasingDigits3(int N) {
        int res = 0;
        int pre = Integer.MAX_VALUE;
        int offset = 1;
        while(N != 0) {
            int digit = N % 10;
            if(digit > pre) {
                res = digit * offset - 1;
                pre = digit - 1;
            }else {
                res = res + digit * offset;
                pre = digit;
            }
            offset *= 10;
            N = N / 10;
        }
        return res;
    }

}

