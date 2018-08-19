/**
 * Given a string s , find the length of the longest substring t  that contains
 * at most 2 distinct characters.
 * 
 * Example 1:
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * 
 * Example 2:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */

public class LongestSubstringWithAtMostTwoDistinctCharacters159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return lengthOfLongestSubstringKDistinct(s, 2);
    }

    // 340. Longest Substring with At Most K Distinct Characters
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int[] map = new int[256];
        int left = 0;
        int right = 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        int num = 0;
        int res = 0;
        while (right < N) {
            char c = chars[right++];
            if (map[c] == 0) {
                num++;
            }
            map[c]++;
            
            while (num > k) {
                char leftC = chars[left++];
                if (map[leftC] == 1) {
                    num--;
                }
                map[leftC]--;
            }
            if (right - left > res) {
                res = right - left;
            }
        }
        
        return res;
    }
}
