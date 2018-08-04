/**
 * Your are given an array of positive integers nums.
 * 
 * Count and print the number of (contiguous) subarrays where the product of
 * all the elements in the subarray is less than k.
 * 
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 *
 * Note that [10, 5, 2] is not included as the product of 100 is not
 * strictly less than k.
 * 
 * Note:
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 */

public class SubarrayProductLessThanK713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return 0;
        int left = 0;
        int right = 0;
        int prod = 1;
        int len = nums.length;
        int res = 0;
        while (right < len) {
            prod *= nums[right];
            while (prod >= k && left <= right) {
                prod /= nums[left++];
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }


    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return 0;
        int prod = 1;
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < nums.length) {
            prod *= nums[right];
            while (left <= right && prod >= k) {
                res += right - left;
                prod /= nums[left++];
            }
            right++;
        }
        while (left < nums.length) {
            res += right - left;
            left++;
        }
        return res;
    }

}
