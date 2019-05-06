/**
 * A sorted list A contains 1, plus some number of primes. Then, for every p < q
 * in the list, we consider the fraction p/q.
 * 
 * What is the K-th smallest fraction considered?  Return your answer as an
 * array of ints, where answer[0] = p and answer[1] = q.
 * 
 * Examples:
 * Input: A = [1, 2, 3, 5], K = 3
 * Output: [2, 5]
 * Explanation:
 * The fractions to be considered in sorted order are:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
 * The third fraction is 2/5.
 * 
 * Input: A = [1, 7], K = 1
 * Output: [1, 7]
 * 
 * Note:
 * A will have length between 2 and 2000.
 * Each A[i] will be between 1 and 30000.
 * K will be between 1 and A.length * (A.length - 1) / 2.
 */

/**
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
 */
public class KthSmallestPrimeFraction786 {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        Comparator<int[]> comp = (int[] a, int[] b) -> {
            return Double.compare(A[a[0]] * 1.0 / A[a[1]], A[b[0]] * 1.0 / A[b[1]]);
        };
        PriorityQueue<int[]> q = new PriorityQueue<>(comp);
        int len = A.length;
        q.add(new int[]{0, len-1});
        boolean[][] mark = new boolean[len][len];
        mark[0][len-1] = true;
        int c = 0;
        while (c + 1 < K) {
            int[] tmp = q.remove();
            c++;
            int i = tmp[0];
            int j = tmp[1];
            if (i+1 < len && i+1 < j && !mark[i+1][j]) {
                q.add(new int[]{i+1, j});
                mark[i+1][j] = true;
            }
            if (j-1 >= 0 && i < j-1 && !mark[i][j-1]) {
                q.add(new int[]{i, j-1});
                mark[i][j-1] = true;
            }
            if (i+1 < len && j-1 >= 0 && i+1 < j-1 && !mark[i+1][j-1]) {
                q.add(new int[]{i+1, j-1});
                mark[i+1][j-1] = true;
            }
        }
        int[] tmp = q.peek();
        return new int[]{A[tmp[0]], A[tmp[1]]};
    }


    /**
     * https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115486/Java-AC-O(max(nk)-*-logn)-Short-Easy-PriorityQueue
     */
    public int[] kthSmallestPrimeFraction2(int[] a, int k) {
        int n = a.length;
        // 0: numerator idx, 1: denominator idx
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int s1 = a[o1[0]] * a[o2[1]];
                int s2 = a[o2[0]] * a[o1[1]];
                return s1 - s2;
            }
        });
        for (int i = 0; i < n-1; i++) {
            pq.add(new int[]{i, n-1});
        }
        for (int i = 0; i < k-1; i++) {
            int[] pop = pq.remove();
            int ni = pop[0];
            int di = pop[1];
            if (pop[1] - 1 > pop[0]) {
                pop[1]--;
                pq.add(pop);
            }
        }

        int[] peek = pq.peek();
        return new int[]{a[peek[0]], a[peek[1]]};
    }

    /**
     * https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/116107/Java-Better-than-O(NlogN)-it-can-be-O(KlogK)
     */
    public int[] kthSmallestPrimeFraction3(int[] A, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> A[a[0]] * A[b[1]] - A[a[1]] * A[b[0]]);
        pq.offer(new int[]{0, A.length - 1});
        while (K > 1 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[1] == A.length - 1 && cur[0] + 1 < cur[1]) {
                pq.offer(new int[]{cur[0] + 1, cur[1]});
            }
            if (cur[0] < cur[1] - 1) {
                pq.offer(new int[]{cur[0], cur[1] - 1});
            }
            K--;
        }
        if (pq.isEmpty()) {
            throw new RuntimeException("invalid input.");
        }
        return new int[]{A[pq.peek()[0]], A[pq.peek()[1]]};
    }
}
