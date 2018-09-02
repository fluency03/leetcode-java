/**
 * Write a program to find the nth super ugly number.
 * 
 * Super ugly numbers are positive numbers whose all prime factors are in the
 * given prime list primes of size k.
 * 
 * Example:
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32 
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
 *              super ugly numbers given primes = [2,7,13,19] of size 4.
 * 
 * Note:
 * 1 is a super ugly number for any given primes.
 * The given numbers in primes are in ascending order.
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */

public class SuperUglyNumber313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n <= 0) return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pointers = new int[primes.length];
        for (int i=1; i<n; i++) {
            int t = Integer.MAX_VALUE;
            for (int j=0; j<primes.length; j++) {
                if (dp[pointers[j]] * primes[j] < t) {
                    t = dp[pointers[j]] * primes[j];
                }
            }
            for (int j=0; j<primes.length; j++) {
                if (dp[pointers[j]] * primes[j] == t) pointers[j]++;
            }
            dp[i] = t;
        }
        return dp[n-1];
    }


    public int nthSuperUglyNumber2(int n, int[] primes) {
        if (n <= 0) return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pointers = new int[primes.length];
        for (int i=1; i<n; i++) {
            int t = Integer.MAX_VALUE;
            int idx = -1;
            for (int j=0; j<primes.length; j++) {
                int now = dp[pointers[j]] * primes[j];
                if (now < t) {
                    t = dp[pointers[j]] * primes[j];
                    idx = j;
                } else if (now == t) {
                    pointers[j]++;
                }
            }
            pointers[idx]++;
            dp[i] = t;
        }
        return dp[n-1];
    }

}
