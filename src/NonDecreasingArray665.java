/**
 * Given an array with n integers, your task is to check if it could become
 * non-decreasing by modifying at most 1 element.
 * 
 * We define an array is non-decreasing if array[i] <= array[i + 1] holds for
 * every i (1 <= i < n).
 * 
 * Example 1:
 * Input: [4,2,3]
 * Output: True
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * 
 * Example 2:
 * Input: [4,2,1]
 * Output: False
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 * 
 * Note: The n belongs to [1, 10,000].
 */

public class NonDecreasingArray665 {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 2) return true;
        int min = nums[nums.length-1];
        int n1 = 0;
        for (int i=nums.length-2; i>=0; i--) {
            if (min < nums[i]) {
                n1++;
                if (n1 == 2) break;
            } else {
                min = nums[i];
            }
        }
        if (n1 < 2) return true;
        
        int max = nums[0];
        int n2 = 0;
        for (int i=1; i<nums.length; i++) {
            if (max > nums[i]) {
                n2++;
                if (n2 == 2) return false;
            } else {
                max = nums[i];
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/non-decreasing-array/discuss/106849/C++-Java-Clean-Code-6-liner-Without-Modifying-Input
     */
    public boolean checkPossibility2(int[] a) {
        int modified = 0;
        for (int i = 1, prev = a[0]; i < a.length; i++) {
            if (a[i] < prev) {
                if (modified++ > 0) return false;
                if (i - 2 >= 0 && a[i - 2] > a[i]) continue;
            }
            prev = a[i];
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/non-decreasing-array/solution/
     */
    public boolean checkPossibility3(int[] a) {
        int p = -1;
        for (int i = 0; i<a.length-1; i++) {
            if (a[i] > a[i+1]) {
                if (p != -1) return false;
                p = i;
            }
        }
        return (p == -1 || p == 0 || p == a.length-2 || a[p-1] <= a[p+1] || a[p] <= a[p+2]);
    }

}
