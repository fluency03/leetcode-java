/**
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 *
 */


public class MinimumSizeSubarraySum209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0 || s == 0) return 0;

        int slow = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int fast=0; fast<nums.length; fast++) {
            sum += nums[fast];
            while (sum >= s && slow < nums.length) {
                min = Math.min(min, fast-slow+1);
                sum -= nums[slow];
                slow++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
