/**
 * Definition for a trie (prefix tree) node.
 *
 * https://leetcode.com/articles/implement-trie-prefix-tree/
 */

public class TrieNode {

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
