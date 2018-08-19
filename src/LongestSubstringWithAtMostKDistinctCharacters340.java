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


    /**
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/discuss/80044/Java-O(nlogk)-using-TreeMap-to-keep-last-occurrence-Interview-%22follow-up%22-question!
     */
    public int lengthOfLongestSubstringKDistinct2(String str, int k) {
        if (str == null || str.isEmpty() || k == 0) {
            return 0;
        }
        TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
        Map<Character, Integer> inWindow = new HashMap<>();
        int j = 0;
        int max = 1;
        for (int i = 0; i < str.length(); i++) {
            char in = str.charAt(i);
            while (inWindow.size() == k && !inWindow.containsKey(in)) {
                int first = lastOccurrence.firstKey();
                char out = lastOccurrence.get(first);
                inWindow.remove(out);
                lastOccurrence.remove(first);
                j = first + 1;
            }
            //update or add in's position in both maps
            if (inWindow.containsKey(in)) {
                lastOccurrence.remove(inWindow.get(in));
            }
            inWindow.put(in, i);
            lastOccurrence.put(i, in);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }


    /**
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/discuss/80082/Solution-to-the-follow-up
     */
    public int lengthOfLongestSubstringKDistinct3(String s, int k) {
        if(s==null || s.length()==0 || k<=0) return 0;
        int len=s.length();
        int i=0, j=0;
        int maxLen=0;
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<Character,Integer>();
        for(char x:s.toCharArray()){
            if(map.containsKey(x)){
                map.remove(x);
                map.put(x,j);
            }else{
                if(map.size()==k){
                    maxLen=Math.max(maxLen,j-i);
                    char toRemove=map.keySet().iterator().next();
                    i=map.get(toRemove)+1;
                    map.remove(toRemove);
                }
                map.put(x,j);
            }
            j++;
        }
        maxLen=Math.max(maxLen,j-i);
        return maxLen;
    }

}
