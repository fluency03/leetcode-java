/**
 * Given a string which consists of lowercase or uppercase letters, find the
 * length of the longest palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * 
 * Example:
 * Input:
 * "abccccdd"
 * Output:
 * 7
 * 
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

public class LongestPalindrome409 {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        int res = 0;
        boolean hasOdd = false;
        for (int i: map.values()) {
            if (i % 2 == 0) {
                res += i;
            } else {
                res += i - 1;
                hasOdd = true;
            }
        }
        return res + (hasOdd ? 1 : 0);
    }


    /**
     * https://leetcode.com/problems/longest-palindrome/discuss/89604/Simple-HashSet-solution-Java
     */
    public int longestPalindrome2(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
    }


    /**
     * https://leetcode.com/problems/longest-palindrome/discuss/89610/Simple-Java-Solution-in-One-Pass
     */
    public int longestPalindrome3(String s) {
        boolean[] map = new boolean[128];
        int len = 0;
        for (char c : s.toCharArray()) {
            map[c] = !map[c];         // flip on each occurrence, false when seen n*2 times
            if (!map[c]) len+=2;
        }
        if (len < s.length()) len++; // if more than len, atleast one single is present
        return len;
    }

}
