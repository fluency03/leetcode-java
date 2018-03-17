/**
 * In a given array nums of positive integers, find three non-overlapping
 * subarrays with maximum sum.
 *
 * Each subarray will be of size k, and we want to maximize the sum of all
 * 3*k entries.
 *
 * Return the result as a list of indices representing the starting position of
 * each interval (0-indexed). If there are multiple answers, return the
 * lexicographically smallest one.
 *
 * Example:
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting
 * indices [0, 3, 5].
 *
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be
 * lexicographically larger.
 *
 * Note:
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 *
 */


public class MaximumSumOf3NonOverlappingSubarrays689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        for (int i=1; i<=nums.length; i++) sums[i] = sums[i-1] + nums[i-1];

        int[][] dp = new int[4][nums.length+1];
        boolean[][] choose = new boolean[3][nums.length];
        for (int i=1; i<=3; i++) {
            int start = i * k;
            for (int j=start; j<=nums.length; j++) {
                int pre = dp[i][j-1];
                int cur = dp[i-1][j-k] + sums[j] - sums[j-k];
                dp[i][j] = Math.max(cur, pre);
                if (cur > pre) choose[i-1][j-1] = true;
            }
        }

        int[] res = new int[3];
        int i = 2;
        int j = nums.length-1;
        while (i >= 0) {
            while (choose[i][j] == false) j--;
            res[i] = j-k+1;
            i--;
            j = j-k;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/
     */
    public int[] maxSumOfThreeSubarrays2(int[] nums, int K) {
        //W is an array of sums of windows
        int[] W = new int[nums.length - K + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= K) sum -= nums[i-K];
            if (i >= K-1) W[i-K+1] = sum;
        }

        int[] left = new int[W.length];
        int best = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[best]) best = i;
            left[i] = best;
        }

        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) {
            if (W[i] >= W[best]) best = i;
            right[i] = best;
        }

        int[] ans = new int[]{-1, -1, -1};
        for (int j = K; j < W.length - K; j++) {
            int i = left[j - K], k = right[j + K];
            if (ans[0] == -1 || W[i] + W[j] + W[k] >
                    W[ans[0]] + W[ans[1]] + W[ans[2]]) {

                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }
        return ans;
    }

}
