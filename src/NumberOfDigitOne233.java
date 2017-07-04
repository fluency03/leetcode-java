/**
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 *
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

public class NumberOfDigitOne233 {
    public int countDigitOne(int n) {
        if (n < 1) return 0;
        int result = 0;
        int i = 1;
        int preK = 0;
        int k = n%10;
        int num = n/10;
        while (num != 0) {
            result += currentOne(n, k, preK, num, i);

            i = i*10;
            preK = k;
            k = num%10;
            num = num/10;
        }

        // do last once!!!
        result += currentOne(n, k, preK, num, i);

        return result;
    }


    private int currentOne(int n, int k, int preK, int num, int i) {
        if (k < 1) return num*i;
        if (k > 1) return (num+1)*i;
        return num*i + n%i + 1;
    }


    /**
     * https://discuss.leetcode.com/topic/18054/4-lines-o-log-n-c-java-python
     */
    public int countDigitOne2(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
        return ones;
    }

    /**
     * https://discuss.leetcode.com/topic/18054/4-lines-o-log-n-c-java-python
     */
    public int countDigitOne3(int n) {
        int ones = 0, m = 1, r = 1;
        while (n > 0) {
            ones += (n + 8) / 10 * m + (n % 10 == 1 ? r : 0);
            r += n % 10 * m;
            m *= 10;
            n /= 10;
        }
        return ones;
    }


    /**
     * https://discuss.leetcode.com/topic/22441/0-ms-recursive-solution
     */
    public int countDigitOne4(int n) {
        if(n<1) return 0;
        if(n>=1 && n<10) return 1;
        // x: first digit
        int y=1, x=n;
        while(!(x<10)){
            x/=10;
            y*=10;
        }
        if(x==1)
            return n-y+1+countDigitOne4(y-1)+countDigitOne4(n%y);
        else
            return y+x*countDigitOne4(y-1)+countDigitOne4(n%y);
    }

}
