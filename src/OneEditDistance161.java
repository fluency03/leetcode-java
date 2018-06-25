/**
 * Given two strings s and t, determine if they are both one edit distance apart.
 * 
 * Note: 
 * 
 * There are 3 possiblities to satisify one edit distance apart:
 * 
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * 
 * Example 1:
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * 
 * Example 2:
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * 
 * Example 3:
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */


public class OneEditDistance161 {
    public boolean isOneEditDistance(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        if (Math.abs(lens - lent) > 1) return false;
        if (lens > lent) {
            return isOneDelete(s, t);
        } else if (lent > lens) {
            return isOneDelete(t, s);
        } else {
            return isOneReplace(s, t);
        }
    }

    private boolean isOneReplace(String s, String t) {
        int diff = 0;
        char[] charss = s.toCharArray();
        char[] charst = t.toCharArray();
        
        for (int i=0; i<charss.length; i++) {
            if (charss[i] != charst[i]) {
                diff++;
                if (diff > 1) return false;
            }
        }
        
        return diff == 1;
    }
    
    private boolean isOneDelete(String longStr, String shortStr) {
        char[] charsl = longStr.toCharArray();
        char[] charss = shortStr.toCharArray();
        
        int l = 0;
        int s = 0;
        int diff = 0;
        while (s < charss.length && l < charsl.length) {
            if (charss[s] != charsl[l]) {
                diff++;
                if (diff > 1) return false;
                l++;
            } else {
                s++;
                l++;
            }
        }
        
        return ((l - s) == 1 && diff == 1) || (s == l && diff == 0);
    }

}
