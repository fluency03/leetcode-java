/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 *
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 *
 */

public class RemoveDuplicatesFromSortedArrayII80 {
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int i = 0;
        for (int j=1; j<nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
                count = 1;
            } else if (count < 2)  {
                count++;
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27987/Short-and-Simple-Java-solution-(easy-to-understand)
     */
    public int removeDuplicates2(int[] nums) {
       int i = 0;
       for (int n : nums)
          if (i < 2 || n > nums[i - 2])
             nums[i++] = n;
       return i;
    }

    // private int removeDuplicates(int[] nums, int k) {
    //     int i = 0;
    //     for (int n : nums)
    //         if (i < k || n > nums[i-k])
    //             nums[i++] = n;
    //     return i;
    // }

}
