/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words. You may assume the dictionary
 * does not contain duplicate words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 *
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a
 * set of strings). Please reload the code definition to get the latest changes.
 */


public class WordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int L = s.length();
        if (L == 0) return true;
        boolean[] dp = new boolean[L+1];
        dp[0] = true;
        int k = 0;
        for (int i=0; i<=L; i++) {
            for (int j=0; j<=i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[L];
    }


    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int j=1; j<=s.length(); j++) {
            for (String word: wordDict) {
                if (j >= word.length() && s.startsWith(word, j - word.length()) && dp[j - word.length()]) dp[j] = true;
            }
        }
        return dp[s.length()];
    }

}
