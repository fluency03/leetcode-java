/**
 * Remember the story of Little Match Girl? By now, you know exactly what
 * matchsticks the little match girl has, please find out a way you can make
 * one square by using up all those matchsticks. You should not break any
 * stick, but you can link them up, and each matchstick must be used exactly
 * one time.
 * 
 * Your input will be several matchsticks the girl has, represented with their
 * stick length. Your output will either be true or false, to represent whether
 * you could make one square using all the matchsticks the little match girl
 * has.
 * 
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square
 * came two sticks with length 1.
 * 
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * 
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 */

public class MatchsticksToSquare473 {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length <= 3) return false;
        return canPartitionKSubsets(nums, 4);
    }

    // PartitionToKEqualSumSubsets698
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

}
