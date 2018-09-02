/**
 * Assume you have an array of length n initialized with all 0's and are given
 * k update operations.
 * 
 * Each operation is represented as a triplet: [startIndex, endIndex, inc]
 * which increments each element of subarray A[startIndex ... endIndex]
 * (startIndex and endIndex inclusive) with inc.
 * 
 * Return the modified array after all k operations were executed.
 * 
 * Example:
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 * 
 * Explanation:
 * 
 * Initial state:
 * [0,0,0,0,0]
 * 
 * After applying operation [1,3,2]:
 * [0,2,2,2,0]
 * 
 * After applying operation [2,4,3]:
 * [0,2,5,5,3]
 * 
 * After applying operation [0,2,-2]:
 * [-2,0,3,5,3]
 */

public class RangeAddition370 {
    // brute force
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] up: updates) {
            for (int i=up[0]; i<=up[1]; i++) {
                res[i] += up[2];
            }
        }
        return res;
    }


    // brute force
    public int[] getModifiedArray2(int length, int[][] updates) {
        int[] res = new int[length];
        for (int i=0; i<length; i++) {
            for (int[] up: updates) {
                if (up[0] <= i && up[1] >= i) {
                    res[i] += up[2];
                }
            }
        }
        return res;
    }


    public int[] getModifiedArray3(int length, int[][] updates) {
        int[] res = new int[length];
        int[] starts = new int[length];
        int[] ends = new int[length];
        for (int[] up: updates) {
            starts[up[0]] += up[2];
            if (up[1]+1 < length) ends[up[1]+1] += up[2];
        }
        int curr = 0;
        for (int i=0; i<length; i++) {
            curr += starts[i];
            curr -= ends[i];
            res[i] += curr;
        }
        return res;
    }


    public int[] getModifiedArray4(int length, int[][] updates) {
        int[] res = new int[length];
        int[] ends = new int[length];
        for (int[] up: updates) {
            res[up[0]] += up[2];
            if (up[1]+1 < length) ends[up[1]+1] += up[2];
        }
        int curr = 0;
        for (int i=0; i<length; i++) {
            curr += res[i];
            curr -= ends[i];
            res[i] = curr;
        }
        return res;
    }


    public int[] getModifiedArray5(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] up: updates) {
            res[up[0]] += up[2];
            if (up[1]+1 < length) res[up[1]+1] -= up[2];
        }
        int curr = 0;
        for (int i=0; i<length; i++) {
            curr += res[i];
            res[i] = curr;
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/range-addition/discuss/84219/Java-O(n+k)-time-O(1)-space-with-algorithm-explained
     */
    public int[] getModifiedArray6(int length, int[][] updates) {
        int[] nums = new int[length];
        for (int[] update : updates) {
            nums[update[1]] += update[2];
            if (update[0] > 0) {
                nums[update[0] - 1] -= update[2];
            } 
        }
        
        int sum = nums[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            nums[i] += sum;
            sum = nums[i]; 
        }
        return nums;
    }

}
