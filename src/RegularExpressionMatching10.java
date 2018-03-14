/**
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 *
 */


public class RegularExpressionMatching10 {

    /**
     * https://leetcode.com/problems/regular-expression-matching/solution/
     */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }


    public boolean isMatch2(String text, String pattern) {
        return isMatch(0, 0, text, pattern);
    }

    public boolean isMatch(int i, int j, String text, String pattern) {
        if (j == pattern.length()) return i == text.length();
        boolean first_match = (i != text.length() &&
                               (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

        if (pattern.length() >= j+2 && pattern.charAt(j+1) == '*'){
            return (isMatch(i, j+2, text, pattern) ||
                    (first_match && isMatch(i+1, j, text, pattern)));
        } else {
            return first_match && isMatch(i+1, j+1, text, pattern);
        }
    }


    public boolean isMatch3(String text, String pattern) {
        int textLen = text.length();
        int pattLen = pattern.length();
        boolean[][] dp = new boolean[textLen+1][pattLen+1];
        dp[0][0] = true;

        for(int j=1; j<=pattLen; j++) {
            if (pattern.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int i=1; i<=textLen; i++) {
            for(int j=1; j<=pattLen; j++) {
                if (j >= 2 && pattern.charAt(j-1) == '*') {
                    boolean preMatch = (pattern.charAt(j-2) == text.charAt(i-1) || pattern.charAt(j-2) == '.');
                    dp[i][j] = dp[i][j-2] || (preMatch && dp[i-1][j]);
                } else {
                    boolean currMatch = (pattern.charAt(j-1) == text.charAt(i-1) || pattern.charAt(j-1) == '.');
                    dp[i][j] = currMatch && dp[i-1][j-1];
                }
            }
        }

        return dp[text.length()][pattern.length()];
    }


    /**
     * https://leetcode.com/problems/regular-expression-matching/solution/
     */
    enum Result {
        TRUE, FALSE
    }
    Result[][] memo;

    public boolean isMatch4(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                       first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }


    public boolean isMatch5(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int j=1; j<=p.length(); j++) dp[0][j] = p.charAt(j-1) == '*' ? dp[0][j-2] : false;
        for (int i=1; i<=s.length(); i++) dp[i][0] = false;

        for (int i=1; i<=s.length(); i++) {
            for (int j=1; j<=p.length(); j++) {
                char c = p.charAt(j-1);
                switch (c) {
                    case '.':
                        dp[i][j] = dp[i-1][j-1];
                        break;
                    case '*':
                        dp[i][j] = dp[i][j-2] || ((p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) && dp[i-1][j]);
                        break;
                    default:
                        dp[i][j] = s.charAt(i-1) == p.charAt(j-1) && dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
