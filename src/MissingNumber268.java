/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * 
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 * 
 * Example 2:
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * 
 * Note:
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 */


public class MissingNumber268 {
    public int missingNumber(int[] nums) {
        int sum = 0;
        int N = nums.length;
        for (int n: nums) sum += n;
        return N * (N + 1) / 2 - sum;
    }


    /**
     * https://leetcode.com/problems/missing-number/solution/
     */
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

}
