/**
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */

public class DivideTwoIntegers29 {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        long result = helper(dividend, divisor);
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
    }

    public long helper(long dividend, long divisor) {
        boolean isNegative = dividend < 0 != divisor < 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        if (dividend < divisor) return 0;

        long res = 0;
        while (dividend >= divisor) {
            long t = divisor;
            long p = 1;
            long tt = t << 1;
            while (dividend > tt) {
                t = tt;
                p <<= 1;
                tt <<= 1;
            }
            res += p;
            dividend -= t;
        }
        return isNegative ? -res : res;
    }

}
