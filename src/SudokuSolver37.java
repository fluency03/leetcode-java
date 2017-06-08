/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by the character '.'.
 *
 * You may assume that there will be only one unique solution.
 */


import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class SudokuSolver37 {
    public void solveSudoku(char[][] board) {
        int L = board.length;
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<Integer, Set<Character>> cells = new HashMap<>();
        for (int l = 0; l < L; l++) {
            rows.put(l, new HashSet<Character>());
            cols.put(l, new HashSet<Character>());
            cells.put(l, new HashSet<Character>());
        }

        for (int i = 0; i < L; i++) {
            Set<Character> r = rows.get(i);
            for (int j = 0; j < L; j++) {
                char c = board[i][j];
                if (c != '.') {
                    Set<Character> co = cols.get(j);
                    Set<Character> ce = cells.get((i/3) * 3 + j/3);
                    r.add(c);
                    co.add(c);
                    ce.add(c);
                }
            }
        }

        Stack<List<Integer>> st = new Stack<>();
        int x = 0;
        int y = 0;

        while (x < L) {
            // System.out.println(x + ", " + y);
            if (board[x][y] == '.') {
                boolean found = false;

                for (int n = 49; n<=57; n++) {
                    char c = (char)n;
                    if (!rows.get(x).contains(c) &&
                            !cols.get(y).contains(c) &&
                            !cells.get(cellPos(x, y)).contains(c)) {
                        board[x][y] = c;
                        // System.out.println(x + ", " + y + ",    get: " + c);
                        rows.get(x).add(c);
                        cols.get(y).add(c);
                        cells.get(cellPos(x, y)).add(c);
                        st.push(Arrays.asList(x, y));
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    char newChar = (char)58;
                    while ((int)newChar > 57) {
                        List<Integer> p = st.peek();
                        x = p.get(0);
                        y = p.get(1);
                        char old = board[x][y];
                        // System.out.println(x + ", " + y + ",    old: " + old);

                        while (old == '9') {
                            rows.get(x).remove(old);
                            cols.get(y).remove(old);
                            cells.get(cellPos(x, y)).remove(old);
                            st.pop();
                            board[x][y] = '.';
                            p = st.peek();
                            x = p.get(0);
                            y = p.get(1);
                            old = board[x][y];
                            // System.out.println(x + ", " + y + ",    old: " + old);
                        }
                        rows.get(x).remove(old);
                        cols.get(y).remove(old);
                        cells.get(cellPos(x, y)).remove(old);
                        newChar = (char)(old + 1);
                        // System.out.println(x + ", " + y + ",    new: " + newChar);
                        while ((rows.get(x).contains(newChar) ||
                                cols.get(y).contains(newChar) ||
                                cells.get(cellPos(x, y)).contains(newChar)) && (int)newChar <= 57) {
                            newChar = (char)(newChar + 1);
                            // System.out.println(x + ", " + y + ",    new: " + newChar);
                        }
                        if ((int)newChar > 57) {
                            st.pop();
                            board[x][y] = '.';
                        }
                    }
                    board[x][y] = newChar;
                    rows.get(x).add(newChar);
                    cols.get(y).add(newChar);
                    cells.get(cellPos(x, y)).add(newChar);
                    // System.out.println(x + ", " + y + ",    new: " + newChar);
                }
            }

            if (y < L-1) {
                y++;
            } else {
                y = 0;
                x++;
            }
        }

    }

    private int cellPos(int x, int y) {
        return (x/3) * 3 + y/3;
    }


    /**
     * https://leetcode.com/submissions/detail/105414901/
     */
    public void solveSudoku2(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }


    public static void main(String[] args) {
        SudokuSolver37 ss = new SudokuSolver37();

        char[][] board = new char[][]{
            {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
            {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
            {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
            {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
            {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
            {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
            {'.', '.', '.', '2', '7', '5', '9', '.', '.'},
        };

        for (int i=0; i<9; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("\n");

        ss.solveSudoku(board);

        for (int j=0; j<9; j++) {
            System.out.println(Arrays.toString(board[j]));
        }
    }

}
