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
                Set<Character> r = rows.get(x);
                Set<Character> co = cols.get(y);
                Set<Character> ce = cells.get((x/3) * 3 + y/3);

                for (int n = 49; n<=57; n++) {
                    char c = (char)n;
                    if (!r.contains(c) && !co.contains(c) && !ce.contains(c)) {
                        board[x][y] = c;
                        // System.out.println(x + ", " + y + ",    get: " + c);
                        r.add(c);
                        co.add(c);
                        ce.add(c);
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
                            r = rows.get(x);
                            co = cols.get(y);
                            ce = cells.get((x/3) * 3 + y/3);
                            r.remove(old);
                            co.remove(old);
                            ce.remove(old);
                            st.pop();
                            board[x][y] = '.';
                            p = st.peek();
                            x = p.get(0);
                            y = p.get(1);
                            old = board[x][y];
                            // System.out.println(x + ", " + y + ",    old: " + old);
                        }
                        r = rows.get(x);
                        co = cols.get(y);
                        ce = cells.get((x/3) * 3 + y/3);
                        r.remove(old);
                        co.remove(old);
                        ce.remove(old);
                        newChar = (char)(old + 1);
                        // System.out.println(x + ", " + y + ",    new: " + newChar);
                        while ((r.contains(newChar) || co.contains(newChar) || ce.contains(newChar)) && (int)newChar <= 57) {
                            newChar = (char)(newChar + 1);
                            // System.out.println(x + ", " + y + ",    new: " + newChar);
                        }
                        if ((int)newChar > 57) {
                            st.pop();
                            board[x][y] = '.';
                        }
                    }
                    r = rows.get(x);
                    co = cols.get(y);
                    ce = cells.get((x/3) * 3 + y/3);
                    board[x][y] = newChar;
                    // System.out.println(x + ", " + y + ",    new: " + newChar);
                    r.add(newChar);
                    co.add(newChar);
                    ce.add(newChar);
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
