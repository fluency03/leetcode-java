/**
 * Note: This is an extension of House Robber.
 *
 * After robbing those houses on that street, the thief has found himself a
 * new place for his thievery so that he will not get too much attention. This
 * time, all houses at this place are arranged in a circle. That means the
 * first house is the neighbor of the last one. Meanwhile, the security system
 * for these houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 */


public class HouseRobberII213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int pre0 = 0;
        int now0 = 0;
        int pre1 = 0;
        int now1 = 0;
        for (int i=1; i<nums.length; i++) {
            int n0 = Math.max(now0, pre0+nums[i-1]);
            pre0 = now0;
            now0 = n0;
            int n1 = Math.max(now1, pre1+nums[i]);
            pre1 = now1;
            now1 = n1;
        }
        return Math.max(now0, now1);
    }

    /**
     * https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }


    public int rob3(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        if (N == 1) return nums[0];
        return Math.max(rob3(nums, 0, N-2), rob3(nums, 1, N-1));
    }

    public int rob3(int[] nums, int i, int j) {
        if (i == j) return nums[i];
        int K = j - i + 1;
        int pre0 = 0;
        int pre1 = nums[i];
        for (int k=2; k<=K; k++) {
            int curr = Math.max(pre1, pre0 + nums[i+k-1]);
            pre0 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

}
