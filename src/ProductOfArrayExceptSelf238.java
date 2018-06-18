/**
 * Given an array nums of n integers where n > 1,  return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * Note: Please solve it without division and in O(n).
 * 
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does
 * not count as extra space for the purpose of space complexity analysis.)
 */


public class ProductOfArrayExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        L[0] = 1;
        for (int i=1; i<nums.length; i++) L[i] = L[i-1] * nums[i-1];
        R[nums.length-1] = 1;
        for (int i=nums.length-2; i>=0; i--) R[i] = R[i+1] * nums[i+1];
        int[] res = new int[nums.length];
        for (int i=0; i<nums.length; i++) res[i] = L[i] * R[i];
        return res;
    }


    public int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length];
        
        int sum = 1;
        for (int i=0; i<nums.length; i++) {
            res[i] = sum;
            sum = sum * nums[i];
        }
        sum = 1;
        for (int i=nums.length-1; i>=0; i--) {
            res[i] = res[i] * sum;
            sum = sum * nums[i];
        }
        return res;
    }

}
