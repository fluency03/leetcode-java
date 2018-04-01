/**
 * Write a function that takes a string as input and returns the string reversed.
 *
 * Example:
 * Given s = "hello", return "olleh".
 */

public class ReverseString344 {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length()-1;
        while (i < j) swap(chars, i++, j--);
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }


    /**
     * https://leetcode.com/problems/reverse-string/discuss/80937/JAVA-Simple-and-Clean-with-Explanations-6-Solutions
     */
    public String reverseString2(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            word[i] = (char) (word[i] ^ word[j]);
            word[j] = (char) (word[i] ^ word[j]);
            word[i] = (char) (word[i] ^ word[j]);
            i++;
            j--;
        }
        return new String(word);
    }


    /**
     * https://leetcode.com/problems/reverse-string/discuss/80937/JAVA-Simple-and-Clean-with-Explanations-6-Solutions
     */
    public String reverseString3(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
