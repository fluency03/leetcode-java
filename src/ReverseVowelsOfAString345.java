/**
 * Write a function that takes a string as input and reverse only the
 * vowels of a string.
 * 
 * Example 1:
 * Given s = "hello", return "holle".
 * 
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * 
 * Note:
 * The vowels does not include the letter "y".
 */

public class ReverseVowelsOfAString345 {
    private Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            while (i < j && !vowels.contains(chars[i])) i++;
            while (i < j && !vowels.contains(chars[j])) j--;
            if (i >= j) break;
    
            swap(chars, i, j);
            i++;
            j--;
        }
        return new String(chars);
    }
    
    
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

}
