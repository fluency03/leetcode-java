/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 * 
 * Example 1:
 * Input: [5,7]
 * Output: 4
 * 
 * Example 2:
 * Input: [0,1]
 * Output: 0
 */

public class BitwiseANDOfNumbersRange201 {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int res = 0;
        int range = n - m + 1;
        int b = 1;
        for (int i=0; i<=31; i++) {
            if (range <= b && (getBit(m, i) == 1 && getBit(n, i) == 1)) {
                res = setBit(res, i);
            }
            b <<= 1;
        }
        return res;
    }

    private int setBit(int num, int pos) {
        return num | (1 << pos);
    }

    private int getBit(int num, int pos) {
        return (num & (1 << pos)) >> pos;
    }


    /**
     * https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56729/Bit-operation-solution(JAVA)
     */
    public int rangeBitwiseAnd2(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }


    /**
     * https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56721/2-line-Solution-with-detailed-explanation
     */
    public int rangeBitwiseAnd3(int m, int n) {
        while(m<n) n = n & (n-1);
        return n;
    }

}
