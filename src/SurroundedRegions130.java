/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */





public class SurroundedRegions130 {
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                markBoundary(board, i, 0);
            if (board[i][n-1] == 'O')
                markBoundary(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                markBoundary(board, 0, j);
            if (board[m-1][j] == 'O')
                markBoundary(board, m - 1, j);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void markBoundary(char[][] board, int i, int j) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || board[i][j] == 'X' || board[i][j] == '*') {
            return;
        }
        board[i][j] = '*';

        if (j < board[0].length - 2)
            markBoundary(board, i, j+1);
        if (i < board.length - 2)
            markBoundary(board, i+1, j);
        if (j > 1)
            markBoundary(board, i, j-1);
        if (i > 1)
            markBoundary(board, i-1, j);
    }

}
