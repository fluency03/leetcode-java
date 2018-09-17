/**
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 *
 * Example:
 *
 * Input: "babad"
 *
 * Output: "bab"
 *
 * Note: "aba" is also a valid answer.
 * Example:
 *
 * Input: "cbbd"
 *
 * Output: "bb"
 */


import java.util.Arrays;

public class LongestPalindromicSubstring5 {
    public String longestPalindrome(String s) {
        char[] input = s.toCharArray();
        int length = input.length;
        if (length == 1) {
            return s;
        }

        String output = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                int left = i;
                int right = i + j;
                while (left >= 0 && right < length && input[left] == input[right]) {
                    left--;
                    right++;
                }
                left++;
                right--;
                if (output.length() < right - left + 1) {
                    output = String.valueOf(Arrays.copyOfRange(input, left, right+1));
                }
            }
        }

        return output;
    }


    /**
     * https://discuss.leetcode.com/topic/23498/very-simple-clean-java-solution
     */
    private int lo, maxLen;

    public String longestPalindrome2(String s) {
      	int len = s.length();
      	if (len < 2) return s;
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
      	while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
      	}
      	if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
      	}
    }


    /**
     * https://leetcode.com/problems/longest-palindromic-substring/discuss/2921/Share-my-Java-solution-using-dynamic-programming
     */
    public String longestPalindrome3(String s) {
        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }


    public String longestPalindrome4(String s) {
        if (s == null || s.length() <= 1) return s;
        int N = s.length();
        int[][] dp = new int[N][N];
        
        char[] chars = s.toCharArray();
        int end = -1;
        int len = Integer.MIN_VALUE;
        for (int i=N-1; i>=0; i--) {
            for (int j=i; j<N; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i == j - 1) {
                    dp[i][j] = chars[i] == chars[j] ? 2 : 1;
                } else {
                    if (chars[i] == chars[j] && dp[i+1][j-1] == (j-i-1)) {
                        dp[i][j] = j-i+1;
                    }
                    
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                }
                if (dp[i][j] > len) {
                    len = dp[i][j];
                    end = j;
                }
            }
        }
        return s.substring(end+1-len, end+1);
    }


    /**
     * https://leetcode.com/problems/longest-palindromic-substring/solution/
     */
    public String longestPalindrome5(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
