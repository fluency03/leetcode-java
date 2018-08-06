/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example 1:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * 
 * Example 2:
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */

public class SearchA2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid - 1;;
            }
        }
        int row = start;
        start = 0;
        end = matrix[0].length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) return true;
            if (matrix[row][mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return matrix[row][start] == target;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int start = 0;
        int end = matrix.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid - 1;;
            }
        }
        int row = target >= matrix[end][0] ? end : start;
        start = 0;
        end = matrix[0].length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) return true;
            if (matrix[row][mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return matrix[row][start] == target;
    }


    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int M = matrix.length;
        int N = matrix[0].length;
        int start = 0;
        int end = M * N - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int midVal = matrix[mid / N][mid % N];
            if (midVal == target) return true;
            if (midVal < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return matrix[start / N][start % N] == target;
    }

}
