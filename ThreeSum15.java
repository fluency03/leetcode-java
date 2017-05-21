/**
 * Given an array S of n integers, are there elements a, b, c in S such that
 * a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;



public class ThreeSum15 {

    /**
     * https://discuss.leetcode.com/topic/8125/concise-o-n-2-java-solution
     */

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int low = i + 1;
                int high = nums.length - 1;
                int sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) {
                          low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                          high--;
                        }
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low++;
                    }
                    else {
                        high--;
                    }
               }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum15 ts = new ThreeSum15();

        System.out.println(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(ts.threeSum(new int[]{0, 0, 0}));
        System.out.println(ts.threeSum(new int[]{0, 0, 0, 0}));
        System.out.println(ts.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
        System.out.println(ts.threeSum(new int[]{0, 0, 0, 1, 1}));
        System.out.println(ts.threeSum(new int[]{-1, -1, 0, 0, 0}));
    }

}
