/**
 * Given a non-empty integer array, find the minimum number of moves required
 * to make all array elements equal, where a move is incrementing a selected
 * element by 1 or decrementing a selected element by 1.
 * 
 * You may assume the array's length is at most 10,000.
 * 
 * Example:
 * 
 * Input:
 * [1,2,3]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * 
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */

public class MinimumMovesToEqualArrayElementsII462 {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int m = 0;
        int midIndex = nums.length / 2;
        if (nums.length % 2 == 1) {
            m = nums[midIndex];
        } else {
            m = (nums[midIndex - 1] + nums[midIndex]) /2;
        }
        
        int res = 0;
        for (int n: nums) {
            res += Math.abs(n - m);
        }
        return res;
    }
}
