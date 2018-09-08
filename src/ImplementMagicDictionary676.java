/**
 * Implement a magic directory with buildDict, and search methods.
 * 
 * For the method buildDict, you'll be given a list of non-repetitive words to
 * build a dictionary.
 * 
 * For the method search, you'll be given a word, and judge whether if you
 * modify exactly one character into another character in this word, the
 * modified word is in the dictionary you just built.
 * 
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * 
 * Note:
 * You may assume that all the inputs are consist of lowercase letters a-z.
 * For contest purpose, the test data is rather small by now. You could think
 * about highly efficient algorithm after the contest.
 * Please remember to RESET your class variables declared in class
 * MagicDictionary, as static/class variables are persisted across multiple
 * test cases. Please see here for more details.
 */


public class ImplementMagicDictionary676 {

    class MagicDictionary {
        private Trie trie = new Trie();

        /** Initialize your data structure here. */
        public MagicDictionary() {
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String word: dict) {
                trie.add(word);
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            return trie.search(word);
        }

        class Trie {
            Trie[] children = new Trie[26];
            boolean isWord;

            void add(String word) {
                if (word == null || word.length() == 0) return;
                add(word.toCharArray(), 0);
            }

            void add(char[] word, int i) {
                if (word.length == i) {
                    isWord = true;
                    return;
                }
                if (children[word[i]-'a'] == null) {
                    children[word[i]-'a'] = new Trie();
                }
                children[word[i]-'a'].add(word, i+1);
            }

            boolean search(String word) {
                if (word == null) return false;
                return search(word.toCharArray(), 0, false);
            }

            boolean search(char[] word, int start, boolean modified) {
                if (word.length == start) {
                    return modified && isWord;
                }
                for (int i=0; i<26; i++) {
                    if (children[i] == null) continue;
                    if (word[start] == (i + 'a')) {
                        if (children[i].search(word, start+1, modified)) return true;
                    } else {
                        if (modified) continue;
                        if (children[i].search(word, start+1, true)) return true;
                    }
                }
                return false;
            }
            
        }
    }


    /**
     * https://leetcode.com/problems/implement-magic-dictionary/solution/
     */
    class MagicDictionary2 {
        Map<Integer, ArrayList<String>> buckets;
        public MagicDictionary() {
            buckets = new HashMap();
        }

        public void buildDict(String[] words) {
            for (String word: words) {
                buckets.computeIfAbsent(word.length(), x -> new ArrayList()).add(word);
            }
        }

        public boolean search(String word) {
            if (!buckets.containsKey(word.length())) return false;
            for (String candidate: buckets.get(word.length())) {
                int mismatch = 0;
                for (int i = 0; i < word.length(); ++i) {
                    if (word.charAt(i) != candidate.charAt(i)) {
                        if (++mismatch > 1) break;
                    }
                }
                if (mismatch == 1) return true;
            }
            return false;
        }
    }


    /**
     * https://leetcode.com/problems/implement-magic-dictionary/solution/
     */
    public class MagicDictionary3 {
        Set<String> words;
        Map<String, Integer> count;

        public MagicDictionary() {
            words = new HashSet();
            count = new HashMap();
        }

        private ArrayList<String> generalizedNeighbors(String word) {
            ArrayList<String> ans = new ArrayList();
            char[] ca = word.toCharArray();
            for (int i = 0; i < word.length(); ++i) {
                char letter = ca[i];
                ca[i] = '*';
                String magic = new String(ca);
                ans.add(magic);
                ca[i] = letter;
            }
            return ans;
        }

        public void buildDict(String[] words) {
            for (String word: words) {
                this.words.add(word);
                for (String nei: generalizedNeighbors(word)) {
                    count.put(nei, count.getOrDefault(nei, 0) + 1);
                }
            }
        }

        public boolean search(String word) {
            for (String nei: generalizedNeighbors(word)) {
                int c = count.getOrDefault(nei, 0);
                if (c > 1 || c == 1 && !words.contains(word)) return true;
            }
            return false;
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */


}
