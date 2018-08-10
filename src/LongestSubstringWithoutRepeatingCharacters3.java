/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 */


import java.util.Map;
import java.util.HashMap;


public class LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        int start = 0;
        int ll = 1;
        Map<Character, Integer> found = new HashMap<>();
        found.put(s.charAt(0), 0);
        int i;
        for (i = 1; i < s.length(); i++) {
            if (found.containsKey(s.charAt(i))) {
                int newStart = remove(start, i, s.charAt(i), found, s);

                if (i - start > ll) {
                    ll = i - start;
                }
                start = newStart;
            } else {
                found.put(s.charAt(i), i);
            }
        }

        return Math.max(ll, i - start);
    }

    private int remove(int start, int now, char re, Map<Character, Integer> found, String s) {
        int j;
        for (j = start; j < now; j++) {
            if (s.charAt(j) == re) {
                found.remove(s.charAt(j));
                break;
            }
            found.remove(s.charAt(j));
        }
        found.put(s.charAt(now), now);
        return j + 1;
    }


    /**
     * https://discuss.leetcode.com/topic/8232/11-line-simple-java-solution-o-n-with-explanation
     */
    public int lengthOfLongestSubstring2(String s) {
         if (s.length()==0) return 0;
         HashMap<Character, Integer> map = new HashMap<Character, Integer>();
         int max=0;
         for (int i=0, j=0; i<s.length(); ++i){
             if (map.containsKey(s.charAt(i))){
                 j = Math.max(j,map.get(s.charAt(i))+1);
             }
             map.put(s.charAt(i),i);
             max = Math.max(max,i-j+1);
         }
         return max;
     }


     public int lengthOfLongestSubstring3(String s) {
         int [] map = new int[256];
         for(int i = 0; i < map.length; i++){
             map[i] = -1;
         }
         int max = 0;
         int pos = -1;
         for(int i = 0; i < s.length(); i++){
             pos = Math.max(pos, map[(int)s.charAt(i)]);
             max = Math.max(max, i - pos);
             map[(int)s.charAt(i)] = i;
         }
         return max;
     }


     public int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] map = new int[256];
        int left = 0;
        int right = 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        int maxLen = 0;
        while (right < N) {
            char curr = chars[right++];
            map[curr]++;
            if (map[curr] == 1 && right - left > maxLen) {
                maxLen = right - left;
            }
            while (map[curr] > 1) {
                char leftChar = chars[left++];
                map[leftChar]--;
            }
        }
        return maxLen;
    }

}
