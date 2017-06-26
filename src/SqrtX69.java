/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 */



public class SqrtX69 {
    public int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    /**
     * https://discuss.leetcode.com/topic/8680/a-binary-search-solution
     */
    public int mySqrt2(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

    /**
     * https://discuss.leetcode.com/topic/24532/3-4-short-lines-integer-newton-every-language
     */
    public int mySqrt3(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }


    /**
     * https://discuss.leetcode.com/topic/2671/share-my-o-log-n-solution-using-bit-manipulation/26
     */
    public int mySqrt(int x) {
        if(x==0)
            return 0;
        int h=0;
        while((long)(1<<h)*(long)(1<<h)<=x) // firstly, find the most significant bit
            h++;
        h--;
        int b=h-1;
        int res=(1<<h);
        while(b>=0){  // find the remaining bits
            if((long)(res | (1<<b))*(long)(res |(1<<b))<=x)
                res|=(1<<b);
            b--;
        }
        return res;
    }


}
