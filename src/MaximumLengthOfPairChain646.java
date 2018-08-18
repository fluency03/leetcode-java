/**
 * You are given n pairs of numbers. In every pair, the first number is always
 * smaller than the second number.
 * 
 * Now, we define a pair (c, d) can follow another pair (a, b) if and
 * only if b < c. Chain of pairs can be formed in this fashion.
 * 
 * Given a set of pairs, find the length longest chain which can be formed.
 * You needn't use up all the given pairs. You can select pairs in any order.
 * 
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * 
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */

public class MaximumLengthOfPairChain646 {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[0], p2[0]));
        int N = pairs.length;
        int[] dp = new int[N + 1];
        int res = 0;
        for (int i=1; i<=N; i++) {
            int local = 1;
            for (int j=1; j<i; j++) {
                if (pairs[i-1][0] > pairs[j-1][1] && dp[j] + 1 > local) {
                    local = dp[j] + 1;
                }
            }
            dp[i] = local;
            if (local > res) {
                res = local;
            }
        }
        return res;
    }


    public int findLongestChain2(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[0], p2[0]));
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] p: pairs) {
            if (list.isEmpty() || list.getLast()[1] < p[0]) {
                list.add(p);
            } else {
                if (p[1] < list.getLast()[1]) {
                    list.removeLast();
                    list.add(p);
                }
            }
        }
        return list.size();
    }


    public int findLongestChain3(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (p1, p2) -> Integer.compare(p1[0], p2[0]));
        int[] last = null;
        int res = 0;
        for (int[] p: pairs) {
            if (last == null || last[1] < p[0]) {
                last = p;
                res++;
            } else {
                if (p[1] < last[1]) {
                    last = p;
                }
            }
        }
        return res;
    }

}
