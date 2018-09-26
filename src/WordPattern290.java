/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty word in str.
 * 
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * 
 * Example 2:
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * 
 * Example 3:
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * 
 * Example 4:
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * 
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains
 * lowercase letters separated by a single space.
 */

public class WordPattern290 {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.trim().split("\\s+");
        if (pattern.length() != words.length) return false;
        Map<Character, String> map = new HashMap<>();
        int N = pattern.length();
        for (int i=0; i<N; i++) {
            String w = words[i];
            Character ch = pattern.charAt(i);
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(w)) return false;
            } else {
                if (map.values().contains(w)) return false;
                map.put(ch, w);
            }
        }
        return true;
    }


    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.trim().split("\\s+");
        if (pattern.length() != words.length) return false;
        String[] map = new String[26];
        int N = pattern.length();
        for (int i=0; i<N; i++) {
            String w = words[i];
            char ch = pattern.charAt(i);
            int idx = ch - 'a';
            if (map[idx] != null) {
                if (!map[idx].equals(w)) return false;
            } else {
                if (contains(map, w)) return false;
                map[idx] = w;
            }
        }
        return true;
    }
    
    private boolean contains(String[] map, String w) {
        for (String m: map) {
            if (m != null && m.equals(w)) {
                return true;
            }
        }
        return false;
    }


    public boolean wordPattern3(String pattern, String str) {
        String[] words = str.trim().split("\\s+");
        if (pattern.length() != words.length) return false;
        String[] map = new String[26];
        Set<String> set = new HashSet<>();
        int N = pattern.length();
        for (int i=0; i<N; i++) {
            String w = words[i];
            char ch = pattern.charAt(i);
            int idx = ch - 'a';
            if (map[idx] != null) {
                if (!map[idx].equals(w)) return false;
            } else {
                if (set.contains(w)) return false;
                map[idx] = w;
                set.add(w);
            }
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/word-pattern/discuss/73402/8-lines-simple-Java
     */
    public boolean wordPattern4(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

}
