/**
 * Given an array of non-negative integers, you are initially positioned at
 * the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Note:
 * You can assume that you can always reach the last index.
 */

public class JumpGameII45 {
    public int jump(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=0; i<N-1; i++) {
            int farest = Math.min(i+nums[i], N-1);
            for (int j=i+1; j<=farest; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[N-1];
    }


    /**
     * https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
     */
    public int jump2(int[] A) {
      int jumps = 0, curEnd = 0, curFarthest = 0;
      for (int i = 0; i < A.length - 1; i++) {
        curFarthest = Math.max(curFarthest, i + A[i]);
        if (i == curEnd) {
          jumps++;
          curEnd = curFarthest;
        }
      }
      return jumps;
    }

}
