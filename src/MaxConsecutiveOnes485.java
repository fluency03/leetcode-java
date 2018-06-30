/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * 
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * 
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *     The maximum number of consecutive 1s is 3.
 * 
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */

public class MaxConsecutiveOnes485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                right++;
                continue;
            }
            max = Math.max(max, right-left);
            left = right;
            while (left < nums.length && nums[left] == 0) left++;
            right = left;
        }
        max = Math.max(max, right-left);
        return max;
    }


    /**
     * https://leetcode.com/problems/max-consecutive-ones/discuss/96693/Java-4-lines-concise-solution-with-explanation
     */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int maxHere = 0, max = 0;
        for (int n : nums)
            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
        return max; 
    }

}
