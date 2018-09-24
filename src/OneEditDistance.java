/**
 * 
 */

public class OneEditDistance {
    public boolean oneEditDistance(String str, String[] wordDict) {
        Trie trie = constructTrie(wordDict, str.length());
        return helper(str.toCharArray(), 0, trie, 0);
    }

    private boolean helper(char[] str, int i, Trie trie, int edit) {
        if (edit > 1) return false;
        if (str.length == i) {
            if (edit == 1) {
                return trie.isWord;
            } else {
                for (char ch='a'; ch<='z'; ch++) {
                    if (trie.children[ch-'a'] != null && trie.children[ch-'a'].isWord) return true;
                }
                return false;
            }
        }

        char curr = str[i];
        for (char ch='a'; ch<='z'; ch++) {
            int idx = ch-'a';
            if (trie.children[idx] == null) continue;
            if (helper(str, i+1, trie.children[idx], edit + ch == curr ? 0 : 1) ||
                helper(str, i, trie.children[idx], edit + 1) ||
                helper(str, i+1, trie, edit + 1)) return true;
        }
        return false;
    }

    private Trie constructTrie(String[] wordDict, int len) {
        Trie trie = new Trie();
        for (String word: wordDict) {
            if (Math.abs(word.length() - len) <= 1) {
                trie.add(word);
            }
        }
        return trie;
    }

    // assume the string only contains a ~ z
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

    public static void main(String[] args) {
        OneEditDistance oed = new OneEditDistance();
        System.out.println(oed.oneEditDistance("abc", new String[]{"abc", "abd"})); // true
        System.out.println(oed.oneEditDistance("abc", new String[]{"abc"})); // false
        System.out.println(oed.oneEditDistance("", new String[]{"a"})); // true
        System.out.println(oed.oneEditDistance("", new String[]{""})); // false
        System.out.println(oed.oneEditDistance("a", new String[]{""})); // false
        System.out.println(oed.oneEditDistance("a", new String[]{"b", "c"})); // true
        System.out.println(oed.oneEditDistance("a", new String[]{"a", "c"})); // true
        System.out.println(oed.oneEditDistance("a", new String[]{"a"})); // false
        System.out.println(oed.oneEditDistance("abcdef", new String[]{"abcdef", "abdef"})); // true
        System.out.println(oed.oneEditDistance("abcdef", new String[]{"abdef"})); // true
        System.out.println(oed.oneEditDistance("abcdef", new String[]{"abef"})); // false
        System.out.println(oed.oneEditDistance("abcef", new String[]{"abcdef"})); // true
    }

}
