/**
 * Given a non negative integer number num. For every numbers i in the range
 * 0 ≤ i ≤ num calculate the number of 1's in their binary representation and
 * return them as an array.
 *
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 *
 * Follow up:
 *      It is very easy to come up with a solution with run time
 *      O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly
 *      in a single pass?
 *
 *      Space complexity should be O(n).
 *
 *      Can you do it like a boss? Do it without using any builtin function
 *      like __builtin_popcount in c++ or in any other language.
 */


public class CountingBits338 {
    public int[] countBits(int num) {
        if (num == 0) return new int[]{0};
        if (num == 1) return new int[]{0, 1};

        int[] r = new int[num+1];
        r[0] = 0;
        r[1] = 1;

        int i = 2;
        while (i <= num) {
            for (int j = 0; j<i && i+j<=num; j++) {
                r[i+j] = r[j] + 1;
            }
            i = i*2;
        }

        return r;
    }


    /**
     * https://discuss.leetcode.com/topic/40162/three-line-java-solution
     */
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }

}
