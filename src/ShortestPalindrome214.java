/**
 * Given a string s, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * Example 1:
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * 
 * Example 2:
 * Input: "abcd"
 * Output: "dcbabcd"
 */


public class ShortestPalindrome214 {
    public String shortestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) return s;
        String res = null;
        char[] chars = s.toCharArray();
        for (int r=len-1; r>=0; r--) {
            if (shortestPalindrome(chars, 0, r)) {
                StringBuilder sb = new StringBuilder();
                for (int i=len-1; i>r; i--) {
                    sb.append(chars[i]);
                }
                sb.append(chars);
                return sb.toString();
            }
        }
        return "";
    }
    
    private boolean shortestPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }


    public String shortestPalindrome2(String s) {
        int len = s.length();
        if (len <= 1) return s;
        String rev = new StringBuilder(s).reverse().toString();
        char[] chars = (s + "#" + rev).toCharArray();
        int[] prefix = prefix(chars);
        return  rev.substring(0, len - prefix[prefix.length - 1]) + s;
    }
    
    private int[] prefix(char[] chars) {
        int len = chars.length;
        int[] res = new int[len];
        res[0] = 0;
        for (int i=1; i<len; i++) {
            int j = res[i-1];
            while (j > 0 && chars[i] != chars[j]) {
                j = res[j-1];
            }
            if (chars[i] == chars[j]) {
                j++;
            }
            res[i] = j;
        }
        return res;
    }

}
