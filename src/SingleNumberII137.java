/**
 * Given an array of integers, every element appears three times except for
 * one, which appears exactly once. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 */

public class SingleNumberII137 {
    /**
     * https://leetcode.com/problems/single-number-ii/discuss/43294/Challenge-me-thx
     * https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
     */
    public int singleNumber(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }

    /**
     * https://leetcode.com/problems/single-number-ii/discuss/43363/JAVA-Easy-Version-To-UnderStand!!!!!!!!!!!!!!!!!!!
     */
    public int singleNumber2(int[] nums) {
        int len = nums.length, result = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                sum += (nums[j] >> i) & 1;
            }
            result |= (sum % 3) << i;
        }
        return result;
    }

}
