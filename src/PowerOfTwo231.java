/**
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * Example 1:
 * Input: 1
 * Output: true 
 * Explanation: 2^0 = 1
 * 
 * Example 2:
 * Input: 16
 * Output: true
 * Explanation: 2^4 = 16
 * 
 * Example 3:
 * Input: 218
 * Output: false
 */

public class PowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if (n % 2 != 0) return false;
            n /= 2;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/power-of-two/discuss/63974/Using-nand(n-1)-trick
     */
    public boolean isPowerOfTwo2(int n) {
        if (n<=0) return false;
        return (n & (n-1)) == 0;
    }

}
