/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 */


public class WordSearch79 {
    public boolean exist(char[][] board, String word) {

        if (word.length() == 0) {
            return true;
        }

        if (board.length == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                if (helper(i, j, 0, board, word, visited)) {
                    return true;
                }
            }
        }

        return false;

    }


    private boolean helper(int i, int j, int index, char[][] board, String word, boolean[][] visited) {
        if (index >= word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        if (helper(i, j+1, index + 1, board, word, visited) ||
                helper(i+1, j, index + 1, board, word, visited) ||
                helper(i, j-1, index + 1, board, word, visited) ||
                helper(i-1, j, index + 1, board, word, visited)) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }


    /**
     * https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space
     */
    public boolean exist2(char[][] board, String word) {

        if (word.length() == 0) {
            return true;
        }

        if (board.length == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(i, j, 0, board, word)) {
                    return true;
                }
            }
        }

        return false;

    }


    private boolean helper(int i, int j, int index, char[][] board, String word) {
        if (index >= word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        board[i][j] ^= 256;

        if (helper(i, j+1, index + 1, board, word) ||
                helper(i+1, j, index + 1, board, word) ||
                helper(i, j-1, index + 1, board, word) ||
                helper(i-1, j, index + 1, board, word)) {
            return true;
        }

        board[i][j] ^= 256;

        return false;
    }

}
