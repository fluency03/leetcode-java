/**
 * Given a string s and a string t, check if s is subsequence of t.
 * 
 * You may assume that there is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short
 * string (<=100).
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ace" is a
 * subsequence of "abcde" while "aec" is not).
 * 
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * 
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * 
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you
 * want to check one by one to see if T has its subsequence. In this scenario,
 * how would you change your code?
 */

public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return true;
        int lenS = s.length();
        int lenT = t.length();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int i = 0;
        int j = 0;
        while (i < lenS && j < lenT) {
            while (j < lenT && charT[j] != charS[i]) {
                j++;
            }
            if (j == lenT) break;
            j++;
            i++;
        }
        return i == lenS;
    }


    /**
     * https://leetcode.com/problems/is-subsequence/discuss/87297/Java.-Only-2ms.-Much-faster-than-normal-2-pointers.
     */
    public boolean isSubsequence2(String s, String t) {
        if(t.length() < s.length()) return false;
        int prev = 0;
        for(int i = 0; i < s.length();i++) {
            char tempChar = s.charAt(i);
            prev = t.indexOf(tempChar,prev);
            if(prev == -1) return false;
            prev++;
        }
        return true;
    }

    public boolean isSubsequence3(String s, String t) {
        int i = 0;
        int j = 0;
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        while (i < chars.length && j < chart.length) {
            if (chars[i] == chart[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == chars.length;
    }

}
