/**
 * In English, we have a concept called root, which can be followed by some
 * other words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word
 * another.
 * 
 * Now, given a dictionary consisting of many roots and a sentence. You need
 * to replace all the successor in the sentence with the root forming it. If
 * a successor has many roots can form it, replace it with the root with the
 * shortest length.
 * 
 * You need to output the sentence after the replacement.
 * 
 * Example 1:
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 * 
 * Note:
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 */

public class ReplaceWords648 {
    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<words.length; i++) {
            for (String d: dict) {
                if (words[i].startsWith(d)) {
                    words[i] = d;
                }
            }
        }
        return String.join(" ", words);
    }


    /**
     * https://leetcode.com/problems/replace-words/solution/
     */
    public String replaceWords2(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet();
        for (String root: roots) rootset.add(root);

        StringBuilder ans = new StringBuilder();
        for (String word: sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix)) break;
            }
            if (ans.length() > 0) ans.append(" ");
            ans.append(prefix);
        }
        return ans.toString();
    }


    public String replaceWords3(List<String> roots, String sentence) {
        Trie trie = constructTrie(roots);
        String[] words = sentence.split("\\s+");
        for (int i=0; i<words.length; i++) {
            String found = trie.search(words[i]);
            // System.out.println(found);
            words[i] = found;
        }
        return String.join(" ", words);
    }

    private Trie constructTrie(List<String> roots) {
        Trie root = new Trie();
        for (String word: roots) {
            root.add(word);
        }
        return root;
    }

    class Trie {
        Trie[] children = new Trie[26];
        String word;

        public void add(String word) {
            add(word.toCharArray(), 0);
        }

        private void add(char[] chars, int i) {
            if (i >= chars.length) {
                this.word = new String(chars);
                return;
            }
            if (children[chars[i]-'a'] == null) {
                children[chars[i]-'a'] = new Trie();
            }
            children[chars[i]-'a'].add(chars, i+1);
        }

        public String search(String word) {
            String found = search(word.toCharArray(), 0);
            if (found == null) return word;
            return found;
        }

        private String search(char[] chars, int i) {
            if (i >= chars.length) {
                return word;
            }
            if (word != null) return word;
            if (children[chars[i]-'a'] == null) return word;
            return children[chars[i]-'a'].search(chars, i+1);
        }
    }

}
