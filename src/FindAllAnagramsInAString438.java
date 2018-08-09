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


    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92015/ShortestConcise-JAVA-O(n)-Sliding-Window-Solution
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--; 
            
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);
        
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }


    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     */
    public List<Integer> findAnagrams4(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }


    public List<Integer> findAnagrams5(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] map = new int[26];
        int M = 0;
        for (char c: p.toCharArray()) {
            if (map[c - 'a'] == 0) M++;
            map[c - 'a']++;
        }
        char[] charS = s.toCharArray();
        int N = charS.length;
        int P = p.length();
        int left = 0;
        int right = 0;
        while (right < N) {
            char rc = charS[right++];
            map[rc - 'a']--;
            if (map[rc - 'a'] == 0) M--;
            if (M == 0) res.add(left);
            if (right - left == P) {
                char lc = charS[left++];
                if (map[lc - 'a'] == 0) M++;
                map[lc - 'a']++;
            }
        }
        return res;
    }


}
