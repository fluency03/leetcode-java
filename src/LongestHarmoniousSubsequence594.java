/**
 * We define a harmonious array is an array where the difference between its
 * maximum value and its minimum value is exactly 1.
 * 
 * Now, given an integer array, you need to find the length of its longest
 * harmonious subsequence among all its possible subsequences.
 * 
 * Subsequences: https://en.wikipedia.org/wiki/Subsequence
 * 
 * Example 1:
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * 
 * Note: The length of the input array will not exceed 20,000.
 */

public class LongestHarmoniousSubsequence594 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
            int b = map.get(n);
            if (map.containsKey(n-1)) {
                int a = map.get(n-1);
                if (a + b > res) res = a + b;
            }
            if (map.containsKey(n+1)) {
                int a = map.get(n+1);
                if (a + b > res) res = a + b;
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-harmonious-subsequence/solution/
     */
    public int findLHS2(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.containsKey(key + 1))
                res = Math.max(res, map.get(key) + map.get(key + 1));
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/longest-harmonious-subsequence/solution/
     */
    public int findLHS3(int[] nums) {
        Arrays.sort(nums);
        int prev_count = 1, res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            if (i > 0 && nums[i] - nums[i - 1] == 1) {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                res = Math.max(res, count + prev_count);
                prev_count = count;
            } else {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                prev_count = count;
            }
        }
        return res;
    }


    public int findLHS4(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int N = nums.length;
        int res = 0;
        int preCount = -1;
        int pre = 0;
        int count = 1;
        int curr = nums[0];
        int i = 1;
        while (i < N && nums[i] == curr) {
            count++;
            i++;
        }
        while (i < N) {
            if (nums[i] == curr) {
                count++;
            } else {
                if (preCount != -1 && curr == pre + 1 && (count + preCount) > res) {
                    res = count + preCount;
                }
                preCount = count;
                pre = curr;
                count = 1;
                curr = nums[i];
            }
            i++;
        }
        if (preCount != -1 && curr == pre + 1 && (count + preCount) > res) {
            res = count + preCount;
        }
        return res;
    }

}
