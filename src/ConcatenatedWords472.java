/**
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 * 
 * A concatenated word is defined as a string that is comprised entirely of at
 * least two shorter words in the given array.
 * 
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * 
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */

public class ConcatenatedWords472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = constructTrie(words);
        List<String> res = new ArrayList<>();
        for (String word: words) {
            if (count(trie, word.toCharArray(), 0, trie, 1) >= 2) {
                res.add(word);
            }
        }
        return res;
    }

    private int count(Trie trie, char[] chars, int i, Trie root, int cnt) {
        if (i == chars.length) return -1;
        int idx = chars[i] - 'a';
        if (trie.children[idx] == null) return -1;
        if (trie.children[idx].isWord) {
            if (i == chars.length - 1) return cnt;
            int tmp = count(root, chars, i+1, root, cnt+1);
            if (tmp >= 2) {
                return tmp;
            }
        }
        return count(trie.children[idx], chars, i+1, root, cnt);
    }

    private Trie constructTrie(String[] words) {
        Trie trie = new Trie();
        for (String word: words) trie.add(word);
        return trie;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isWord;

        void add(String word) {
            add(word.toCharArray(), 0);
        }

        void add(char[] chars, int i) {
            if (i == chars.length) {
                isWord = true;
                return;
            }
            int idx = chars[i] - 'a';
            if (children[idx] == null) {
                children[idx] = new Trie();
            }
            children[idx].add(chars, i+1);
        }
    }

}
