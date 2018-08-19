/**
 * Determine whether an integer is a palindrome. An integer is a palindrome
 * when it reads the same backward as forward.
 * 
 * Example 1:
 * Input: 121
 * Output: true
 * 
 * Example 2:
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left,
 * it becomes 121-. Therefore it is not a palindrome.
 * 
 * Example 3:
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * 
 * Follow up:
 * Coud you solve it without converting the integer to a string?
 */

public class PalindromeNumber9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        return x == reverse(x);
    }
    
    private int reverse(int x) {
        int res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        return res;
    }


    public int numberOfDigits(int x) {
        int res = 0;
        while (x != 0) {
            res++;
            x /= 10;
        }
        return res;
    }

    private int getDigit(int x, int pos) {
        return (x / (int) Math.pow(10, pos-1)) % 10;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        int N = numberOfDigits(x);
        int hi = N;
        int lo = 1;
        while (lo < hi) {
            if (getDigit(x, lo++) != getDigit(x, hi--)) return false;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/palindrome-number/discuss/5127/9-line-accepted-Java-code-without-the-need-of-handling-overflow
     */
    public boolean isPalindrome3(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
          rev = rev*10 + x%10;
          x = x/10;
        }
        return (x==rev || x==rev/10);
    }

}
