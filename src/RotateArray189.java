/**
 * Rotate an array of n elements to the right by k steps.
 *
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated
 * to [5,6,7,1,2,3,4].
 *
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different
 * ways to solve this problem.
 *
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * Related problem: Reverse Words in a String II
 *
 */


public class RotateArray189 {
    public void rotate(int[] nums, int k) {
        int kk = k % nums.length;
        if (kk == 0) return;

        boolean[] visited = new boolean[nums.length];
        for (int i=0; i<nums.length; i++) {
            if (visited[i]) continue;
            int j = i;
            int r = nums[j];
            while (true) {
                int nextIndex = (j+k) % nums.length;
                int temp = nums[nextIndex];
                nums[nextIndex] = r;
                visited[nextIndex] = true;
                r = temp;
                j = nextIndex;
                if (j == i) break;
            }
        }
    }

    /**
     * https://leetcode.com/problems/rotate-array/solution/
     */
    public void rotate2(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    /**
     * https://leetcode.com/problems/rotate-array/solution/
     */
    public void rotate3(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * https://leetcode.com/problems/rotate-array/solution/
     */
    public void rotate4(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    public void rotate5(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        int len = nums.length;
        
        k = k % len;
        if (k == 0) return;        
        int count = 0;
        for (int i=0; i<k && count < len; i++) {
            count += cipher(nums, k, i, len);
        }
    }
    
    private int cipher(int[] nums, int k, int start, int len) {
        int res = 0;
        int i = start;
        int pre = nums[i];
        do {
            i = (i + k) % len;
            int old = nums[i];
            nums[i] = pre;
            pre = old;
            res++;
        } while (i != start);
        return res;
    }
    

}
