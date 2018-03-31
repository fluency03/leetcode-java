/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * http://sudoku.com.au/TheRules.aspx
 *
 * The Sudoku board could be partially filled, where empty cells are filled
 * with the character '.'.
 *
 * http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png
 *
 * A partially filled sudoku which is valid.
 *
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only
 * the filled cells need to be validated.
 *
 */

public class ValidSudoku36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] cubs = new boolean[9][9];

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] == '.') continue;
                int n = board[i][j] - '1';
                if (rows[i][n] || cols[j][n] || cubs[cubeId(i,j)][n]) return false;
                rows[i][n] = true;
                cols[j][n] = true;
                cubs[cubeId(i,j)][n] = true;
            }
        }

        return true;
    }

    private int cubeId(int i, int j) {
        return (i/3)*3 + (j/3);
    }

    /**
     * https://leetcode.com/problems/valid-sudoku/discuss/15472/Short+Simple-Java-using-Strings
     */
    public boolean isValidSudoku2(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/valid-sudoku/discuss/15560/Yet-another-java-2ms-solution
     */
    public boolean isValidSudoku3s(char[][] board) {
        int [] vset = new int [9];
        int [] hset = new int [9];
        int [] bckt = new int [9];
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = 1 << (board[i][j] - '0') ;
                    if ((hset[i] & idx) > 0 ||
                        (vset[j] & idx) > 0 ||
                        (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;
                    hset[i] |= idx;
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        return true;
    }

}
