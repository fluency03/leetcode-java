/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container and n is at least 2.
 */

public class ContainerWithMostWater11 {
    /**
     * https://leetcode.com/problems/container-with-most-water/discuss/6089/Anyone-who-has-a-O(N)-algorithm/7268
     * Here is the proof.
     * Proved by contradiction:
     * 
     * Suppose the returned result is not the optimal solution. Then there must
     * exist an optimal solution, say a container with a_ol and a_or (left and
     * right respectively), such that it has a greater volume than the one we got.
     * Since our algorithm stops only if the two pointers meet. So, we must have
     * visited one of them but not the other. WLOG, let's say we visited a_ol but
     * not a_or. When a pointer stops at a_ol, it won't move until
     * 
     * The other pointer also points to a_ol.
     * In this case, iteration ends. But the other pointer must have visited a_or
     * on its way from right end to a_ol. Contradiction to our assumption that we
     * didn't visit a_or.
     * 
     * The other pointer arrives at a value, say a_rr, that is greater than a_ol
     * before it reaches a_or.
     * 
     * In this case, we does move a_ol. But notice that the volume of a_ol and
     * a_rr is already greater than a_ol and a_or (as it is wider and heigher),
     * which means that a_ol and a_or is not the optimal solution --
     * Contradiction!
     * 
     * Both cases arrive at a contradiction.
     */
    public int maxArea(int[] height) {
        int res = 0;
        int n = height.length;
        int left = 0;
        int right = n-1;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
