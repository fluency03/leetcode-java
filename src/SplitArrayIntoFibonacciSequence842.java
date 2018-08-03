/**
 * Given a string S of digits, such as S = "123456579", we can split it into a
 * Fibonacci-like sequence [123, 456, 579].
 * 
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * F.length >= 3;
 * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * 
 * Also, note that when splitting the string into pieces, each piece must not
 * have extra leading zeroes, except if the piece is the number 0 itself.
 * 
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 * 
 * Example 1:
 * Input: "123456579"
 * Output: [123,456,579]
 * 
 * Example 2:
 * Input: "11235813"
 * Output: [1,1,2,3,5,8,13]
 * 
 * Example 3:
 * Input: "112358130"
 * Output: []
 * Explanation: The task is impossible.
 * 
 * Example 4:
 * Input: "0123"
 * Output: []
 * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
 * 
 * Example 5:
 * Input: "1101111"
 * Output: [110, 1, 111]
 * Explanation: The output [11, 0, 11, 11] would also be accepted.
 * 
 * Note:
 * 1 <= S.length <= 200
 * S contains only digits.
 */

public class SplitArrayIntoFibonacciSequence842 {
    public List<Integer> splitIntoFibonacci(String S) {
        int N = S.length();
        List<Integer> res = new ArrayList<>();
        if (N <= 2) return res;
        for (int i=0; i<Math.min(N-2, 10); i++) {
            res = new ArrayList<>();
            try {
                int a = Integer.parseInt(S.substring(0, i+1));
                if (S.charAt(0) == '0' && a != 0) return new ArrayList<>();
                res.add(a);
                for (int j=i+1; j<Math.min(N-1, 10); j++) {
                    int b = Integer.parseInt(S.substring(i+1, j+1));
                    if (S.charAt(i+1) == '0' && b != 0) continue;
                    res.add(b);
                    if (helper(res, a, b, j+1, N, S)) return res;
                    res = res.subList(0, 1);
                }
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public boolean helper(List<Integer> res, int a, int b, int k, int N, String S) {
        int x = a;
        int y = b;
        int idx = k;
        int next = x + y;
        while (idx < N) {
            String n = Integer.toString(next);
            if (!S.startsWith(n, idx)) return false;
            res.add(next);
            x = y;
            y = next;
            next = x + y;
            idx += n.length();
        }
        return true;
    }

}
