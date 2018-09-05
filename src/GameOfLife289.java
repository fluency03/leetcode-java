/**
 * According to the Wikipedia's article: "The Game of Life, also known simply
 * as Life, is a cellular automaton devised by the British mathematician
 * John Horton Conway in 1970."
 * 
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal, vertical,
 * diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 * 
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * 
 * Write a function to compute the next state (after one update) of the board given its current state.
 * 
 * Follow up: 
 * Could you solve it in-place? Remember that the board needs to be updated at
 * the same time: You cannot update some cells first and then use their updated
 * values to update other cells.
 * 
 * In this question, we represent the board using a 2D array. In principle, the
 * board is infinite, which would cause problems when the active area encroaches
 * the border of the array. How would you address these problems?
 */


public class GameOfLife289 {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int n = board.length;
        int m = board[0].length;
        int[][] newBoard = new int[n][m];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 0) {
                    newBoard[i][j] = countLive(board, i, j) == 3 ? 1 : 0;
                } else {
                    int lives = countLive(board, i, j);
                    newBoard[i][j] = (lives == 2 || lives == 3) ? 1 : 0;
                }
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    private int countLive(int[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        int res = 0;
        for (int i=x-1; i<=x+1; i++) {
            if (i < 0 || i >= n) continue;
            for (int j=y-1; j<=y+1; j++) {
                if (j < 0 || j >= m || (i == x && j == y)) continue;
                if (board[i][j] == 1) res++;
            }
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
     */
    public void gameOfLife2(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {  
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }


    public void gameOfLife3(int[][] board) {
        int M = board.length;
        int N = board[0].length;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                update(board, i, j, M, N);
            }
        }
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                refresh(board, i, j);
            }
        }
    }

    private void update(int[][] board, int i, int j, int M, int N) {
        int lives = 0;
        for (int x=i-1; x<=i+1; x++) {
            if (x < 0 || x >= M) continue;
            for (int y=j-1; y<=j+1; y++) {
                if (y < 0 || y >= N || (x == i && y == j)) continue;
                if ((board[x][y] & 1) == 1) lives++;
            }
        }
        boolean wasLive = (board[i][j] & 1) == 1;
        if (wasLive) {
            if (lives == 2 || lives == 3) {
                board[i][j] |= 1 << 1;
            }
        } else {
            if (lives == 3) {
                board[i][j] |= 1 << 1;
            }
        }
    }

    private void refresh(int[][] board, int i, int j) {
        board[i][j] = board[i][j] >> 1;
    }

}

