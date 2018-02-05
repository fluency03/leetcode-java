/**
 * Given two integer arrays A and B, return the maximum length of an subarray
 * that appears in both arrays.
 *
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 *
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *
 * Note:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 */


public class MaximumLengthOfRepeatedSubarray718 {
    public int findLength(int[] A, int[] B) {
        int[][] res = new int[A.length+1][B.length+1];
        int[][] lens = new int[A.length+1][B.length+1];

        for (int i=1; i<=A.length; i++) {
            for (int j=1; j<=B.length; j++) {
                lens[i][j] = (A[i-1] == B[j-1]) ? lens[i-1][j-1] + 1 : 0 ;
                res[i][j] = Math.max(lens[i][j], Math.max(res[i][j-1], res[i-1][j]));
            }
        }

        return res[A.length][B.length];
    }

    public int findLength2(int[] A, int[] B) {
        int[] res = new int[B.length+1];
        int[] lens = new int[B.length+1];
        int lens0 = 0;

        for (int i=1; i<=A.length; i++) {
            for (int j=1; j<=B.length; j++) {
                int tempLens = lens[j];
                lens[j] = (A[i-1] == B[j-1]) ? lens0 + 1 : 0;
                lens0 = tempLens;
                res[j] = Math.max(lens[j], Math.max(res[j-1], res[j]));
            }
            lens0 = 0;
        }

        return res[B.length];
    }

    /**
     * https://leetcode.com/problems/maximum-length-of-repeated-subarray/solution/
     */
    public int findLength3(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    ans = Math.max(ans, memo[i][j]);
                }
            }
        }
        return ans;
    }

    /**
     * https://discuss.leetcode.com/topic/108760/java-c-clean-code-8-lines
     */
    public int findLength4(int[] a, int[] b) {
        int m = a.length, n = b.length;
        if (m == 0 || n == 0) return 0;
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                max = Math.max(max, dp[j] = a[i] == b[j] ? 1 + dp[j + 1] : 0);
        return max;
    }

}
