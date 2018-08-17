/**
 * A sequence of numbers is called a wiggle sequence if the differences between
 * successive numbers strictly alternate between positive and negative. The
 * first difference (if one exists) may be either positive or negative. A
 * sequence with fewer than two elements is trivially a wiggle sequence.
 * 
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences
 * (6,-3,5,-7,3) are alternately positive and negative. In contrast,
 * [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its
 * first two differences are positive and the second because its last
 * difference is zero.
 * 
 * Given a sequence of integers, return the length of the longest subsequence
 * that is a wiggle sequence. A subsequence is obtained by deleting some number
 * of elements (eventually, also zero) from the original sequence, leaving the
 * remaining elements in their original order.
 * 
 * Examples:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 * 
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length.
 * One is [1,17,10,13,10,16,8].
 * 
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * 
 * Follow up:
 * Can you do it in O(n) time?
 */

public class WiggleSubsequence376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int[] up = new int[N];
        int[] down = new int[N];
        up[0] = 1;
        down[0] = 1;
        for (int i=1; i<N; i++) {
            up[i] = 1;
            down[i] = 1;
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return Math.max(up[N-1], down[N-1]);
    }


    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int[] up = new int[N];
        int[] down = new int[N];
        for (int i=1; i<N; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return Math.max(up[N-1], down[N-1]) + 1;
    }


    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int[] up = new int[N];
        int[] down = new int[N];
        up[0] = 1;
        down[0] = 1;
        for (int i=1; i<N; i++) {
            if (nums[i] == nums[i-1]) {
                up[i] = up[i-1];
                down[i] = down[i-1];
            } else if (nums[i] > nums[i-1]) {
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            } else {
                up[i] = up[i-1];
                down[i] = up[i-1] + 1;
            }
        }
        return Math.max(up[N-1], down[N-1]);
    }


    public int wiggleMaxLength4(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int up = 1;
        int down = 1;
        for (int i=1; i<N; i++) {
            if (nums[i] > nums[i-1]) {
                up = down + 1;
            } else if (nums[i] < nums[i-1]){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }


    /**
     * https://leetcode.com/problems/wiggle-subsequence/solution/
     */
    public int wiggleMaxLength5(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        return count;
    }

}
