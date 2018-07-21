/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with
 * sum at least K.
 * 
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 * Example 1:
 * Input: A = [1], K = 1
 * Output: 1
 * 
 * Example 2:
 * Input: A = [1,2], K = 4
 * Output: -1
 * 
 * Example 3:
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 * 
 * Note:
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 */

public class ShortestSubarrayWithSumAtLeastK862 {
    public int shortestSubarray(int[] A, int K) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        map.put(0, -1);
        for (int i=0; i<A.length; i++) {
            sum += A[i];
            int remain = sum - K;
            int idx = Collections.binarySearch(list, remain);
            if (idx < 0) idx = - (idx + 1) - 1;
            if (idx >= 0) {
                int val = list.get(idx);
                if (sum - val >= K && i - map.get(val) < res) {
                    res = i - map.get(val);
                }
            }
            
            while (!list.isEmpty() && list.get(list.size() - 1) >= sum) {
                int val = list.remove(list.size()-1);
            }
            list.add(sum);
            map.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public int shortestSubarray2(int[] A, int K) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        map.put(0, -1);
        for (int i=0; i<A.length; i++) {
            sum += A[i];
            int remain = sum - K;
            
            while (!list.isEmpty() && sum - list.getFirst() >= K) {
                int val = list.removeFirst();
                if (i - map.get(val) < res) {
                    res = i - map.get(val);
                }
                map.remove(val);
            }
            
            while (!list.isEmpty() && list.getLast() >= sum) {
                int val = list.removeLast();
                map.remove(val);
            }
            list.add(sum);
            map.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    /**
     * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/solution/
     */
    public int shortestSubarray3(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N+1 ? ans : -1;
    }


}
