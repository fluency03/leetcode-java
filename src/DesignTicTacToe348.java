/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * 
 * You may assume the following rules:
 * 
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical,
 * or diagonal row wins the game.
 * 
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * 
 * TicTacToe toe = new TicTacToe(3);
 * 
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * 
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * 
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * 
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * 
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * 
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * 
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * 
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 */


public class DesignTicTacToe348 {
    class TicTacToe {
        private Set<Integer>[] player1Rows;
        private Set<Integer>[] player1Cols;
        private Set<Integer>[] player1Dias;
        
        private Set<Integer>[] player2Rows;
        private Set<Integer>[] player2Cols;
        private Set<Integer>[] player2Dias;
        
        private int n;
        
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n = n;
            this.player1Rows = new Set[n];
            for (int i=0; i<n; i++) this.player1Rows[i] = new HashSet<>();
            this.player1Cols = new Set[n];
            for (int i=0; i<n; i++) this.player1Cols[i] = new HashSet<>();
            this.player1Dias = new Set[2];
            for (int i=0; i<2; i++) this.player1Dias[i] = new HashSet<>();
            
            this.player2Rows = new Set[n];
            for (int i=0; i<n; i++) this.player2Rows[i] = new HashSet<>();
            this.player2Cols = new Set[n];
            for (int i=0; i<n; i++) this.player2Cols[i] = new HashSet<>();
            this.player2Dias = new Set[2];
            for (int i=0; i<2; i++) this.player2Dias[i] = new HashSet<>();
        }
        
        /** Player {player} makes a move at ({row}, {col}).
            @param row The row of the board.
            @param col The column of the board.
            @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
                    0: No one wins.
                    1: Player 1 wins.
                    2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if (player == 1) {
                this.player1Rows[row].add(col);
                if (this.player1Rows[row].size() == n) return 1;
                this.player1Cols[col].add(row);
                if (this.player1Cols[col].size() == n) return 1;
                if (row == col) {
                    this.player1Dias[0].add(row);
                    if (this.player1Dias[0].size() == n) return 1;
                }
                if (row == n-col-1) {
                    this.player1Dias[1].add(row);
                    if (this.player1Dias[1].size() == n) return 1;
                }
            } else if (player == 2) {
                this.player2Rows[row].add(col);
                if (this.player2Rows[row].size() == n) return 2;
                this.player2Cols[col].add(row);
                if (this.player2Cols[col].size() == n) return 2;
                if (row == col) {
                    this.player2Dias[0].add(row);
                    if (this.player2Dias[0].size() == n) return 2;
                }
                if (row == n-col-1) {
                    this.player2Dias[1].add(row);
                    if (this.player2Dias[1].size() == n) return 2;
                }
            }
            return 0;
        }
    }
    

    /**
     * https://leetcode.com/problems/design-tic-tac-toe/discuss/81898/Java-O(1)-solution-easy-to-understand
     */
    class TicTacToe2 {
        private int[] rows;
        private int[] cols;
        private int diagonal;
        private int antiDiagonal;
        
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }
        
        /** Player {player} makes a move at ({row}, {col}).
            @param row The row of the board.
            @param col The column of the board.
            @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
                    0: No one wins.
                    1: Player 1 wins.
                    2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int toAdd = player == 1 ? 1 : -1;
            
            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col)
            {
                diagonal += toAdd;
            }
            
            if (col == (cols.length - row - 1))
            {
                antiDiagonal += toAdd;
            }
            
            int size = rows.length;
            if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size  ||
                Math.abs(antiDiagonal) == size)
            {
                return player;
            }
            
            return 0;
        }
    }


    /**
     * https://leetcode.com/problems/design-tic-tac-toe/discuss/81896/78-lines-O(1)-JavaPython
     */
    class TicTacToe3 {
        public TicTacToe(int n) {
            count = new int[6*n][3];
        }

        public int move(int row, int col, int player) {
            int n = count.length / 6;
            for (int x : new int[]{row, n+col, 2*n+row+col, 5*n+row-col})
                if (++count[x][player] == n)
                    return player;
            return 0;
        }

        int[][] count;
    }

    class TicTacToe4 {
        private int[] rows;
        private int[] cols;
        private int diag1;
        private int diag2;
        private int N;

        /** Initialize your data structure here. */
        public TicTacToe4(int n) {
            this.rows = new int[n];
            this.cols = new int[n];
            this.diag1 = 0;
            this.diag2 = 0;
            this.N = n;
        }

        /** Player {player} makes a move at ({row}, {col}).
            @param row The row of the board.
            @param col The column of the board.
            @param player The player, can be either 1 or 2.
            @return The current winning condition, can be either:
                    0: No one wins.
                    1: Player 1 wins.
                    2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int diff = player == 1 ? 1 : -1;
            this.rows[row] += diff;
            this.cols[col] += diff;
            if (row == col) this.diag1 += diff;
            if (row + col == N-1) this.diag2 += diff;
            if (this.rows[row] == N || this.cols[col] == N || this.diag1 == N || this.diag2 == N) return 1;
            if (this.rows[row] == -N || this.cols[col] == -N || this.diag1 == -N || this.diag2 == -N) return 2;
            return 0;
        }
    }

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
  

}

