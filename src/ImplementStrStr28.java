/**
 * Implement strStr(). http://www.cplusplus.com/reference/cstring/strstr/
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 *
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 */


public class ImplementStrStr28 {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() == 0 || haystack.length() < needle.length()) return -1;

        int i = 0;
        while (i <= haystack.length() - needle.length()) {
            int j = 0;
            int t = i;
            while (j < needle.length() && haystack.charAt(t) == needle.charAt(j)) {
                j++;
                t++;
            }
            if (j == needle.length()) return i;
            i++;
        }

        return -1;
    }


    /**
     * https://leetcode.com/problems/implement-strstr/discuss/12811/Share-my-accepted-java-solution
     */
    public int strStr2(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; ++i) {
            if (haystack.substring(i,i+l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

}
