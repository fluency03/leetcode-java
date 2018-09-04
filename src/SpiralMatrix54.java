/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements
 * of the matrix in spiral order.
 * 
 * Example 1:
 * 
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */


public class SpiralMatrix54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        helper(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, res);
        return res;
    }


    private void helper(int[][] matrix, int x0, int y0, int x1, int y1, List<Integer> res) {
        if (x0 > x1 || y0 > y1) return;
        if (x0 == x1) {
            for (int i=y0; i<=y1; i++) res.add(matrix[x0][i]);
            return;
        }
        if (y0 == y1) {
            for (int i=x0; i<=x1; i++) res.add(matrix[i][y0]);
            return;
        }
        for (int i=y0; i<y1; i++) res.add(matrix[x0][i]);
        for (int i=x0; i<x1; i++) res.add(matrix[i][y1]);
        for (int i=y1; i>y0; i--) res.add(matrix[x1][i]);
        for (int i=x1; i>x0; i--) res.add(matrix[i][y0]);

        helper(matrix, x0 + 1, y0 + 1, x1 - 1, y1 - 1, res);
    }


    /**
     * https://leetcode.com/problems/spiral-matrix/solution/
     */
    public List < Integer > spiralOrder2(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/spiral-matrix/solution/
     */
    public List<Integer> spiralOrder3(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }


    public List<Integer> spiralOrder4(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int M = matrix.length;
        int N = matrix[0].length;
        int mid = Math.min((M+1)/2, (N+1)/2);
        for (int c=0; c<mid; c++) {
            for (int j=c; j<=N-c-1; j++) res.add(matrix[c][j]);
            for (int i=c+1; i<=M-c-1; i++) res.add(matrix[i][N-c-1]);
            if (M-c-1 > c) for (int j=N-c-2; j>=c; j--) res.add(matrix[M-c-1][j]);
            if (N-c-1 > c) for (int i=M-c-2; i>=c+1; i--) res.add(matrix[i][c]);
        }
        return res;
    }

}
