/**
 * Given an array of integers, every element appears twice except for one.
 * Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 */

public class SingleNumber136 {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++) {
            int n = nums[i];
            if ((i > 0 && n == nums[i-1]) || (i < nums.length-1 && n == nums[i+1])) continue;
            return nums[i];
        }
        return -1;
    }

    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return set.iterator().next();
    }

    /**
     * https://leetcode.com/problems/single-number/discuss/42997/My-O(n)-solution-using-XOR
     */
    public int singleNumber3(int[] nums) {
        int result = 0;
        for (int i = 0; i<nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

}
