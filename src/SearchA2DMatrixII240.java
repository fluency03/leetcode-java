/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * For example,
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * Given target = 5, return true.
 * Given target = 20, return false.
 *
 */

public class SearchA2DMatrixII240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length ; j++) {
                if (matrix[i][j] == target) return true;
                if (matrix[i][j] > target) break;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int ye = matrix.length-1;
        if (ye == -1) return false;
        int xe = matrix[0].length-1;
        if (xe == -1) return false;

        return searchMatrix(matrix, target, 0, xe, 0, ye);
    }

    public boolean searchMatrix(int[][] matrix, int target, int xs, int xe, int ys, int ye) {
        if (xs < 0 || ys < 0 || xs > xe || ys > ye) return false;

        int midx = (xe - xs)/2 + xs;
        int midy = (ye - ys)/2 + ys;

        int now = matrix[midy][midx];
        if (now == target) {
            return true;
        }
        if (now > target) {
            return searchMatrix(matrix, target, xs, xe, ys, midy-1) ||
                searchMatrix(matrix, target, xs, midx-1, midy, ye);
        }
        return searchMatrix(matrix, target, midx+1, xe, ys, ye) ||
            searchMatrix(matrix, target, xs, midx, midy+1, ye);
    }

    /**
     * https://discuss.leetcode.com/topic/20064/my-concise-o-m-n-java-solution
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }


    public boolean searchMatrix4(int[][] matrix, int target) {
        if (matrix == null) return false;
        if (matrix.length == 0 || matrix[0].length == 0) return false; 
        return searchMatrix(0, 0, matrix.length-1, matrix[0].length-1, matrix, target);
    }
    
    public boolean searchMatrix(int loi, int loj, int hii, int hij, int[][] matrix, int target) {
        if (loi > hii || loj > hij) return false;
        if (matrix[loi][loj] > target|| matrix[hii][hij] < target) return false;
        
        int midi = (loi + hii) / 2;
        int midj = (loj + hij) / 2;
        if (matrix[midi][midj] == target) return true;
        if (searchMatrix(loi, midj+1, midi, hij, matrix, target) ||
            searchMatrix(midi+1, loj, hii, midj, matrix, target)) return true;
        if (matrix[midi][midj] > target) return searchMatrix(loi, loj, midi, midj, matrix, target);
        return searchMatrix(midi+1, midj+1, hii, hij, matrix, target);
    }

}
