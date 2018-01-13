/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get
 *
 * nums[left] * nums[i] * nums[right]
 *
 * coins. Here left and right are adjacent indices of i. After the burst,
 * the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 *
 * Given [3, 1, 5, 8]
 *
 * Return 167
 *
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */


public class BurstBalloons312 {
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int res = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int l = (i == 0) ? 1 : nums[i-1];
            int r = (i == nums.length-1) ? 1 : nums[i+1];
            int curr = l*nums[i]*r;
            int[] left = Arrays.copyOfRange(nums, 0, i);
            int[] right = Arrays.copyOfRange(nums, i+1, nums.length);
            res = Math.max(res, curr + maxCoins(
                IntStream.concat(Arrays.stream(left), Arrays.stream(right)).toArray()
            ));
        }
        return res;
    }


    public int maxCoins1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        Set<Integer> key = new HashSet<>();
        for (int i=0; i<nums.length; i++) key.add(i);
        return dp(nums, key, new HashMap<Set<Integer>, Integer>());
    }

    private int dp(int[] nums, Set<Integer> key, Map<Set<Integer>, Integer> caches) {
        if (key.size() == 0) return 0;
        if (key.size() == 1) return nums[key.iterator().next()];
        if (caches.containsKey(key)) return caches.get(key);

        int res = Integer.MIN_VALUE;
        for (Integer pos: key) {
            int posL = pos-1;
            int posR = pos+1;
            while(posL >= 0 && !key.contains(posL)) posL--;
            while(posR < nums.length && !key.contains(posR)) posR++;
            int l = (posL < 0) ? 1 : nums[posL];
            int r = (posR >= nums.length) ? 1 : nums[posR];
            int curr = l * nums[pos] * r;
            Set<Integer> newKey = new HashSet<>(key);
            newKey.remove(pos);

            if (caches.containsKey(newKey)) {
                res = Math.max(res, curr + caches.get(newKey));
            } else {
                Integer rem = dp(nums, newKey, caches);
                caches.put(newKey, rem);
                res = Math.max(res, curr + rem);
            }
        }
        return res;
    }


    public int maxCoins2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[][] dp = new int[nums.length][nums.length];
        for (int len=0; len<=nums.length; len++) {
            for (int s=0; s<=nums.length-len; s++) {
                int e = s + len - 1;
                for (int i=s; i<=e; i++) {
                    int l = (s <= 0) ? 1 : nums[s-1];
                    int r = (e >= nums.length-1) ? 1 : nums[e+1];
                    int curr = l * nums[i] * r;
                    curr += i != s ? dp[s][i - 1] : 0;
                    curr += i != e ? dp[i + 1][e] : 0;
                    dp[s][e] = Math.max(dp[s][e], curr);
                }

            }
        }

        return dp[0][nums.length-1];
    }


    /**
     * https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
     */
    public int maxCoins3(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;

        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }

        return dp[0][n - 1];
    }

    /**
     * https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
     */
    public int maxCoins4(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;

        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }

}
