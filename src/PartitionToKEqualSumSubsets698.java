/**
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal.
 * 
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets
 * (5), (1, 4), (2,3), (2,3) with equal sums.
 * 
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */

public class PartitionToKEqualSumSubsets698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int N = nums.length;
        for (int i=0; i<N; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        boolean[] visited = new boolean[N];
        return valid(nums, visited, 0, 0, target, k, N);
    }

    private boolean valid(int[] nums, boolean[] visited, int start, int curr, int target, int k, int N) {
        if (k == 1) return true;
        if (curr == target) return valid(nums, visited, 0, 0, target, k-1, N);
        for (int i=start; i<N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (valid(nums, visited, i, curr + nums[i], target, k, N)) return true;
            visited[i] = false;
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solution/
     */
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];

        for (int state = 0; state < (1 << N); state++) {
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
    }


    /**
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solution/
     */
    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }

    public boolean canPartitionKSubsets3(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

}
