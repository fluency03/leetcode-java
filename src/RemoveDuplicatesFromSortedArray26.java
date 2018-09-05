/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.
 *
 */

public class RemoveDuplicatesFromSortedArray26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /**
     * https://discuss.leetcode.com/topic/17252/5-lines-c-java-nicer-loops
     */
    public int removeDuplicates2(int[] nums) {
        int i = nums.length > 0 ? 1 : 0;
        for (int n : nums)
            if (n > nums[i-1])
                nums[i++] = n;
        return i;
    }


    public int removeDuplicates3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int i = 0;
        int j = 0;
        while (j < len) {
            while (j > 0 && j < len && nums[j] == nums[j-1]) j++;
            if (j == len) break;
            nums[i++] = nums[j++];
        }
        return i;
    }

}
