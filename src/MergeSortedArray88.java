/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 *
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to
 * m + n) to hold additional elements from nums2. The number of elements
 * initialized in nums1 and nums2 are m and n respectively.
 *
 */


public class MergeSortedArray88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        int i = m-1;
        int j = n-1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[i+j+1] = nums1[i];
                i--;
            } else {
                nums1[i+j+1] = nums2[j];
                j--;
            }
        }

        while (j >= 0) {
            nums1[j] = nums2[j];
            j--;
        }
    }


    /**
     * https://leetcode.com/problems/merge-sorted-array/discuss/29505/1-Line-Solution
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) nums1[m+n-1] = (m == 0 || nums2[n-1] > nums1[m-1]) ? nums2[--n] : nums1[--m];
    }

}
