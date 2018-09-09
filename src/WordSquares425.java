/**
 * Given a set of words (without duplicates), find all word squares you can
 * build from them.
 * 
 * A sequence of words forms a valid word square if the kth row and column read
 * the exact same string, where 0 ≤ k < max(numRows, numColumns).
 * 
 * For example, the word sequence ["ball","area","lead","lady"] forms a word
 * square because each word reads the same both horizontally and vertically.
 * 
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * 
 * Note:
 * - There are at least 1 and at most 1000 words.
 * - All words will have the exact same length.
 * - Word length is at least 1 and at most 5.
 * - Each word contains only lowercase English alphabet a-z.
 * 
 * Example 1:
 * 
 * Input:
 * ["area","lead","wall","lady","ball"]
 * 
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 * 
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 * 
 * Example 2:
 * 
 * Input:
 * ["abat","baba","atan","atal"]
 * 
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 * 
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class WordSquares425 {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0 || words[0].length() == 0) return res;

        int len = words[0].length();
        TrieNode root = new TrieNode();
        for (String word: words) root.addWord(word);

        helper(new char[len][len], root, 0, len, new ArrayList<String>(), res);
        return res;
    }

    private void helper(char[][] square, TrieNode root, int pos, int len, List<String> sqr, List<List<String>> res) {
        if (pos == len) {
            if (sqr.size() == len) {
                List<String> newSqr = new ArrayList<String>();
                for (String s: sqr) {
                    newSqr.add(s);
                }
                res.add(newSqr);
            }
            return;
        }
        List<String> finds = root.search(Arrays.copyOfRange(square[pos], 0, pos), len);

        if (finds.size() == 0) return;
        for (String w: finds) {
            sqr.add(w);
            for (int j=pos; j<len; j++) square[j][pos] = w.charAt(j);
            helper(square, root, pos+1, len, sqr, res);
            sqr.remove(sqr.size() - 1);
        }
    }


    class TrieNode {
        boolean isWord = false;
        String word = null;
        TrieNode[] children = new TrieNode[26];
    
        public void addWord(String word) {
            addWord(word.toCharArray(), 0);
        }
    
        public void addWord(char[] chars, int i) {
            if (i == chars.length) {
                this.isWord = true;
                this.word = new String(chars);
                return;
            }
            int idx = chars[i] - 'a';
            if (children[idx] == null) {
                TrieNode t = new TrieNode();
                children[idx] = t;
            }
            children[idx].addWord(chars, i+1);
        }
    
        public List<String> search(char[] prefix, int length) {
            List<String> res = new ArrayList<String>();
            search(prefix, length, 0, res);
            return res;
        }

        public void search(char[] prefix, int length, int i, List<String> res) {
            if (i == length) {
                if (word != null) res.add(word);
                return;
            }
            if (i < prefix.length) {
                if (children[prefix[i] - 'a'] == null) return;
                children[prefix[i] - 'a'].search(prefix, length, i+1, res);
            } else {
                for (TrieNode child: children) {
                    if (child != null) child.search(prefix, length, i+1, res);
                }
            }
        }
    }


    public List<List<String>> wordSquares2(String[] words) {
        Arrays.sort(words);
        List<List<String>> res = new ArrayList<>();
        int W = words[0].length();
        char[][] board = new char[W][W];
        helper(board, 0, words, res, W);
        return res;
    }

    private void helper(char[][] board, int i, String[] words, List<List<String>> res, int W) {
        if (i >= W) {
            List<String> r = new ArrayList<>();
            for (int j=0; j<W; j++) {
                r.add(new String(board[j]));
            }
            res.add(r);
            return;
        }
        for (int j=i; j<W; j++) {
            board[i][j] = 'a';
        }
        String start = new String(board[i]);
        String prefix = new String(board[i], 0, i);
        int idx = Arrays.binarySearch(words, start);
        if (idx < 0) idx = - (idx + 1);
        for (int k=idx; k<words.length; k++) {
            String key = words[k];
            if (!key.startsWith(prefix)) break;
            char[] chars = key.toCharArray();
            for (int j=0; j<W; j++) {
                board[i][j] = chars[j];
            }
            for (int j=0; j<W; j++) {
                board[j][i] = chars[j];
            }
            helper(board, i+1, words, res, W);
        }
    }

}




