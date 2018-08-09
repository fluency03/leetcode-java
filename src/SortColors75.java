/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 *
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
 * array with total number of 0's, then 1's and followed by 2's.
 *
 * Could you come up with an one-pass algorithm using only constant space?
 */


import java.util.Arrays;


public class SortColors75 {
    public void sortColors(int[] nums) {
        int L = nums.length;
        if (L == 0 || L == 1) {
            return;
        }

        helper(0, L - 1, nums);
    }

    private void helper(int left, int right, int[] nums) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        helper(left, mid, nums);
        helper(mid + 1, right, nums);
        merge(left, mid, right, nums);
    }

    private void merge(int left, int mid, int right, int[] nums) {
        int l = 0;
        int r = 0;
        int i = left;
        int[] la = Arrays.copyOfRange(nums, left, mid+1);
        int[] ra = Arrays.copyOfRange(nums, mid+1, right+1);
        while (l <= mid - left && r <= right - mid - 1) {
            if (la[l] <= ra[r]) {
                nums[i] = la[l];
                l++;
            } else {
                nums[i] = ra[r];
                r++;
            }
            i++;
        }
        while (l <= mid - left) {
            nums[i] = la[l];
            l++;
            i++;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/6968/four-different-solutions
     */
    // two pass O(m+n) space
    public void sortColors2(int A[]) {
        int num0 = 0, num1 = 0, num2 = 0;

        for(int i = 0; i < A.length; i++) {
            if (A[i] == 0) ++num0;
            else if (A[i] == 1) ++num1;
            else if (A[i] == 2) ++num2;
        }

        for(int i = 0; i < num0; ++i) A[i] = 0;
        for(int i = 0; i < num1; ++i) A[num0+i] = 1;
        for(int i = 0; i < num2; ++i) A[num0+num1+i] = 2;
    }

    // one pass in place solution
    public void sortColors3(int A[]) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == 0)
            {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            }
            else if (A[i] == 1)
            {
                A[++n2] = 2; A[++n1] = 1;
            }
            else if (A[i] == 2)
            {
                A[++n2] = 2;
            }
        }
    }

    // one pass in place solution
    public void sortColors4(int A[]) {
        int j = 0, k = A.length - 1;
        for (int i = 0; i <= k; ++i){
            if (A[i] == 0 && i != j)
                swap(A, i--, j++);
            else if (A[i] == 2 && i != k)
                swap(A, i--, k--);
        }
    }


    // one pass in place solution
    public void sortColors5(int A[], int n) {
        int j = 0, k = n-1;
        for (int i=0; i <= k; i++) {
            if (A[i] == 0)
                swap(A, i, j++);
            else if (A[i] == 2)
                swap(A, i--, k--);
        }
    }

    // bucket sort
    public void sortColors6(int[] nums) {
        int[] buckets = new int[3];

        for (int i=0; i<nums.length; i++) {
            buckets[nums[i]]++;
        }

        int idx = 0;
        for (int i=0; i<3; i++) {
            int count = buckets[i];
            for (int j=0; j<count; j++) {
                nums[idx++] = i;
            }
        }

    }

    public void sortColors7(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int left = 0;
        int right = nums.length - 1;
        int i = left;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i++, left++);
            } else if (nums[i] == 2) {
                swap(nums, i, right--);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        SortColors75 sc = new SortColors75();

        int[] nums = new int[]{
            5, 8, 1, 3, 7, 2, 9, 4, 1, 1, 0
        };

        System.out.println(Arrays.toString(nums));
        sc.sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{
            1, 1, 0, 2, 0, 1, 2, 0, 1, 1, 1, 2
        };

        System.out.println(Arrays.toString(nums));
        sc.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
