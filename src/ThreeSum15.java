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
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length-1] < 0) return res;

        for (int i=0; i<nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            int l = i+1;
            int r = nums.length-1;
            int left = 0 - nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == left) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                } else if (nums[l] + nums[r] > left) {
                    r--;
                } else {
                    l++;
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
