/**
 * Follow up for N-Queens problem.
 *
 * Now, instead outputting board configurations, return the total number of
 * distinct solutions.
 */


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class NQueensII52 {
    private void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2, int n, Map<String, Integer> res) {
        if (r == n) {
            res.put("num", res.get("num") + 1);
        } else {
            for (int c = 0; c < n; c++) {
                int id1 = r - c + n, id2 = 2*n - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    cols[c] = true; d1[id1] = true; d2[id2] = true;
                    helper(r+1, cols, d1, d2, n, res);
                    cols[c] = false; d1[id1] = false; d2[id2] = false;
                }
            }
        }
    }

    public int totalNQueens(int n) {
        Map<String, Integer> res = new HashMap<>();
        res.put("num", 0);
        helper(0, new boolean[n], new boolean[2*n], new boolean[2*n], n, res);
        return res.get("num");
    }


    public static void main(String[] args) {
        NQueensII52 nq = new NQueensII52();

        System.out.println("---- 1");
        System.out.println(nq.totalNQueens(1));
        System.out.println("---- 4");
        System.out.println(nq.totalNQueens(4));
        System.out.println("---- 2");
        System.out.println(nq.totalNQueens(2));
        System.out.println("---- 3");
        System.out.println(nq.totalNQueens(3));
        System.out.println("---- 5");
        System.out.println(nq.totalNQueens(5));
        System.out.println("---- 6");
        System.out.println(nq.totalNQueens(6));
    }
}
