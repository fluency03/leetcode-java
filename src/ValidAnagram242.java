/**
 * Given two strings s and t, write a function to determine if t is an
 * anagram of s.
 *
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 *
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your
 * solution to such case?
 */

public class ValidAnagram242 {
    public boolean isAnagram(String s, String t) {
        int[] cs = new int[26];
        for (int i=0; i<s.length(); i++) cs[s.charAt(i)-'a']++;
        int[] ct = new int[26];
        for (int i=0; i<t.length(); i++) ct[t.charAt(i)-'a']++;
        for (int i=0; i<26; i++) {
            if (cs[i] != ct[i]) return false;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/valid-anagram/solution/
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /**
     * https://leetcode.com/problems/valid-anagram/solution/
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

}
