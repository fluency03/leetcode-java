/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return
 * the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 *
 * Explanation: The encoded message can be decoded to the string:
 * "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 *
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */


public class DecodeWaysII639 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        long[] dp = new long[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);
        for (int i=2; i<=s.length(); i++) {
            char c = s.charAt(i-1);
            char pc = s.charAt(i-2);
            if (c == '*') {
                dp[i] += dp[i-1]*9;
                dp[i] += pc == '*' ? dp[i-2]*15 : (pc == '1' ? dp[i-2]*9 : (pc == '2' ? dp[i-2]*6 : 0));
            } else {
                dp[i] += c == '0' ? 0 : dp[i-1];
                if (pc == '*') {
                    dp[i] += dp[i-2] * (c <= '6' ? 2 : 1);
                } else {
                    int two = (pc - '0') * 10 + (c - '0');
                    dp[i] += (two >= 10 && two <= 26) ? dp[i-2] : 0;
                }
            }
            dp[i] %= 1000000007;
        }

        return (int) dp[s.length()];
    }


    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) return 0;
        long n0 = 1;
        long n1 = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);
        long n2 = 0;
        for (int i=2; i<=s.length(); i++) {
            n2 = 0;
            char c = s.charAt(i-1);
            char pc = s.charAt(i-2);
            if (c == '*') {
                n2 += n1*9;
                n2 += pc == '*' ? n0*15 : (pc == '1' ? n0*9 : (pc == '2' ? n0*6 : 0));
            } else {
                n2 += c == '0' ? 0 : n1;
                if (pc == '*') {
                    n2 += n0 * (c <= '6' ? 2 : 1);
                } else {
                    int two = (pc - '0') * 10 + (c - '0');
                    n2 += (two >= 10 && two <= 26) ? n0 : 0;
                }
            }
            n2 %= 1000000007;
            n0 = n1;
            n1 = n2;
        }

        return (int) n1;
    }

}
