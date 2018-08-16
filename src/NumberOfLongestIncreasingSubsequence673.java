/**
 * Given an unsorted array of integers, find the number of longest increasing
 * subsequence.
 * 
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are
 * [1, 3, 4, 7] and [1, 3, 5, 7].
 * 
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1,
 * and there are 5 subsequences' length is 1, so output 5.
 * 
 * Note: Length of the given array will be not exceed 2000 and the answer is
 * guaranteed to be fit in 32-bit signed int.
 */

public class NumberOfLongestIncreasingSubsequence673 {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int[] dp = new int[N];
        int[] count = new int[N];
        int res = 0;
        int maxLen = 1;
        for (int i=0; i<N; i++) {
            int curr = nums[i];
            int localRes = 1;
            int localCount = 1;
            for (int j=0; j<i; j++) {
                if (curr > nums[j]) {
                    if (dp[j] + 1 > localRes) {
                        localRes = dp[j] + 1;
                        localCount = count[j];
                    } else if (dp[j] + 1 == localRes) {
                        localCount += count[j];
                    }
                }
            }
            if (localRes > maxLen) {
                maxLen = localRes;
                res = localCount;
            } else if (localRes == maxLen) {
                res += localCount;
            }
            count[i] = localCount;
            dp[i] = localRes;
        }
        return res;
    }
}
