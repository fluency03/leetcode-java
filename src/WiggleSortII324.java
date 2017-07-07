/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */


public class WiggleSortII324 {
    /**
     * https://www.hrwhisper.me/leetcode-wiggle-sort-ii/
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int s = (nums.length + 1) >> 1, t = nums.length;
        for (int i = 0; i < nums.length; i++) {
            temp[i] = (i & 1) == 0 ?  nums[--s] : nums[--t] ;
        }

        for (int i = 0; i < nums.length; i++)
            nums[i] = temp[i];
    }


    /**
     * https://www.hrwhisper.me/leetcode-wiggle-sort-ii/
     */
    public void wiggleSort2(int[] nums) {
        int medium = findMedium(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
        int s = 0, t = nums.length - 1 , mid_index = (nums.length + 1) >> 1;
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < medium)
                temp[s++] = nums[i];
            else if (nums[i] > medium)
                temp[t--] = nums[i];
        }

        while (s < mid_index) temp[s++] = medium;
        while (t >= mid_index) temp[t--] = medium;

        t = nums.length;
        for (int i = 0; i < nums.length; i++)
            nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
    }

    private int findMedium(int[] nums, int L, int R, int k) {
        if (L >= R) return nums[R];
        int i = partition(nums, L, R);
        int cnt = i - L + 1;
        if (cnt == k) return nums[i];
        return cnt > k ? findMedium(nums, L, i - 1, k) : findMedium(nums, i + 1, R, k - cnt);
    }

    private int partition(int[] nums, int L, int R) {
        int val = nums[L];
        int i = L, j = R + 1;
        while (true) {
            while (++i < R && nums[i] < val) ;
            while (--j > L && nums[j] > val) ;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, L, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }






}
