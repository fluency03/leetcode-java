/**
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 */


public class PartitionEqualSubsetSum416 {
    public boolean canPartition(int[] nums) {
        if (nums.length == 1) return nums[0] == 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        if ((sum % 2) != 0) return false;

        int half = sum / 2;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int i = 1;
        int acc = nums[0];
        while (!q.isEmpty() && i < nums.length) {
            int curr = nums[i];
            if (acc == half) {
                return true;
            } else if (acc < half) {
                q.offer(i);
                acc += curr;
                i++;
            } else {
                i = q.poll();
                acc -= nums[i];
                i++;
                if (i >= nums.length) {
                    i = q.poll();
                    acc -= nums[i];
                    i++;
                }
            }
        }

        return false;
    }


    /**
     * https://discuss.leetcode.com/topic/62987/java-dynamic-programming-solution-21ms-with-explanation
     */
    public boolean canPartition2(int[] nums) {
    	int total = 0;
    	for(int i : nums) total+=i; // compute the total sum of the input array
    	if(total%2 != 0) return false; // if the array sum is not even, we cannot partition it into 2 equal subsets
    	int max = total/2; // the maximum for a subset is total/2
    	int[][] results = new int[nums.length][max]; // integer matrix to store the results, so we don't have to compute it more than one time
    	return isPartitionable(max,0,0,nums,results);
    }

    public boolean isPartitionable(int max,int curr, int index, int[] nums, int[][] results) {
    	if(curr>max || index>nums.length-1) return false; // if we passed the max, or we reached the end of the array, return false
    	if(curr==max) return true; // if we reached the goal (total/2) we found a possible partition
    	if(results[index][curr]==1) return true; // if we already computed teh result for the index i with the sum current, we retrieve this result (1 for true)
    	if(results[index][curr]==2) return false; // if we already computed teh result for the index i with the sum current, we retrieve this result (2 for false)
    	boolean res = isPartitionable(max, curr+nums[index], index+1, nums, results) || isPartitionable(max, curr, index+1, nums, results); // else try to find the equal partiion, taking this element, or not taking it
    	results[index][curr] = res ? 1 : 2; // store the result for this index and this current sum, to use it in dynamic programming
    	return res;
    }


    /**
     * https://discuss.leetcode.com/topic/67539/0-1-knapsack-detailed-explanation
     */
    public boolean canPartition3(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], false);
        }

        dp[0][0] = true;

        for (int i = 1; i < n+1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < sum+1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum+1; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i-1]) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }

        return dp[n][sum];
    }

    /**
     * https://discuss.leetcode.com/topic/62312/java-solution-similar-to-backpack-problem-easy-to-understand
     */
    public boolean canPartition4(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = volumn; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
        }
        return dp[volumn];
    }


}
