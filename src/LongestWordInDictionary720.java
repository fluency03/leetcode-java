/**
 * Given a list of strings words representing an English Dictionary, find the
 * longest word in words that can be built one character at a time by other
 * words in words. If there is more than one possible answer, return the
 * longest word with the smallest lexicographical order.
 * 
 * If there is no answer, return the empty string.
 * 
 * Example 1:
 * Input: 
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation: 
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * 
 * Example 2:
 * Input: 
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation: 
 * Both "apply" and "apple" can be built from other words in the dictionary.
 * However, "apple" is lexicographically smaller than "apply".
 * 
 * Note:
 * All the strings in the input will only contain lowercase letters.
 * The length of words will be in the range [1, 1000].
 * The length of words[i] will be in the range [1, 30].
 */

public class LongestWordInDictionary720 {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        int N = words.length;
        String res = "";
        Stack<String> stack = new Stack<>();
        for (int i=0; i<N; i++) {
            while (!stack.isEmpty() && stack.peek().length() >= words[i].length()) {
                stack.pop();
            }
            String curr = stack.isEmpty() ? "" : stack.peek();
            if (curr.length() + 1 == words[i].length() && words[i].startsWith(curr)) {
                stack.push(words[i]);
            }
            if (!stack.isEmpty() && stack.peek().length() > res.length()) {
                res = stack.peek();
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-word-in-dictionary/discuss/109114/JavaC%2B%2B-Clean-Code
     */
    public String longestWord2(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }


    public String longestWord3(String[] words) {
        Trie trie = constructTrie(words);
        trie.isWord = true;
        String[] res = new String[]{""};
        dfs(trie, new StringBuilder(), res);
        return res[0];
    }
  
    private void dfs(Trie trie, StringBuilder sb, String[] res) {
        if (trie == null || !trie.isWord) return;
        if (sb.length() > res[0].length()) {
            res[0] = sb.toString();
        }
        for (char ch='a'; ch<='z'; ch++) {
            Trie child = trie.children[ch-'a'];
            if (child == null) continue;
            sb.append(ch);
            dfs(child, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    private Trie constructTrie(String[] words) {
        Trie trie = new Trie();
        for (String word: words) {
            trie.add(word);
        }
        return trie;
    }
    
    
    class Trie {
        Trie[] children = new Trie[26];
        boolean isWord;
        
        void add(String word) {
            add(word.toCharArray(), 0);
        }
        
        void add(char[] word, int i) {
            if (i == word.length) {
                this.isWord = true;
                return;
            }
            int idx = word[i] - 'a';
            if (this.children[idx] == null) {
                this.children[idx] = new Trie();
            }
            this.children[idx].add(word, i+1);
        }
    }

}
