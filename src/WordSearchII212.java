/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * Hint:
 * You would need to optimize your backtracking to pass the larger test. Could
 * you stop backtracking earlier?
 *
 * If the current candidate does not exist in all words' prefix, you could stop
 * backtracking immediately. What kind of data structure could answer such query
 * efficiently? Does a hash table work? Why or why not? How about a Trie? If
 * you would like to learn how to implement a basic trie, please work on this
 * problem: Implement Trie (Prefix Tree) first.
 *
 */



public class WordSearchII212 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(board, i, j, root, res);
            }
        }
        return res;
    }

    private void helper(char[][] board, int i, int j, TrieNode node, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || !node.containsKey(board[i][j])) {
            return;
        }
        char curr = board[i][j];

        node = node.get(curr);
        if (node.containsWord()) {
            res.add(node.getWord());
            node.setWord(null);
        }

        board[i][j] = '#';
        helper(board, i, j+1, node, res);
        helper(board, i+1, j, node, res);
        helper(board, i, j-1, node, res);
        helper(board, i-1, j, node, res);
        board[i][j] = curr;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setWord(w);
        }
        return root;
    }

    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private String word;

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
        public void setWord(String w) {
            word = w;
        }
        public String getWord() {
            return word;
        }
        public boolean containsWord() {
            return word != null;
        }
    }

}
