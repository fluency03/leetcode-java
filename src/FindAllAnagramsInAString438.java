/**
 * Given a string s and a non-empty string p, find all the start indices of
 * p's anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * 
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

public class FindAllAnagramsInAString438 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[26];
        for (char c: p.toCharArray()) {
            map[c - 'a']++;
        }

        List<Integer> res = new ArrayList<>();
        int lenS = s.length();
        int count = p.length();
        if (lenS < count) return res;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = 0;
        while (j < lenS) {
            char c = chars[j];
            while (i <= j && map[c - 'a'] <= 0) {
                map[chars[i] - 'a']++;
                i++;
            }
            map[c - 'a']--;
            j++;
            if (j - i == count) {
                res.add(i);
                map[chars[i] - 'a']++;
                i++;
            }
        }

        return res;
    }


    public List<Integer> findAnagrams2(String s, String p) {
        int[] map = new int[26];
        for (char c: p.toCharArray()) {
            map[c - 'a']++;
        }

        List<Integer> res = new ArrayList<>();
        int lenS = s.length();
        int lenP = p.length();
        if (lenS < lenP) return res;
        char[] chars = s.toCharArray();
        int[] window = new int[26];
        for (int i=0; i<lenP; i++) {
            window[chars[i] - 'a']++;
        }
        if (compare(map, window)) res.add(0);
        for (int i=0; i<(lenS-lenP); i++) {
            window[chars[i] - 'a']--;
            window[chars[i+lenP] - 'a']++;
            if (compare(map, window)) res.add(i+1);
        }
        return res;
    }

    private boolean compare(int[] map, int[] window) {
        for (int i=0; i<26; i++) {
            if (map[i] != window[i]) return false;
        }
        return true;
    }

}
