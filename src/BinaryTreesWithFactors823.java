/**
 * Given an array of unique integers, each integer is strictly greater than 1.
 * 
 * We make a binary tree using these integers and each number may be used for
 * any number of times.
 * 
 * Each non-leaf node's value should be equal to the product of the values of
 * it's children.
 * 
 * How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.
 * 
 * Example 1:
 * Input: A = [2, 4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * 
 * Example 2:
 * Input: A = [2, 4, 5, 10]
 * Output: 7
 * Explanation: We can make these trees:
 * [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 * 
 * Note:
 * 1 <= A.length <= 1000.
 * 2 <= A[i] <= 10 ^ 9.
 */

public class BinaryTreesWithFactors823 {
    private static long MOD = (long) Math.pow(10, 9) + 7;
    public int numFactoredBinaryTrees(int[] A) {
        Arrays.sort(A);
        int N = A.length;
        int max = A[N-1];
        Map<Integer, Long> map = new HashMap<>();
        long res = 0;
        for (int i=0; i<N; i++) {
            int curr = A[i];
            long local = 1;
            for (int j=0; j<i; j++) {
                int left = A[j];
                if (curr % left != 0) continue;
                int right = curr / left;
                if (left == right) {
                    local += map.get(left) * map.get(left);
                    continue;
                }
                int idx = Arrays.binarySearch(A, j, i, right);
                if (idx < 0) continue;
                local += map.get(left) * map.get(right) * 2;
            }
            res += local;
            map.put(curr, local);
        }
        return (int) (res % MOD);
    }


    /**
     * https://leetcode.com/problems/binary-trees-with-factors/solution/
     */
    public int numFactoredBinaryTrees2(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;
        Arrays.sort(A);
        long[] dp = new long[N];
        Arrays.fill(dp, 1);

        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < i; ++j) {
                if (A[i] % A[j] == 0) { // A[j] is left child
                    int right = A[i] / A[j];
                    if (index.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[index.get(right)]) % MOD;
                    }
                }
            }

        long ans = 0;
        for (long x: dp) ans += x;
        return (int) (ans % MOD);
    }

}
