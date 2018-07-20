/**
 * Given an array nums and a target value k, find the maximum length of a
 * subarray that sums to k. If there isn't one, return 0 instead.
 *
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit
 * signed integer range.
 *
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 *
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 *
 * Follow Up:
 * Can you do it in O(n) time?
 *
 */


public class MaximumSizeSubarraySumEqualsK325 {
    // brute force
    public int maxSubArrayLen(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int sum = 0;
            for (int j=i; j<nums.length; j++) {
                sum += nums[j];
                if (sum == k) max = Math.max(max, j-i+1);
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }


    /**
     * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/discuss/77784/O(n)-super-clean-9-line-Java-solution-with-HashMap
     */
    public int maxSubArrayLen2(int[] nums, int k) {
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum-k)) {
                max = Math.max(max, i-map.get(sum-k));
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }

        return max;
    }


    public int maxSubArrayLen3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0; 

        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int res = 0;
        int sum = 0;
        map.put(0, -1);
        for (int i=0; i<len; i++) {
            sum += nums[i];
            int remain = sum - k;
            if (map.containsKey(remain)) {
                int idx = map.get(remain);
                if (i - idx > res) res = i - idx;
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }

        return res;
    }

}
