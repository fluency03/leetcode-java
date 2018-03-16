/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one letter.
 *
 * For example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 */


public class WordDictionary {

    Trie t = new Trie();

    /** Initialize your data structure here. */
    public WordDictionary() {
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        add(t, word.toCharArray(), 0);
    }

    private static void add(Trie tr, char[] chars, int i) {
        while (i < chars.length) tr = tr.add(chars[i++]);
        tr.isWord = true;
    }

    // private static void add(Trie tr, char[] chars, int i) {
    //     if (i >= chars.length) {
    //         tr.isWord = true;
    //         return;
    //     }
    //     add(tr.add(chars[i]), chars, i+1);
    // }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(t, word.toCharArray(), 0);
    }

    private static boolean search(Trie tr, char[] chars, int i) {
        while (i < chars.length) {
            char c = chars[i];
            if (c == '.') {
                for (char nc: tr.map.keySet()) {
                    if (tr.has(nc) && search(tr.get(nc), chars, i+1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!tr.has(c)) return false;
                tr = tr.get(c);
                i++;
            }
        }
        return tr.isWord;
    }

    // private static boolean search(Trie tr, char[] chars, int i) {
    //     if (i >= chars.length) return tr.isWord;
    //     char c = chars[i];
    //     if (c == '.') {
    //         for (char nc: tr.map.keySet()) {
    //             if (tr.has(nc) && search(tr.get(nc), chars, i+1)) {
    //                 return true;
    //             }
    //         }
    //         return false;
    //     } else {
    //         return tr.has(c) && search(tr.get(c), chars, i+1);
    //     }
    // }

}


class Trie {
    // also Trie[] map = new Trie[26];
    Map<Character, Trie> map = new HashMap<>();
    boolean isWord = false;

    public Trie() {
    }

    public Trie get(char c) {
        return this.map.get(c);
    }

    public Trie add(char c) {
        return this.map.computeIfAbsent(c, cc -> new Trie());
    }

    public boolean has(char c) {
        return c == '.' || this.map.containsKey(c);
    }
}


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
