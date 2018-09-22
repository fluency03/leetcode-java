/**
 * Given an array of scores that are non-negative integers. Player 1 picks one
 * of the numbers from either end of the array followed by the player 2 and
 * then player 1 and so on. Each time a player picks a number, that number will
 * not be available for the next player. This continues until all the scores
 * have been chosen. The player with the maximum score wins.
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can
 * assume each player plays to maximize his score.
 * 
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2. 
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If
 * player 2 chooses 5, then player 1 will be left with 1 (or 2). 
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
 * Hence, player 1 will never be the winner and you need to return False.
 * 
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between
 * 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to
 * return True representing player1 can win.
 * 
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed
 * 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 */

public class PredictTheWinner486 {
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length <= 1) return true;
        int sum = 0;
        for (int n: nums) sum += n;
        int player1 = helper(nums, 0, 0, nums.length - 1, true);
        return player1 >= (sum - player1);
    }

    private int helper(int[] nums, int p1, int left, int right, boolean turn1) {
        if (left == right) return p1 + (turn1 ? nums[left] : 0);
        if (turn1) {
            p1 += nums[left];
            int a = helper(nums, p1, left+1, right, false);
            p1 -= nums[left];
            p1 += nums[right];
            int b = helper(nums, p1, left, right-1, false);
            p1 -= nums[right];
            return Math.max(a, b);
        } else {
            int a = helper(nums, p1, left+1, right, true);
            int b = helper(nums, p1, left, right-1, true);
            return Math.min(a, b);
        }
    }


    public boolean PredictTheWinner2(int[] nums) {
        if (nums.length <= 1) return true;
        int sum = 0;
        for (int n: nums) sum += n;
        int player1 = helper(nums, 0, nums.length - 1, true);
        return player1 >= (sum - player1);
    }

    private int helper(int[] nums, int left, int right, boolean turn1) {
        if (left == right) return turn1 ? nums[left] : 0;

        if (turn1) {
            int a = nums[left] + helper(nums, left+1, right, false);
            int b = nums[right] + helper(nums, left, right-1, false);
            return Math.max(a, b);
        } else {
            int a = helper(nums, left+1, right, true);
            int b = helper(nums, left, right-1, true);
            return Math.min(a, b);
        }
    }


    public boolean PredictTheWinner3(int[] nums) {
        if (nums.length <= 1) return true;
        int sum = 0;
        for (int n: nums) sum += n;
        int[][][] mem = new int[2][nums.length][nums.length];
        int player1 = helper(nums, 0, nums.length - 1, true, mem);
        return player1 >= (sum - player1);
    }

    private int helper(int[] nums, int left, int right, boolean turn1, int[][][] mem) {
        if (left == right) return turn1 ? nums[left] : 0;

        if (turn1) {
            if (mem[0][left][right] > 0) return mem[0][left][right];
            int a = nums[left] + helper(nums, left+1, right, false, mem);
            int b = nums[right] + helper(nums, left, right-1, false, mem);
            mem[0][left][right] = Math.max(a, b);
            return Math.max(a, b);
        } else {
            if (mem[1][left][right] > 0) return mem[1][left][right];
            int a = helper(nums, left+1, right, true, mem);
            int b = helper(nums, left, right-1, true, mem);
            mem[1][left][right] = Math.min(a, b);
            return Math.min(a, b);
        }
    }


    /**
     * https://leetcode.com/problems/predict-the-winner/solution/
     */
    public boolean PredictTheWinner4(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return winner(nums, 0, nums.length - 1, memo) >= 0;
    }

    public int winner(int[] nums, int s, int e, Integer[][] memo) {
        if (s == e)
            return nums[s];
        if (memo[s][e] != null)
            return memo[s][e];
        int a = nums[s] - winner(nums, s + 1, e, memo);
        int b = nums[e] - winner(nums, s, e - 1, memo);
        memo[s][e] = Math.max(a, b);
        return memo[s][e];
    }


    /**
     * https://leetcode.com/problems/predict-the-winner/solution/
     */
    public boolean PredictTheWinner5(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[s + 1][e];
                int b = nums[e] - dp[s][e - 1];
                dp[s][e] = Math.max(a, b);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }


    /**
     * https://leetcode.com/problems/predict-the-winner/solution/
     */
    public boolean PredictTheWinner6(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }

}
