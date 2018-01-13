
/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */


public class NextPermutation31 {
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        while (n > 0) {
            if (nums[n-1] < nums[n]) break;
            n--;
        }
        if (n == 0) {
            Arrays.sort(nums);
            return;
        }

        int p = nums.length - 1;
        int minIdx = nums.length - 1;
        int minVal = Integer.MAX_VALUE;
        while (p >= n) {
            if (nums[p] > nums[n-1]) break;
            p--;
        }
        swap(nums, n-1, p);

        int l = n;
        int r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
