/**
 * Given a string, find the length of the longest substring T that contains
 * at most k distinct characters.
 * 
 * For example, Given s = “eceba” and k = 2,
 * 
 * T is "ece" which its length is 3.
 */


public class LongestSubstringWithAtMostKDistinctCharacters340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int left = 0;
        int right = 0;
        int res = 0;
        int count = 0;
        int[] map = new int[256];
        char[] chars = s.toCharArray();
        while (right < len) {
            if (map[chars[right]] == 0) {
                count++;
            }
            map[chars[right]]++;
            right++;
            
            if (count <= k && right - left > res) {
                res = right - left;
            }
            while (count == k + 1) {
                map[chars[left]]--;
                if (map[chars[left]] == 0) {
                    count--;
                }
                left++;
            }
        }
        return res;
    }

}
