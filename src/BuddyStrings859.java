/**
 * Given two strings A and B of lowercase letters, return true if and only if
 * we can swap two letters in A so that the result equals B.
 * 
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: true
 * 
 * Example 2:
 * Input: A = "ab", B = "ab"
 * Output: false
 * 
 * Example 3:
 * Input: A = "aa", B = "aa"
 * Output: true
 * 
 * Example 4:
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * 
 * Example 5:
 * Input: A = "", B = "aa"
 * Output: false
 * 
 * Note:
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 */

public class BuddyStrings859 {
    public boolean buddyStrings(String A, String B) {
        if (A == null || B == null || A.length() < 2 || B.length() < 2 || A.length() != B.length()) return false;
        char[] aa = A.toCharArray();
        char[] bb = B.toCharArray();
        int len = A.length();
        int first = -1;
        int second = -1;
        int[] map = new int[26];
        boolean same = false;
        for (int i=0; i<len; i++) {
            if (aa[i] == bb[i]) {
                map[aa[i]-'a']++;
                if (map[aa[i]-'a'] >= 2) same = true;
                continue;
            }
            if (first == -1) {
                first = i;
            } else {
                if (second != -1 || (aa[first] != bb[i] || aa[i] != bb[first])) return false;
                second = i;
            }
        }
        return first != -1 || same;
    }
}

