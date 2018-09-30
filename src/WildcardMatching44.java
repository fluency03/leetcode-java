/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 *
 */

public class WildcardMatching44 {
    // time limit exceeded
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return isMatch(s, 0, p, 0);
    }

    private boolean isMatch(String s, int i, String p, int j) {
        if (i >= s.length()) return restAreStars(p, j);
        if (j >= p.length()) return false;

        switch (p.charAt(j)) {
            case '?':
                return isMatch(s, i+1, p, j+1);
            case '*':
                while (j+1 < p.length() && p.charAt(j+1) == '*') j++;
                return isMatch(s, i, p, j+1) || isMatch(s, i+1, p, j+1) || isMatch(s, i+1, p, j);
            default:
                return s.charAt(i) == p.charAt(j) && isMatch(s, i+1, p, j+1);
        }
    }

    private boolean restAreStars(String p, int j) {
        while (j < p.length()) {
            if (p.charAt(j) != '*') return false;
            j++;
        }
        return true;
    }


    // DP, 2D space
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;
        for (int j=1; j<=s.length(); j++) dp[0][j] = false;
        for (int i=1; i<=p.length(); i++) dp[i][0] = p.charAt(i-1) == '*' ? dp[i-1][0] : false;

        for (int i=1; i<=p.length(); i++) {
            char c = p.charAt(i-1);
            for (int j=1; j<=s.length(); j++) {
                switch (c) {
                    case '?':
                        dp[i][j] = dp[i-1][j-1];
                        break;
                    case '*':
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                        break;
                    default:
                        dp[i][j] = c == s.charAt(j-1) && dp[i-1][j-1];
                }
            }
        }

        return dp[p.length()][s.length()];
    }


    // DP, 1D space
    public boolean isMatch3(String s, String p) {
        if (s == null || p == null) return false;

        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int j=1; j<=s.length(); j++) dp[j] = false;

        for (int i=1; i<=p.length(); i++) {
            boolean old = dp[0];
            boolean pre = dp[0];
            dp[0] = p.charAt(i-1) == '*' ? dp[0] : false;
            char c = p.charAt(i-1);
            for (int j=1; j<=s.length(); j++) {
                old = dp[j];
                switch (c) {
                    case '?':
                        dp[j] = pre;
                        break;
                    case '*':
                        dp[j] = old || pre || dp[j-1];
                        break;
                    default:
                        dp[j] = c == s.charAt(j-1) && pre;
                }
                pre = old;
            }
        }

        return dp[s.length()];
    }


    /**
     * https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
     */
    boolean isMatch4(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') p++;

        return p == pattern.length();
    }


    public boolean isMatch5(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        Map<Integer, Boolean> memo = new HashMap<>();
        return isMatch(s.toCharArray(), 0, p.toCharArray(), 0, memo);
    }

    public boolean isMatch(char[] s, int i, char[] p, int j, Map<Integer, Boolean> memo) {
        int idx = i * p.length + j;
        if (memo.containsKey(idx)) return memo.get(idx);
        if (j == p.length) return i == s.length;

        boolean res = false;
        char pChar = p[j];
        if (i == s.length) {
            res = pChar == '*' && isMatch(s, i, p, j+1, memo);
        } else {
            char sChar = s[i];
            if (pChar == '?') {
                res = isMatch(s, i+1, p, j+1, memo);
            } else if (pChar == '*') {
                res = isMatch(s, i+1, p, j+1, memo) ||
                        isMatch(s, i, p, j+1, memo) ||
                        isMatch(s, i+1, p, j, memo);
            } else {
                res = pChar == sChar && isMatch(s, i+1, p, j+1, memo);
            }
        }
        memo.put(idx, res);
        return res;
    }

}
