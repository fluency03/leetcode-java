/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * 
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

public class MaximumProductSubarray152 {
    // time O(n), space O(n)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        boolean hasZero = false;
        int[] pos = new int[len+1];
        int[] neg = new int[len+1];
        int res = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int now = nums[i];
            if (now > 0) {
                pos[i+1] = pos[i] == 0 ? now : pos[i] * now;
                neg[i+1] = neg[i] == 0 ? 0 : neg[i] * now;
            } else if (now < 0) {
                pos[i+1] = neg[i] == 0 ? 0 : neg[i] * now;
                neg[i+1] = pos[i] == 0 ? now : pos[i] * now;
            } else {
                hasZero = true;
            }
            if (pos[i+1] != 0 && pos[i+1] > res) {
                res = pos[i+1];
            }
            if (neg[i+1] != 0 && neg[i+1] > res) {
                res = neg[i+1];
            }
        }
        return (hasZero && res < 0) ? 0 : res;
    }

    // time O(n), space O(1)
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        boolean hasZero = false;
        int pos = 0;
        int neg = 0;
        int res = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int now = nums[i];
            int oldPos = pos;
            int oldNeg = neg;
            if (now > 0) {
                pos = oldPos == 0 ? now : oldPos * now;
                neg = oldNeg == 0 ? 0 : oldNeg * now;
            } else if (now < 0) {
                pos = oldNeg == 0 ? 0 : oldNeg * now;
                neg = oldPos == 0 ? now : oldPos * now;
            } else {
                hasZero = true;
                pos = 0;
                neg = 0;
            }
            if (pos != 0 && pos > res) {
                res = pos;
            }
            if (neg != 0 && neg > res) {
                res = neg;
            }
        }
        return (hasZero && res < 0) ? 0 : res;
    }


    /**
     * https://leetcode.com/problems/maximum-product-subarray/discuss/48330/Simple-Java-code
     */
    public int maxProduct3(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
     */
    public int maxProduct4(int A[]) {
        // store the result that is the max we have found so far
        int r = A[0];
    
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < A.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0) {
                int t = imax;
                imax = imin;
                imin = t;
            }
    
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);
    
            // the newly computed max value is a candidate for our global result
            r = Math.max(r, imax);
        }
        return r;
    }

}
