/**
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies:
 * Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */

public class LargestDivisibleSubset368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        int[] dp = new int[N + 1];
        int[] track = new int[N + 1];
        int maxIdx = 1;
        int maxSize = 0;
        for (int i=1; i<=N; i++) {
            dp[i] = 1;
            for (int j=1; j<i; j++) {
                if (nums[i-1] % nums[j-1] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        track[i] = j;

                    }
                }
            }
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxIdx = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        int i = maxIdx;
        while (i != 0) {
            res.add(nums[i-1]);
            i = track[i];
        }
        return res;
    }

}
