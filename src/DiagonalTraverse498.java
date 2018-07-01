/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements
 * of the matrix in diagonal order as shown in the below image.
 * 
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 * 
 * Explanation:
 *    https://leetcode.com/static/images/problemset/diagonal_traverse.png
 * 
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 */


public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int M = matrix.length;
        int N = matrix[0].length;
        int total = M * N;
        int[] res = new int[total];
        int i = 0;
        int[] pos = new int[2];
        res[i++] = matrix[0][0];
        while (i < total) {
            pos = next(pos, M, N);
            res[i++] = matrix[pos[0]][pos[1]];
        }
        return res;
    }

    private int[] next(int[] pos, int M, int N) {
        int i = pos[0];
        int j = pos[1];
        if (isEven(i+j)) { // top-right
            int ni = i - 1;
            int nj = j + 1;
            if (nj == N) {
                nj = N - 1;
                ni = i + 1;
            } else if (ni < 0) {
                ni = 0;
            }
            return new int[]{ni, nj};
        } else { // bottom-left
            int ni = i + 1;
            int nj = j - 1;
            if (ni == M) {
                ni = M - 1;
                nj = j + 1;
            } else if (nj < 0) {
                nj = 0;
            }
            return new int[]{ni, nj};
        }
    }
    
    private boolean isEven(int x) {
        return x % 2 == 0;
    }

}
