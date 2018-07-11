/**
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * 
 * The string S is magical because concatenating the number of contiguous
 * occurrences of characters '1' and '2' generates the string S itself.
 * 
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * 
 * If we group the consecutive '1's and '2's in S, it will be:
 * 
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * 
 * and the occurrences of '1's or '2's in each group are:
 * 
 * 1 2	2 1 1 2 1 2 2 1 2 2 ......
 * 
 * You can see that the occurrence sequence above is the S itself.
 * 
 * Given an integer N as input, return the number of '1's in the first N number
 * in the magical string S.
 * 
 * Note: N will not exceed 100,000.
 * 
 * Example 1:
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it
 * contains three 1's, so return 3.
 */

public class MagicalString481 {
    public int magicalString(int N) {
        if (N == 0) return 0;
        if (N <= 3) return 1;
        int[] arr = new int[N];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 2;
        int now = 1;
        int i = 2;
        int j = 3;
        int res = 1;
        while (j < N) {
            int curr = arr[i];
            for (int k=0; k<curr; k++) {
                if (j >= N) break;
                if (now == 1) res++;
                arr[j++] = now;
            }
            now = now == 1 ? 2 : 1;
            i++;
        }
        return res;
    }

}
