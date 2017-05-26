/**
 * Given an array of integers, return indices of the two numbers such that
 * they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and
 * you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */


import java.util.HashMap;
import java.util.Map;


public class TwoSum1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> foundSet = new HashMap<>();

        int[] returns = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer now = nums[i];
            Integer lookingfor = target - now;
            if (foundSet.containsKey(lookingfor)) {
                returns[0] = foundSet.get(lookingfor);
                returns[1] = i;
                return returns;
            } else {
                foundSet.put(now, i);
            }
        }
        return returns;
    }

    public static void main(String[] args) {
        TwoSum1 ts = new TwoSum1();

        System.out.println(ts.twoSum(new int[]{2, 7, 11, 15}, 9));
        System.out.println(ts.twoSum(new int[]{1, 2, 3}, 5));
    }

}
