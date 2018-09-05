/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * Note:
 *      Each element in the result must be unique.
 *      The result can be in any order.
 */

import java.util.Set;
import java.util.HashSet;

public class IntersectionOfTwoArrays349 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
            nums2 == null || nums2.length == 0) return new int[]{};

        Set<Integer> setNums1 = new HashSet<>();
        for (int i1: nums1) {
            setNums1.add(i1);
        }

        Set<Integer> resSet = new HashSet<>();
        for (int i2: nums2) {
            if (setNums1.contains(i2)) {
                resSet.add(i2);
            }
        }

        int[] res = new int[resSet.size()];
        int i = 0;
        for (Integer resInt: resSet) {
            res[i++] = (int) resInt;
        }

        return res;
    }


    /**
     * https://leetcode.com/problems/intersection-of-two-arrays/discuss/81969/Three-Java-Solutions
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

}
