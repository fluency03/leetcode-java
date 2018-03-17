/**
 * Given an array S of n integers, find three integers in S such that the sum
 * is closest to a given number, target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


public class ThreeSumClosest16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        Integer sumClosest = Integer.MAX_VALUE;
        Integer diffClosest = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int tempSum  = nums[i] + nums[low] + nums[high];
                    if (tempSum == target) {
                        return tempSum;
                    }

                    boolean update = diffClosest > Math.abs(tempSum - target);
                    sumClosest = update ? tempSum : sumClosest;
                    diffClosest = update ? Math.abs(tempSum - target) : diffClosest;

                    if (tempSum < target) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return sumClosest;
    }

    /**
     * https://discuss.leetcode.com/topic/5192/java-solution-with-o-n2-for-reference/19
     */
    public int threeSumClosest2(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i-1]) continue;
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        ThreeSumClosest16 tsc = new ThreeSumClosest16();

        System.out.println(tsc.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(tsc.threeSumClosest(new int[]{-1, 0, 1, 1, 55}, 3));
    }

}
