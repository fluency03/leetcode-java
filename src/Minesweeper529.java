/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 * 
 * You are given a 2D char matrix representing the game board. 'M' represents
 * an unrevealed mine, 'E' represents an unrevealed empty square, 'B'
 * represents a revealed blank square that has no adjacent (above, below, left,
 * right, and all 4 diagonals) mines, digit ('1' to '8') represents how many
 * mines are adjacent to this revealed square, and finally 'X' represents a
 * revealed mine.
 * 
 * Now given the next click position (row and column indices) among all the
 * unrevealed squares ('M' or 'E'), return the board after revealing this
 * position according to the following rules:
 * 
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it
 * to revealed blank ('B') and all of its adjacent unrevealed squares should be
 * revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then
 * change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * 
 * Example 1:
 * Input: 
 * 
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 * 
 * lick : [3,0]
 * 
 * Output: 
 * 
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * 
 * Explanation:
 * https://leetcode.com/static/images/problemset/minesweeper_example_1.png
 * 
 * Example 2:
 * Input: 
 * 
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * 
 * Click : [1,2]
 * 
 * Output: 

 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * 
 * Explanation:
 * https://leetcode.com/static/images/problemset/minesweeper_example_2.png
 * 
 * Note:
 * he range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'), which
 * also means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have
 * been revealed).
 * For simplicity, not mentioned rules should be ignored in this problem.
 * For example, you don't need to reveal all the unrevealed mines when the game
 * is over, consider any cases that you will win the game or flag any squares.
 */

public class Minesweeper529 {
    private int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0|| board[0].length == 0) return board;
        int M = board.length;
        int N = board[0].length;
        helper(board, click[0], click[1], M, N);
        return board;
    }

    public void helper(char[][] board, int i, int j, int M, int N) {
        if (i < 0 || i >= M || j < 0 || j >= N || board[i][j] == 'B' || isDigit(board[i][j]) || board[i][j] == 'X') return;
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        }

        // board[i][j] == 'E'
        int numMines = numAdjacentMines(board, i, j, M, N);
        if (numMines == 0) {
            board[i][j] = 'B';
            for (int[] d: directions) {
                helper(board, i + d[0], j + d[1], M, N);
            }
        } else {
            board[i][j] = Character.forDigit(numMines, 10);
        }
    }

    private boolean isDigit(char c) {
        return c >= '1' && c <= '8';
    }

    private int numAdjacentMines(char[][] board, int i, int j, int M, int N) {
        int numMines = 0;
        for (int[] d: directions) {
            int ii = i + d[0];
            int jj = j + d[1];
            if (ii < 0 || ii >= M || jj < 0 || jj >= N) continue;
            if (board[ii][jj] == 'M' || board[ii][jj] == 'X') numMines++;
        }
        return numMines;
    }


}
