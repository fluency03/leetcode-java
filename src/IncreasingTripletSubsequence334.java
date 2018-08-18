/**
 * Given an unsorted array return whether an increasing subsequence of length
 * 3 exists or not in the array.
 *
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 *
 * Given [5, 4, 3, 2, 1],
 * return false.
 */


public class IncreasingTripletSubsequence334 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int a = nums[0];
        int b = Integer.MAX_VALUE;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > b) return true;
            else if (nums[i] > a) b = nums[i];
            else a = nums[i];
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int N = nums.length;
        int[] dp = new int[3];
        
        int size = 0;
        for (int i=0; i<N; i++) {
            int curr = nums[i];
            int insertPoist = 0;
            while (insertPoist < size && dp[insertPoist] < curr) {
                insertPoist++;
            }
            dp[insertPoist] = curr;
            if (insertPoist == size) size++;
            if (size == 3) return true;
        }
        return false;
    }


    public boolean increasingTriplet3(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int N = nums.length;
        int[] dp = new int[N + 1];
        for (int i=1; i<=N; i++) {
            int tmp = 1;
            for (int j=1; j<i; j++) {
                if (nums[i-1] > nums[j-1] && dp[j] + 1 > tmp) {
                    tmp = dp[j] + 1;
                }
                if (tmp >= 3) return true;
            }
            dp[i] = tmp;
        }
        return false;
    }

}
