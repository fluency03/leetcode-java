/**
 * Given an unsorted array nums, reorder it in-place such that
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * 
 * Example:
 * 
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 */

public class WiggleSort280 {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int len = nums.length;
        int i = 0;
        while (i < len - 1) {
            if ((i % 2 == 0 && nums[i] > nums[i+1]) || (i % 2 == 1 && nums[i] < nums[i+1])) swap(nums, i, i+1);
            i++;
        }
    }
    

    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        Arrays.sort(nums);
        int len = nums.length;
        int mid = len / 2;
        if (mid % 2 == 1) mid++;
        int left = 1;
        int right = mid;
        while (right < len) {
            swap(nums, left, right);
            left += 2;
            right += 2;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
