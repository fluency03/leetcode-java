/**
 * mplement function ToLowerCase() that has a string parameter str, and returns
 * the same string in lowercase.
 * 
 * Example 1:
 * Input: "Hello"
 * Output: "hello"
 * 
 * Example 2:
 * Input: "here"
 * Output: "here"
 * 
 * Example 3:
 * Input: "LOVELY"
 * Output: "lovely"
 */

public class ToLowerCase709 {
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * https://leetcode.com/problems/to-lower-case/discuss/148993/Java-no-library-methods
     */
    public String toLowerCase2(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++)
            if ('A' <= a[i] && a[i] <= 'Z')
                a[i] = (char) (a[i] - 'A' + 'a');
        return new String(a);
    }

}
