/**
 * Given four lists A, B, C, D of integer values, compute how many tuples
 * (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * 
 * To make problem a bit easier, all A, B, C, D have same length of N where
 * 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result
 * is guaranteed to be at most 231 - 1.
 * 
 * Example:
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * Output: 2
 * 
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */

public class FourSumII454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (invalid(A) || invalid(B) || invalid(C) || invalid(D)) return 0;
        int N = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            int c = C[i];
            for (int j=0; j<N; j++) {
                int sum = c + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        for (int i=0; i<N; i++) {
            int a = A[i];
            for (int j=0; j<N; j++) {
                int counterPart = 0 - a - B[j];
                res += map.getOrDefault(counterPart, 0);
            }
        }
        return res;
    }

    private boolean invalid(int[] arr) {
        return arr == null || arr.length == 0;
    }


    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        if (invalid(A) || invalid(B) || invalid(C) || invalid(D)) return 0;
        Arrays.sort(A);
        int N = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            int c = C[i];
            for (int j=0; j<N; j++) {
                int sum = c + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        int lastA = 0;
        for (int i=0; i<N; i++) {
            if (i != 0 && A[i] == A[i-1]) {
                res += lastA;
                continue;
            }
            int a = A[i];
            int tmpRes = res;
            for (int j=0; j<N; j++) {
                int counterPart = 0 - a - B[j];
                res += map.getOrDefault(counterPart, 0);
            }
            lastA = res - tmpRes;
        }
        return res;
    }

}
