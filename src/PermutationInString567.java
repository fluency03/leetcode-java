/**
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's
 * permutations is the substring of the second string.
 * 
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * 
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */

public class PermutationInString567 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) return false;
        int[] map = new int[26];
        int M = 0;
        for (char c: s1.toCharArray()) {
            if (map[c - 'a'] == 0) M++;
            map[c - 'a']++;
        }
        char[] chars2 =  s2.toCharArray();
        int N2 = s2.length();
        int N1 = s1.length();
        int left = 0;
        int right = 0;
        while (right < N2) {
            char rc = chars2[right++];
            map[rc - 'a']--;
            if (map[rc - 'a'] == 0) M--;
            if (M == 0) return true;
            if (right - left == N1) {
                char lc = chars2[left++];
                if (map[lc - 'a'] == 0) M++;
                map[lc - 'a']++;
            }
        }
        return false;
    }

}
