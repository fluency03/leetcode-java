/**
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 * 
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note:
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */

/**
 * https://www.geeksforgeeks.org/ugly-numbers/
 */
public class UglyNumberII264 {
    public int nthUglyNumber(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        Set<Long> set = new HashSet<>();
        Comparator<long[]> comp = (a1, a2) -> Long.compare(a1[0] * a1[1], a2[0] * a2[1]);
        PriorityQueue<long[]> pq = new PriorityQueue<>(1, comp);
        set.add(1L);
        pq.add(new long[]{(long)1, (long)2});

        while (true) {
            long[] curr = pq.poll();
            long newAdd = curr[0] * curr[1];
            if (move(curr)) {
                pq.add(curr);
            }
            if (!set.contains(newAdd)) {
                pq.add(new long[]{newAdd, 2L});
                set.add(newAdd);
            }
            if (set.size() == n) {
                return (int) newAdd;
            }
        }
    }

    private boolean move(long[] p) {
        if (p[1] == 5) return false;
        if (p[1] == 2) {
            p[1] = 3L;
        } else {
            p[1] = 5L;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
     */
    public int nthUglyNumber2(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }


    /**
     * https://leetcode.com/problems/ugly-number-ii/discuss/69372/Java-solution-using-PriorityQueue
     */
    public int nthUglyNumber3(int n) {
        if(n==1) return 1;
        PriorityQueue<Long> q = new PriorityQueue();
        q.add(1l);
        
        for(long i=1; i<n; i++) {
            long tmp = q.poll();
            while(!q.isEmpty() && q.peek()==tmp) tmp = q.poll();
            
            q.add(tmp*2);
            q.add(tmp*3);
            q.add(tmp*5);
        }
        return q.poll().intValue();
    }

}

