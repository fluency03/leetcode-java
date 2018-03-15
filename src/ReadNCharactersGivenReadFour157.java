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
 * The read function will only be called once for each test case.
 *
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class ReadNCharactersGivenReadFour157 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int count = 0;
        char[] buf4 = new char[4];
        while (count < n) {
            int k = read4(buf4);
            if (k == 0) return count;
            int add = Math.min(k, n-count);
            for (int i=0; i<add; i++) buf[count+i] = buf4[i];
            count += add;
        }

        return count;
    }

}
