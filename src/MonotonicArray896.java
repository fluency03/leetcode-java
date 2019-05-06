/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * 
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is
 * monotone decreasing if for all i <= j, A[i] >= A[j].
 * 
 * Return true if and only if the given array A is monotonic.
 * 
 * Example 1:
 * Input: [1,2,2,3]
 * Output: true
 * 
 * Example 2:
 * Input: [6,5,4,4]
 * Output: true
 * 
 * Example 3:
 * Input: [1,3,2]
 * Output: false
 * 
 * Example 4:
 * Input: [1,2,4,5]
 * Output: true
 * 
 * Example 5:
 * Input: [1,1,1]
 * Output: true
 * 
 * Note:
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 */

public class MonotonicArray896 {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 2) return true;
        int i = 1;
        int len = A.length;
        while (i < len && A[i] == A[i-1]) i++;
        if (i == len) return true;
        boolean flag = A[i] > A[i-1];
        while (i < len) {
            if (A[i] != A[i-1] && flag != A[i] > A[i-1]) return false;
            i++;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/monotonic-array/solution/
     */
    public boolean isMonotonic2(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i+1]);
            if (c != 0) {
                if (c != store && store != 0)
                    return false;
                store = c;
            }
        }
        return true;
    }
}
