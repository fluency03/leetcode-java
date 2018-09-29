/**
 * 
 */

public class UniquePathIII {

    // dp[0][0] = 1;
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j+1]; 

    // public int uniquePath(int w, int h) {
    //     if (w < 0 || h < 0) return 0;
    //     if (w == 0 || h == 0) return 1;
    //     int[][] dp = new int[w+1][h+1];
    //     dp[0][0] = 1;
    //     for (int i=1; i<=w; i++) {
    //         for (int j=0; j<=i && j<=h; j++) {
    //             dp[i][j] = dp[i-1][j] + (j == 0 ? 0: dp[i-1][j-1]) + (j == h ? 0 : dp[i-1][j+1]);
    //         }
    //     }
    //     return dp[w][0];
    // }


    // public int uniquePath(int w, int h) {
    //     if (w < 0 || h < 0) return 0;
    //     if (w == 0 || h == 0) return 1;
    //     int[] dp = new int[h+1];
    //     dp[0] = 1;
    //     for (int i=1; i<=w; i++) {
    //         int pre = 0;
    //         for (int j=0; j<=i && j<=h; j++) {
    //             int curr = dp[j];
    //             dp[j] = pre + curr + (j == h ? 0 : dp[j+1]);
    //             pre = curr;
    //         }
    //     }
    //     return dp[0];
    // }


    public int uniquePath(int w, int h, int[][] mustPoints) {
        if (w < 0 || h < 0) return 0;
        boolean[][] must = new int[w+1][h+1];
        for (int[] p: mustPoints) {
            must[p[0]][p[1]] = true;
        }
        int[][] A = new int[w+1][h+1];
        A[0][0] = 1;
        int[][] B = new int[w+1][h+1];
        B[0][0] = must[0][0] ? 1 : 0;
        for (int i=1; i<=w; i++) {
            for (int j=0; j<=i && j<=h; j++) {
                A[i][j] = path(A, i, j, h);
                if (must[i][j]) {
                    B[i][j] = A[i][j];
                } else {
                    B[i][j] = path(B, i, j, h);
                }
            }
        }
        return B[w][0];
    }

    private int path(int[][] X, int i, int j, int h) {
        return X[i-1][j] + (j == 0 ? 0: X[i-1][j-1]) + (j == h ? 0 : X[i-1][j+1]);
    }


    public static void main(String[] args) {
        Solution slt = new Solution();
        System.out.println(slt.uniquePath(0, 0)); // 1
        System.out.println(slt.uniquePath(1, 1)); // 1
        System.out.println(slt.uniquePath(2, 1)); // 2
        System.out.println(slt.uniquePath(2, 2)); // 2
        System.out.println(slt.uniquePath(3, 0)); // 1
        System.out.println(slt.uniquePath(3, 1)); // 4
        System.out.println(slt.uniquePath(3, 2)); // 4
    }

}
