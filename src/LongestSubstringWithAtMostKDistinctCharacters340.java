/**
 * Given a string, find the length of the longest substring T that contains
 * at most k distinct characters.
 * 
 * For example, Given s = “eceba” and k = 2,
 * 
 * T is "ece" which its length is 3.
 */


public class LongestSubstringWithAtMostKDistinctCharacters340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k){
        if (s == null || s.length() == 0 || k == 0) return 0;
        int[] map = new int[256];
        
        int slow = 0;
        int fast = 0;
        int longestLen = 0;
        char[] chars = s.toCharArray();
        int numChar = 0;
        while (fast < s.length()) {
            char c = chars[fast];
            if (map[c] == 0) {
                numChar++;
            }
            map[c]++;
            if (fast - slow > longestLen) longestLen = fast - slow;
            if (numChar > k) {
                while (slow < fast) {
                    char cs = chars[slow];
                    map[cs]--;
                    slow++;
                    if (map[cs] == 0) {
                        numChar--;
                        break;
                    }
                }
            }
            fast++;
        }
        return Math.max(longestLen, fast - slow);
    }

}
