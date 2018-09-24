/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 *
 * Note:
 * The read function may be called multiple times.
 *
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class ReadNCharactersGivenRead4IICallMultipleTimes158 extends Reader4 {
    private int cacheSize = 0;
    private int ptr = 0;
    private int SIZE = 4;
    private char[] cache = new char[SIZE];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int count = 0;
        while (cacheSize > 0) {
            if (count >= n) {
                return count;
            }
            buf[count] = cache[ptr];
            count++;
            cacheSize--;
            ptr = (ptr+1) % SIZE;
        }
        char[] buf4 = new char[4];
        while (count < n) {
            int k = read4(buf4);
            if (k == 0) {
                cacheSize = 0;
                return count;
            }
            int add = Math.min(k, n - count);
            for (int i=0; i<add; i++) buf[i+count] = buf4[i];
            if (n - count < k) {
                int p = ptr;
                cacheSize = k - (n-count);
                for (int i=0; i<cacheSize; i++) {
                    cache[p] = buf4[add+i];
                    p = (p+1) % SIZE;
                }
            }
            count += add;
        }

        return count;
    }


    /**
     * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49598/A-simple-Java-code
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read2(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }


    /**
     * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49615/Clean-solution-in-Java
     */
    char[] prevBuf = new char[4];
    int prevSize = 0;
    int prevIndex = 0;
    public int read3(char[] buf, int n) {
        int counter = 0;

        while (counter < n) {
            if (prevIndex < prevSize) {
                buf[counter++] = prevBuf[prevIndex++];
            } else {
                prevSize = read4(prevBuf);
                prevIndex = 0;
                if (prevSize == 0) {
                    // no more data to consume from stream
                    break;
                }
            }
        }
        return counter;
    }

    // private char[] cache = new char[4];
    // private int ptr = 0;
    // private int len = 0;
    // /**
    //  * @param buf Destination buffer
    //  * @param n   Maximum number of characters to read
    //  * @return    The number of characters read
    //  */
    // public int read(char[] buf, int n) {
    //     int res = 0;
    //     while (true) {
    //         while (ptr < len && res < n) {
    //             buf[res++] = cache[ptr++];
    //         }
    //         if (res == n) break;
    //         len = read4(cache);
    //         ptr = 0;
    //         if (len == 0) break;
    //     }
    //     return res;
    // }

}
