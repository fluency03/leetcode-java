/**
 * Given a positive integer n, generate a square matrix filled with elements
 * from 1 to n2 in spiral order.
 * 
 * Example:
 * 
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */


public class SpiralMatrixII59 {
    public int[][] generateMatrix(int n) {
        if (n == 0) return new int[0][0];
        int[][] matrix = new int[n][n];
        boolean[][] filled = new boolean[n][n];
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0;
        int y = 0;
        int d = 0;
        for (int i=1; i<=n*n; i++) {
            matrix[x][y] = i;
            filled[x][y] = true;
            int xx = x + dx[d];
            int yy = y + dy[d];
            if (xx >= 0 && xx < n && yy >= 0 && yy < n && !filled[xx][yy]) {
                x = xx;
                y = yy;
            } else {
                d = (d + 1) % 4;
                x += dx[d];
                y += dy[d];
            }
        }
        return matrix;
    }

}
