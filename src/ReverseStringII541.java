/**
 * Given a string and an integer k, you need to reverse the first k characters
 * for every 2k characters counting from the start of the string. If there are
 * less than k characters left, reverse all of them. If there are less than 2k
 * but greater than or equal to k characters, then reverse the first k
 * characters and left the other as original.
 * 
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * 
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 * 
 */

public class ReverseStringII541 {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        
        for (int i=0; i<=s.length()/k; i++) {
            if (i % 2 == 0) {
                reverse(chars, i * k, Math.min((i+1)*k - 1, s.length()-1));
            }
        }

        return new String(chars);

    }

    private void reverse(char[] chars, int left, int right) {
        int i = 0;
        while (i < (right-left+1)/2) {
            swap(chars, left+i, right-i);
            i++;
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
