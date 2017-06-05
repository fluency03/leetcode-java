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
            for (int j = 0; j < n; j++) {
                if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && board[i][j] == 'O') {
                    markBoundary(board, i, j);
                }
            }
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

    private void markBoundary(char[][] board, int i, int j) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1 || board[i][j] != 'O') {
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

    private void markBoundary2(char[][] board, int i, int j) {
        board[i][j] = '*';
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        for (int k = 0; k < dx.length; k++) {
            int x = dx[k] + i;
            int y = dy[k] + j;
            if (x > 0 && x < board.length - 1 && y > 0 && y < board[0].length - 1 && board[x][y] == 'O') {
                markBoundary2(board, x, y);
            }
        }
    }


    /**
     * https://discuss.leetcode.com/topic/35191/java-easy-version-to-understand/4
     */
    public static void solve2(char[][] board) {
    	if (board == null || board.length == 0)
    		return;
    	int rows = board.length, columns = board[0].length;
    	int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < columns; j++) {
    			if ((i == 0 || i == rows - 1 || j == 0 || j == columns - 1) && board[i][j] == 'O') {
    				Queue<Point> queue = new LinkedList<>();
    				board[i][j] = 'B';
    				queue.offer(new Point(i, j));
    				while (!queue.isEmpty()) {
    					Point point = queue.poll();
    					for (int k = 0; k < 4; k++) {
    						int x = direction[k][0] + point.x;
    						int y = direction[k][1] + point.y;
    						if (x >= 0 && x < rows && y >= 0 && y < columns && board[x][y] == 'O') {
    							board[x][y] = 'B';
    							queue.offer(new Point(x, y));
    						}
    					}
    				}
    			}
    		}
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < columns; j++) {
    			if (board[i][j] == 'B')
    				board[i][j] = 'O';
    			else if (board[i][j] == 'O')
    				board[i][j] = 'X';
    		}

    }


}
