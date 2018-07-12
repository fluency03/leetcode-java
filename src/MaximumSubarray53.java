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

    /**
     * https://leetcode.com/problems/maximum-subarray/discuss/20210/O(n)-Java-solution
     */
    public int maxSubArray2(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0)
                sum = A[i];
            else
                sum += A[i];
            if (sum > max)
                max = sum;
        }
        return max;
    }


    // divide-and-conquer
    public int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length-1);
    }
    
    private int maxSubArray(int[] nums, int left, int right) {
        if (left > right) return Integer.MIN_VALUE;
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int L = maxSubArray(nums, left, mid-1);
        int R = maxSubArray(nums, mid+1, right);
        int leftSum = 0;
        int tmp = 0;
        for (int i=mid-1; i>=left; i--) {
            tmp += nums[i];
            if (tmp > leftSum) leftSum = tmp;
        }
        tmp = 0;
        int rightSum = 0;
        for (int i=mid+1; i<=right; i++) {
            tmp += nums[i];
            if (tmp > rightSum) rightSum = tmp;
        }
        return Math.max(Math.max(L, R), leftSum + rightSum + nums[mid]);
    }


    public static void main(String[] args) {
        MaximumSubarray53 ms = new MaximumSubarray53();

        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(ms.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 8}));
        System.out.println(ms.maxSubArray(new int[]{-2, -1}));
        System.out.println(ms.maxSubArray(new int[]{8, -19, 5, -4, 20}));
    }


}
