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


    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<String> findWords2(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words.length == 0) return new ArrayList<>();
        int M = board.length;
        int N = board[0].length;
        Trie trie = constructTrie(words);
        
        Set<String> set = new HashSet<>();
        boolean[][] visited = new boolean[M][N];
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                dfs(board, visited, trie, i, j, M, N, set);
            }
        }
        
        return new ArrayList<>(set);
    }

    
    private void dfs(char[][] board, boolean[][] visited, Trie trie, int i, int j, int M, int N, Set<String> set) {
        if (trie == null) return;
        if (trie.word != null) set.add(trie.word);
        if (i < 0 || j < 0 || i >= M || j >= N || visited[i][j]) return;
        visited[i][j] = true;
        char c = board[i][j];
        Trie next = trie.get(c);
        // if (next == null) return;
        for (int[] d: dirs) {
            dfs(board, visited, next, i+d[0], j+d[1], M, N, set);
        }
        visited[i][j] = false;
    }
    
    
    private Trie constructTrie(String[] words) {
        Trie t = new Trie();
        for (String word: words) t.addWord(word);
        return t;
    }

    class Trie {
        Trie[] children = new Trie[26];
        String word = null;
    
        public void addWord(String word) {
            addWord(word.toCharArray(), 0);
        }

        public void addWord(char[] chars, int i) {
            if (i == chars.length) {
                word = new String(chars);
                return;
            }
            if (children[chars[i]-'a'] == null) {
                children[chars[i]-'a'] = new Trie();
            }
            children[chars[i]-'a'].addWord(chars, i+1);
        }
        
        public Trie get(char c) {
            return children[c-'a'];
        }
        
    }

    /**
     * https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
     * 
     * 59ms: Use search and startsWith in Trie class like this popular solution.
     * 33ms: Remove Trie class which unnecessarily starts from root in every dfs call.
     * 30ms: Use w.toCharArray() instead of w.charAt(i).
     * 22ms: Use StringBuilder instead of c1 + c2 + c3.
     * 20ms: Remove StringBuilder completely by storing word instead of boolean in TrieNode.
     * 20ms: Remove visited[m][n] completely by modifying board[i][j] = '#' directly.
     * 18ms: check validity, e.g., if(i > 0) dfs(...), before going to the next dfs.
     * 17ms: De-duplicate c - a with one variable i.
     * 15ms: Remove HashSet completely. dietpepsi's idea is awesome.
     */
    public List<String> findWords3(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie2(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }
    
    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }
    
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res); 
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
        board[i][j] = c;
    }
    
    public TrieNode buildTrie2(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
           }
           p.word = w;
        }
        return root;
    }
    
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

}
