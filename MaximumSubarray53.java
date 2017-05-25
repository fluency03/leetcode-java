/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 *
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 *
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 */

import java.util.Arrays;

public class MaximumSubarray53 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], Math.max(dp[i-1] + nums[i], nums[i-1] + nums[i]));
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dp.length; i++) {
            if(dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }



    public static void main(String[] args) {
        MaximumSubarray53 ms = new MaximumSubarray53();

        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 8}));
        System.out.println(ms.maxSubArray(new int[]{-2, -1}));
        System.out.println(ms.maxSubArray(new int[]{8, -19, 5, -4, 20}));
    }


}
