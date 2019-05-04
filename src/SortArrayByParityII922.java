/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * 
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 * 
 * You may return any answer array that satisfies this condition.
 * 
 * Example 1:
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * 
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * 
 * Note:
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class SortArrayByParityII922 {
    public int[] sortArrayByParityII(int[] A) {
        int i = 0;
        int j = 1;
        int k = 0;
        int len = A.length;
        int[] B = new int[len];
        while (k < len) {
            if (isOdd(A[k])) {
                B[j] = A[k];
                j += 2;
            } else {
                B[i] = A[k];
                i += 2;
            }
            k++;
        }
        return B;
    }

    private boolean isOdd(int num) {
        return (num & 1) == 1;
    }
}
