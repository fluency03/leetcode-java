/**
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 *
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length
 * is 4. Note that there may be more than one LIS combination, it is only
 * necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

public class LongestIncreasingSubsequence300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        int max = 1;
        for (int i=1; i<=n; i++) {
            int c = 1;
            for (int j=0; j<i-1; j++) {
                if (nums[i-1] > nums[j]) {
                    c = Math.max(c, dp[j+1]+1);
                }
            }
            dp[i] = c;
            max = Math.max(max, c);
        }
        return max;
    }


    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/solution/
     */
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

}
