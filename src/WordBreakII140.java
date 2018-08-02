/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
 * 
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */

public class WordBreakII140 {
    // Memory Limit Exceeded
    // public List<String> wordBreak(String s, List<String> wordDict) {
    //     int N = s.length();
    //     LinkedList<String>[] dp = new LinkedList[N+1];
    //     for (int i=0; i<=N; i++) dp[i] = new LinkedList<>();
    //     Set<String> wordSet = new HashSet<>(wordDict);

    //     dp[0].add("");
    //     for (int i=1; i<=N; i++) {
    //         for (int j=0; j<i; j++) {
    //             String w = s.substring(j, i);
    //             if (dp[j].size() > 0 && wordSet.contains(w)) {
    //                 for (String p: dp[j]) {
    //                     dp[i].add(p.length() == 0 ? w : (p + " " + w));
    //                 }
    //             }
    //         }
    //     }

    //     return dp[N];
    // }


    // DP
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        if (!wordIsBreakable(s, wordSet)) return new ArrayList<>();
        return wordBreak(s, wordSet);
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

    public boolean wordIsBreakable(String s, Set<String> wordDictSet) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    // Backtracing
    public List<String> wordBreak2(String s, List<String> wordDict) {
        if (!wordIsBreakable(s, new HashSet<>(wordDict))) return new ArrayList<>();
        int N = s.length();
        Set<String>[] dp = new Set[N];
        for (int i=0; i<N; i++) dp[i] = new HashSet<String>();

        for (int i=0; i<N; i++) {
            for (String word: wordDict) {
                if (s.startsWith(word, i)) {
                    dp[i].add(word);
                }
            }
        }

        List<String> res = new ArrayList<>();
        helper(N, dp, res, new StringBuilder(), 0);
        return res;
    }

    private void helper(int N, Set<String>[] dp, List<String> res, StringBuilder sb, int i) {
        if (i == N) {
            res.add(sb.substring(0, sb.length()-1).toString());
            return;
        }
        for (String w: dp[i]) {
            sb.append(w).append(" ");
            helper(N, dp, res, sb, i+w.length());
            sb.delete(sb.length()-w.length()-1, sb.length());
        }
    }


    /**
     * https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
     */
    public List<String> wordBreak3(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s)) 
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }
        map.put(s, res);
        return res;
    }

}
