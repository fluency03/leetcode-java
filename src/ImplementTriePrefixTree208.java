/**
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Example:
 * 
 * Trie trie = new Trie();
 * 
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");   
 * trie.search("app");     // returns true
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */


public class ImplementTriePrefixTree208 {

    class Trie {

        private Trie[] children = new Trie[26];
        private boolean isWord = false;
        
        /** Initialize your data structure here. */
        public Trie() {
            
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            insert(word.toCharArray(), 0);
        }
        
        private void insert(char[] chars, int i) {
            if (i == chars.length) {
                this.isWord = true;
                return;
            }
            if (this.children[chars[i]-'a'] == null) {
                this.children[chars[i]-'a'] = new Trie();
            }
            this.children[chars[i]-'a'].insert(chars, i+1);
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return search(word.toCharArray(), 0);
        }
        
        private boolean search(char[] chars, int i) {
            if (i == chars.length) return this.isWord;
            if (this.children[chars[i]-'a'] == null) return false;
            return this.children[chars[i]-'a'].search(chars, i+1);
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(prefix.toCharArray(), 0);
        }

        private boolean startsWith(char[] chars, int i) {
            if (i == chars.length) return true; 
            if (this.children[chars[i]-'a'] == null) return false;
            return this.children[chars[i]-'a'].startsWith(chars, i+1);
        }
        
    }


    /**
     * https://leetcode.com/problems/implement-trie-prefix-tree/solution/
     */
    class Trie2 {
        private TrieNode root;
    
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }
    
        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setLeaf();
        }
    
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.containsKey(c)) {
                    return false;
                }
                node = node.get(c);
            }
            return node.isLeaf();
        }
    
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!node.containsKey(c)) {
                    return false;
                }
                node = node.get(c);
            }
            return true;
        }
    }

    class TrieNode {
        // R links to node children
        private TrieNode[] links;
    
        private final int R = 26;
    
        private boolean isLeaf;
    
        public TrieNode() {
            links = new TrieNode[R];
        }
    
        public boolean containsKey(char c) {
            return links[c -'a'] != null;
        }
        public TrieNode get(char c) {
            return links[c -'a'];
        }
        public void put(char c, TrieNode node) {
            links[c -'a'] = node;
        }
        public void setLeaf() {
            isLeaf = true;
        }
        public boolean isLeaf() {
            return isLeaf;
        }
    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

  
}
