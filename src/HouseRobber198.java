/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping
 * you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 *
 */

public class HouseRobber198 {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i=1; i<nums.length; i++) {
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i]);
        }

        return dp[nums.length];
    }


    public int rob2(int[] nums) {
        int pre = 0;
        int now = 0;
        for (int i=0; i<nums.length; i++) {
            int n = Math.max(now, pre+nums[i]);
            pre = now;
            now = n;
        }

        return now;
    }

}
