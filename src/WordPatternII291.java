/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty substring in str.
 * 
 * Example 1:
 * Input: pattern = "abab", str = "redblueredblue"
 * Output: true
 * 
 * Example 2:
 * Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
 * Output: true
 * 
 * Example 3:
 * Input: pattern = "aabb", str = "xyzabcxzyabc"
 * Output: false
 * 
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */

public class WordPatternII291 {
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.length() == 0) return str.length() == 0;
        if (str.length() == 0) return false;
        return match(pattern, 0, str, 0, new String[26], new HashMap<>());
    }

    private boolean match(String pattern, int i, String str, int j, String[] p2s, Map<String, Character> s2p) {
        if (i == pattern.length()) return j == str.length();
        if (j == str.length()) return false; 

        char p = pattern.charAt(i);
        if (p2s[p-'a'] != null) {
            String sub = p2s[p-'a'];
            if (!str.startsWith(sub, j)) return false;
            if (match(pattern, i+1, str, j+sub.length(), p2s, s2p)) return true;
        } else {
            for (int k=j+1; k<=str.length(); k++) {
                String sub = str.substring(j, k);
                if (s2p.containsKey(sub)) {
                    char p0 = s2p.get(sub);
                    if (p0 != p) continue;
                    if (match(pattern, i+1, str, j+sub.length(), p2s, s2p)) return true;
                } else {
                    p2s[p-'a'] = sub;
                    s2p.put(sub, p);
                    if (match(pattern, i+1, str, j+sub.length(), p2s, s2p)) return true;
                    p2s[p-'a'] = null;
                    s2p.remove(sub);
                }
            }
        }
        return false;
    }

}
