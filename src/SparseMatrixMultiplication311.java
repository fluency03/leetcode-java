/**
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 */


public class SparseMatrixMultiplication311 {
    // brutal force
    // public int[][] multiply(int[][] A, int[][] B) {
    //     int ax = A.length;
    //     int by = B[0].length;
    //     int len = B.length;
    //     int[][] res = new int[ax][by];
    //
    //     for (int i=0; i<ax; i++) {
    //         for (int j=0; j<by; j++) {
    //             for (int k=0; k<len; k++) {
    //                 res[i][j] += A[i][k] * B[k][j];
    //             }
    //         }
    //     }
    //
    //     return res;
    // }


    // change order, add zero check
    public int[][] multiply(int[][] A, int[][] B) {
        int ax = A.length;
        int by = B[0].length;
        int len = B.length;
        int[][] res = new int[ax][by];

        for (int i=0; i<ax; i++) {
            for (int k=0; k<len; k++) {
                if (A[i][k] == 0) continue;
                for (int j=0; j<by; j++) {
                    if (B[k][j] == 0) continue;
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return res;
    }

}
