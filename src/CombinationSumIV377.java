/**
 * Given an integer array with all positive numbers and no duplicates, find the
 * number of possible combinations that add up to a positive integer target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * 
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */


public class CombinationSumIV377 {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || target < 0) return 0;
        int N = nums.length;
        int[][] dp = new int[N + 1][target + 1];
        for (int i=0; i<=N; i++) {
            dp[i][0] = 1;
        }
        for (int i=1; i<=N; i++) {
            int n = nums[i-1];
            for (int j=1; j<=target; j++) {
                int local = 0;
                for (int k=1; k<i; k++) {
                    local += j < nums[k-1] ? 0 : (dp[i][j - nums[k-1]] - dp[i-1][j - nums[k-1]]);
                }
                dp[i][j] = local + (j < n ? 0 : dp[i][j - n]) + dp[i-1][j];
            }
        }
        return dp[N][target];
    }


    /**
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     */
    private int[] dp;
    public int combinationSum42(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }
    
    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }


    public int combinationSum43(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n: nums) {
                if (i - n >= 0) {
                    comb[i] += comb[i - n];
                }
            }
        }
        return comb[target];
    }

}
