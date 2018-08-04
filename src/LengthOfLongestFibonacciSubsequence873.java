/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * 
 * Given a strictly increasing array A of positive integers forming a sequence,
 * find the length of the longest fibonacci-like subsequence of A.
 * If one does not exist, return 0.
 * 
 * (Recall that a subsequence is derived from another sequence A by deleting
 * any number of elements (including none) from A, without changing the order
 * of the remaining elements.  For example, [3, 5, 8] is a subsequence of
 * [3, 4, 5, 6, 7, 8].)
 * 
 * Example 1:
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 * 
 * Example 2:
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 * 
 * Note:
 * 
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 */


public class LengthOfLongestFibonacciSubsequence873 {
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        int longest = Integer.MIN_VALUE;
        for (int i=0; i<len-3; i++) {
            for (int j=i+1; j<len-2; j++) {
                int a = A[i];
                int b = A[j];
                int idx = Arrays.binarySearch(A, j+1, len, a + b);
                if (idx < 0) continue;
                int l = 2;
                while (idx >= 0 && idx < len) {
                    l++;
                    a = b;
                    b = A[idx];
                    if (idx >= len) break;
                    idx = Arrays.binarySearch(A, idx + 1, len, a + b);
                }
                if (l > longest) {
                    longest = l;
                }
            }
        }
        return longest == Integer.MIN_VALUE ? 0 : longest;
    }


    public int lenLongestFibSubseq2(int[] A) {
        int N = A.length;
        int[][] dp = new int[N + 1][N + 1];
        int res = 0;
        for (int i=2; i<N; i++) {
            for (int j=i+1; j<=N; j++) {
                int toBeFound = A[j-1] - A[i-1];
                if (toBeFound > A[i-2]) break;
                int idx = Arrays.binarySearch(A, 0, i-1, toBeFound);
                if (idx >= 0) {
                    if (dp[idx+1][i] == 0) {
                        dp[i][j] = 3;
                    } else {
                        dp[i][j] += dp[idx+1][i] + 1;
                    }
                }
                if (dp[i][j] > res) res = dp[i][j];
            }
        }
        return res;
    }


    public int lenLongestFibSubseq3(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i) index.put(A[i], i);
        int[][] dp = new int[N + 1][N + 1];
        int res = 0;
        for (int i=2; i<N; i++) {
            for (int j=i+1; j<=N; j++) {
                int toBeFound = A[j-1] - A[i-1];
                if (toBeFound > A[i-2]) break;
                if (index.containsKey(toBeFound)) {
                    int idx = index.get(toBeFound);
                    if (dp[idx+1][i] == 0) {
                        dp[i][j] = 3;
                    } else {
                        dp[i][j] += dp[idx+1][i] + 1;
                    }
                }
                if (dp[i][j] > res) res = dp[i][j];
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/solution/
     */
    public int lenLongestFibSubseq4(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;

        for (int k = 0; k < N; ++k) {
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // Encoding tuple (i, j) as integer (i * N + j)
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }


    /**
     * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/discuss/152343/C++JavaPython-Check-Pair
     */
    public int lenLongestFibSubseq5(int[] A) {
        Set<Integer> s = new HashSet<Integer>();
        for (int x : A) s.add(x);
        int res = 2;
        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                int a = A[i], b = A[j], l = 2;
                while (s.contains(a + b)) {
                    b = a + b;
                    a = b - a;
                    l++;
                }
                res = Math.max(res, l);
            }
        }
        return res > 2 ? res : 0;
    }


    /**
     * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/discuss/152343/C++JavaPython-Check-Pair
     */
    public int lenLongestFibSubseq6(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        Map<Integer, Integer> index = new HashMap<>();
        for (int j = 0; j < A.length; j++) {
            index.put(A[j], j);
            for (int i = 0; i < j; i++) {
                int k = index.getOrDefault(A[j] - A[i], -1);
                dp[i][j] = (A[j] - A[i] < A[i] && k >= 0) ? dp[k][i] + 1 : 2;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res > 2 ? res : 0;
    }

}
