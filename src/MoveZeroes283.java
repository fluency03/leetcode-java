/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function,
 * nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 */

public class MoveZeroes283 {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }

    public void moveZeroes2(int[] nums) {
        int p = 0;
        int zeros = -1;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            } else {
                nums[p] = nums[i];
                p++;
            }

        }
        while (zeros >=0) {
            nums[nums.length-1-zeros] = 0;
            zeros--;
        }
    }

    public void moveZeroes3(int[] nums) {
        for (int i = 0, p = 0; p < nums.length; p++) {
            if (nums[p] != 0) {
                swap(nums, i++, p);
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
