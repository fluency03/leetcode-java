/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 *
 */


public class MaximalRectangle85 {
    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        if (N == 0) return 0;
        int M = matrix[0].length;
        if (M == 0) return 0;

        int[][] left = new int[N+1][M+1];
        int[][] top = new int[N+1][M+1];
        int maxArea = 0;

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (matrix[i-1][j-1] == '1') {
                    left[i][j] = left[i][j-1] + 1;
                    top[i][j] = top[i-1][j] + 1;
                    maxArea = Math.max(maxArea, helper(left, top, i, j));
                }
            }
        }

        return maxArea;
    }

    private int helper(int[][] left, int[][] top, int i, int j) {
        int maxArea = 0;
        System.out.println("-------- i: " + i + "; j:" + j);

        int move = 0;
        int minH = top[i][j];
        while (move < left[i][j]) {
            System.out.println("move: " + move);
            minH = Math.min(minH, top[i][j-move]);

            maxArea = Math.max(maxArea, (move+1) * minH);
            System.out.println(move + " X " + minH);
            System.out.println("maxArea: " + maxArea);
            move++;
        }

        return maxArea;
    }


    public static void main(String[] args) {
        MaximalRectangle85 mr = new MaximalRectangle85();

        char[][] matrix = new char[][]{
          { '0', '1', '1', '0', '1' },
          { '1', '1', '0', '1', '0' },
          { '0', '1', '1', '1', '0' },
          { '1', '1', '1', '1', '0' },
          { '1', '1', '1', '1', '1' },
        };

        System.out.println(mr.maximalRectangle(matrix));
    }

}
