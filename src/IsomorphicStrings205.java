/**
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character
 * while preserving the order of characters. No two characters may map to the
 * same character but a character may map to itself.
 * 
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * 
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * 
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * 
 * Note:
 * You may assume both s and t have the same length.
 */

public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null || s.length() != t.length()) return false;
    
        int N = s.length();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            if (map.containsKey(charS[i])) {
                if (map.get(charS[i]) != charT[i]) return false; 
            } else {
                if (map.values().contains(charT[i])) {
                    return false;
                } else {
                    map.put(charS[i], charT[i]);
                }
            }
        }
        return true;
    }


    public boolean isIsomorphic2(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }


    public boolean isIsomorphic3(String sString, String tString) {
        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;
        if(length != t.length) return false;

        char[] sm = new char[256];
        char[] tm = new char[256];

        for(int i=0; i<length; i++){
            char sc = s[i];
            char tc = t[i];
            if(sm[sc] == 0 && tm[tc] == 0){
                sm[sc] = tc;
                tm[tc] = sc;
            }else{
                if(sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isIsomorphic4(String sString, String tString) {
        return encode(sString.toCharArray()) == encode(tString.toCharArray());
    }

    private int encode(char[] chars) {
        int[] map = new int[256];
        int res = 0;
        int id = 1;
        for (int i=1; i<=chars.length; i++) {
            char ch = chars[i-1];
            if (map[ch] == 0) {
                map[ch] = id;
                id++;
            }
            res += map[ch] * i;
        }
        return res;
    }

}
