/**
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 * 
 * Example:
 * 
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */


public class PalindromePartitioning131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        helper(s.toCharArray(), 0, new ArrayList<>(), res);
        return res;
    }

    private void helper(char[] chars, int start, List<String> path, List<List<String>> res) {
        if (start == chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int j=start; j<chars.length; j++) {
            if (isPalindrome(chars, start, j)) {
                path.add(new String(Arrays.copyOfRange(chars, start, j+1)));
                helper(chars, j+1, path, res);
                path.remove(path.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(char[] chars, int i, int j) {
        if (i > j) return true;
        if (i == j) return true;
        for (int k=0; k<=(j-i)/2; k++) {
            if (chars[i+k] != chars[j-k]) return false;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/palindrome-partitioning/discuss/41982/Java-DP-+-DFS-solution
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for(int i = pos; i < s.length(); i++) {
            if(dp[pos][i]) {
                path.add(s.substring(pos,i+1));
                helper(res, path, dp, s, i+1);
                path.remove(path.size()-1);
            }
        }
    }


}

