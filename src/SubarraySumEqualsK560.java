/**
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the
 * integer k is [-1e7, 1e7].
 *
 */


public class SubarraySumEqualsK560 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        int[] dp = new int[n];
        for (int j=0; j<n; j++) {
            for (int i=j; i>=0; i--) {
                int curr = (i == j) ? nums[j] : dp[i] + nums[j];
                dp[i] = curr;
                if (curr == k) res++;
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/solution/
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }


    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/solution/
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }


    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/solution/
     */
    public int subarraySum4(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    public int subarraySum5(int[] nums, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                map.put(sum, new HashSet<>());
            }
            map.get(sum).add(i);
        }
        
        int res = 0;
        sum = 0;
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(sum + k)) {
                for (int idx: map.get(sum + k)) {
                    if (idx >= i) res++;
                }
            }
            sum += nums[i];
        }
        return res;
    }

}
