/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of
 * ways to decode it.
 *
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 */


import java.util.Map;
import java.util.HashMap;


public class DecodeWays91 {
    public int numDecodings(String s) {
        int length = s.length();
        length = s.length();

        if (length == 0) {
            return 0;
        }

        if (s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;

        boolean isZero = false;
        for (int i = 2; i <= length; i++) {
            int n = Integer.valueOf(s.substring(i-2, i));

            if (s.charAt(i - 1) == '0' && (n > 26 || n == 0)) {
                return 0;
            } else if (s.charAt(i - 1) == '0') {
                isZero = true;
                dp[i] = dp[i - 2];
            } else if (isZero) {
                isZero = false;
                dp[i] = dp[i - 1];
            } else {
                isZero = false;
                dp[i] = dp[i - 1] + ((n > 0 && n <= 26) ? dp[i - 2] : 0);
            }
        }

        return dp[length];
    }


    /**
     * https://discuss.leetcode.com/topic/2562/dp-solution-java-for-reference
     */
    public int numDecodings2(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }


    /**
     * https://discuss.leetcode.com/topic/35840/java-clean-dp-solution-with-explanation
     */
    public int numDecodings3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] ways = new int[s.length() + 1];
        ways[0] = 1;
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int one = s.charAt(i - 1) - '0';
            int two = (s.charAt(i - 2) - '0') * 10 + one;
            if (one != 0) {
                ways[i] += ways[i - 1];
            }
            if (two >= 10 && two <= 26) {
                ways[i] += ways[i - 2];
            }
        }
        return ways[s.length()];
    }


    public int numDecodings4(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=s.length(); i++) {
            int now = s.charAt(i-1) - '0';
            int prenow = (s.charAt(i-2) - '0')*10 + now;
            if (now == 0 && prenow != 10 && prenow != 20) return 0;
            int a = now == 0 ? 0 : dp[i-1];
            int b = (prenow >= 10 && prenow <= 26) ? dp[i-2] : 0;
            dp[i] = a + b;
        }

        return dp[s.length()];
    }


    /**
     * https://leetcode.com/problems/decode-ways/discuss/30416/Simple-java-solution-with-O(1)-space
     */
    public int numDecodings5(String s) {
        int n1 =1, n2=1, n3=0;
        if(s.length() == 0 || s.charAt(0) == '0') return 0;
        for (int i=2; i<=s.length(); i++) {
            n3=0;
            if(s.charAt(i-1)!='0') n3=n2;
            int num = Integer.parseInt(s.substring(i-2,i));
            if(num>=10 && num<=26) n3+=n1;
            n1=n2;
            n2=n3;
        }
        return n2;
    }

}
