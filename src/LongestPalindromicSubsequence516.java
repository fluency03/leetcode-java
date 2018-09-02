/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */

public class LongestPalindromicSubsequence516 {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[len][len];
        for (int i=len-1; i>=0; i--) {
            dp[i][i] = 1;
            for (int j=i+1; j<len; j++) {
                int notEqual = Math.max(dp[i+1][j], dp[i][j-1]);
                int equal = 1;
                if (i + 1 == j) {
                    if (chars[i] == chars[j]) {
                        equal = 2;
                    }
                } else {
                    equal = dp[i+1][j-1] + (chars[i] == chars[j] ? 2 : 0);
                }
                dp[i][j] = Math.max(equal, notEqual);
            }
        }
        return dp[0][len-1];
    }


    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        return helper(s.toCharArray(), 0, len-1, new int[len][len]);
    }
    
    private int helper(char[] chars, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (chars[i] == chars[j]) {
            dp[i][j] = helper(chars, i+1, j-1, dp) + 2;
        } else {
            dp[i][j] = Math.max(helper(chars, i+1, j, dp), helper(chars, i, j-1, dp));
        }
        return dp[i][j];
    }

}
