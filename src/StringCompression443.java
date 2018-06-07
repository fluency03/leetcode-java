/**
 * Given an array of characters, compress it in-place.
 * 
 * The length after compression must always be smaller than or equal to the original array.
 * 
 * Every element of the array should be a character (not int) of length 1.
 * 
 * After you are done modifying the input array in-place, return the new length of the array.
 * 
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * 
 * Example 1:
 * Input:
 * ["a","a","b","b","c","c","c"]
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * 
 * 
 * Example 2:
 * Input:
 * ["a"]
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * Explanation:
 * Nothing is replaced.
 * 
 * 
 * Example 3:
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * 
 * Note:
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */


public class StringCompression443 {
    public int compress(char[] chars) {
        if (chars == null || chars.length <= 0) return 0;
        if (chars.length == 1) return 1;
        int i = 1;
        int j = 1;
        int count = 1;
        char pre = chars[0];
        while (j < chars.length) {
            if (chars[j] == pre) {
                count++;
                j++;
            } else {
                if (count != 1) {
                    char[] nums = Integer.toString(count).toCharArray();
                    for (char n: nums) {
                        chars[i++] = n;
                    }
                }
                pre = chars[j];
                count = 1;
                j++;
                chars[i] = pre;
                i++;
            }
        }
        if (count != 1) {
            char[] nums = Integer.toString(count).toCharArray();
            for (char n: nums) {
                chars[i++] = n;
            }
        }
        return i;
    }


    /**
     * https://leetcode.com/problems/string-compression/solution/
     */
    public int compress2(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

}
