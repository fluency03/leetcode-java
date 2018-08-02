/**
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays.
 * 
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 
 * Examples:
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * 
 * Output:
 * 18
 * 
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */

public class SplitArrayLargestSum410 {
    // DP 1
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int j=1; j<=n; j++) sum[j] += sum[j-1]  + nums[j-1];

        int[][] dp = new int[m][n];
        for (int j=0; j<n; j++) {
            dp[0][j] = sum[j+1];
        }
        for (int i=1; i<m; i++) {
            for (int j=i; j<n; j++) {
                int s = Integer.MAX_VALUE;
                for (int k=0; k<j; k++) {
                    s = Math.min(s, Math.max(dp[i-1][k], sum[j+1] - sum[k+1]));
                }
                dp[i][j] = s;
            }
        }
        return dp[m-1][n-1];
    }

    // DP 2
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int j=1; j<=n; j++) sum[j] += sum[j-1]  + nums[j-1];

        int[] dp = new int[n];
        for (int j=0; j<n; j++) {
            dp[j] = sum[j+1];
        }
        for (int i=1; i<m; i++) {
            int[] tmp = new int[n];
            for (int j=0; j<n; j++) tmp[j] = dp[j];
            for (int j=i; j<n; j++) {
                int s = Integer.MAX_VALUE;
                for (int k=0; k<j; k++) {
                    s = Math.min(s, Math.max(tmp[k], sum[j+1] - sum[k+1]));
                }
                dp[j] = s;
            }
        }
        return dp[n-1];
    }

    // DP 3
    public int splitArray3(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int j=1; j<=n; j++) sum[j] += sum[j-1]  + nums[j-1];

        int[][] dp = new int[2][n];
        for (int j=0; j<n; j++) {
            dp[0][j] = sum[j+1];
        }
        for (int i=1; i<m; i++) {
            for (int j=i; j<n; j++) {
                int s = Integer.MAX_VALUE;
                for (int k=0; k<j; k++) {
                    s = Math.min(s, Math.max(dp[(i+1)%2][k], sum[j+1] - sum[k+1]));
                }
                dp[i%2][j] = s;
            }
        }
        return dp[(m+1)%2][n-1];
    }


    // Binary Search on Value Range
    public int splitArray4(int[] nums, int m) {
        int n = nums.length;
        long l = Long.MIN_VALUE;
        long r = 0;
        for (int j=0; j<n; j++) {
            l = Math.max(l, nums[j]);
            r += nums[j];
        }
        while (l < r) {
            long mid = (l + r) / 2;
            int cnt = count(nums, m, mid);
            if (cnt > m) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return (int) l;
    }

    private int count(int[] nums, int m, long mid) {
        int res = 0;
        long sum = 0;
        for (int n: nums) {
            sum += n;
            if (sum > mid) {
                res++;
                sum = n;
            }
        }
        return res+1;
    }

}
