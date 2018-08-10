/**
 * Given an unsorted array of integers, find the length of longest continuous
 * increasing subsequence (subarray).
 * 
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its
 * length is 3. 
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a
 * continuous one where 5 and 7 are separated by 4. 
 * 
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its
 * length is 1. 
 * 
 * Note: Length of the array will not exceed 10,000.
 */

public class LongestContinuousIncreasingSubsequence674 {
    public int findLengthOfLCIS(int[] nums) {
        int len = 0;
        int res = 0;
        if (nums == null || nums.length == 0) return res; 
        int pre = nums[0]-1;
        for (int n: nums) {
            if (n > pre) {
                len++;
            } else {
                res = Math.max(res, len);
                len = 1;
            }
            pre = n;
        }
        return Math.max(res, len);
    }


    public int findLengthOfLCIS2(int[] nums) {
        int count = 0;
        int res = 0;
        for (int i=0; i<nums.length; i++) {
            if (i == 0) {
                count++;
            } else {
                if (nums[i] > nums[i-1]) {
                    count++;
                } else {
                    count = 1;
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }

}
