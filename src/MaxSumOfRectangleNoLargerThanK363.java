/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a
 * rectangle in the matrix such that its sum is no larger than k.
 * 
 * Example:
 * Given matrix = [
 *   [1,  0, 1],
 *   [0, -2, 3]
 * ]
 * k = 2
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2
 * is the max number no larger than k (k = 2).
 * 
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 */


public class MaxSumOfRectangleNoLargerThanK363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] sum = new int[m+1][n+1];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                sum[i+1][j+1] = matrix[i][j] + sum[i][j+1] + sum[i+1][j] - sum[i][j];
            }
        }

        int res = Integer.MIN_VALUE;
        for (int y1=0; y1<m; y1++) {
            for (int y2=y1+1; y2<=m; y2++) {
                for (int x1=0; x1<n; x1++) {
                    for (int x2=x1+1; x2<=n; x2++) {
                        int tmp = sum[y2][x2] - sum[y2][x1] - sum[y1][x2] + sum[y1][x1];
                        if (tmp == k) return tmp;
                        if (tmp > res && tmp <= k) {
                            res = tmp;
                        }
                    }
                }
            }
        }
        return res;
    }












}
