/**
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 *
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 *
 */


public class MinimumSizeSubarraySum209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0 || s == 0) return 0;

        int slow = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int fast=0; fast<nums.length; fast++) {
            sum += nums[fast];
            while (sum >= s && slow < nums.length) {
                min = Math.min(min, fast-slow+1);
                sum -= nums[slow];
                slow++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= s) {
                if (right - left < minLen) {
                    minLen = right - left;
                }
                sum -= nums[left++];
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    public int minSubArrayLen3(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int lo = 1;
        int hi = nums.length;
        int minLen = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isValid(nums, mid, s)) {
                if (mid < minLen) minLen = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private boolean isValid(int[] nums, int len, int s) {
        int sum = 0;
        for (int i=0; i<len; i++) {
            sum += nums[i];
            if (sum >= s) return true;
        }
        for (int i=len; i<nums.length; i++) {
            sum -= nums[i-len];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }


}
