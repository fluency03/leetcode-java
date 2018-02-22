/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 *
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 */


public class RotateImage48 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length == 1) return;
        int len = matrix.length;
        int mid = (len-1)/2;
        for (int i=0; i<=mid; i++) {
            if (i > len-i-2) continue;
            int last = Math.max(len-i-2, i);
            for (int j=i; j<=last; j++) {
                int forth = matrix[j][len-i-1];
                int first = matrix[i][j];
                matrix[j][len-i-1] = first;
                int second = matrix[len-j-1][i];
                matrix[i][j] = second;
                int third = matrix[len-i-1][len-j-1];
                matrix[len-j-1][i] = third;
                matrix[len-i-1][len-j-1] = forth;
            }
        }
    }


    /**
     * https://leetcode.com/problems/rotate-image/discuss/18879/AC-Java-in-place-solution-with-explanation-Easy-to-understand.
     */
    public void rotate2(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }

}
