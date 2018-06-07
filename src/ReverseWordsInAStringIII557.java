/**
 * Given a string, you need to reverse the order of characters in each word
 * within a sentence while still preserving whitespace and initial word order.
 * 
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Note: In the string, each word is separated by single space and there will
 * not be any extra space in the string.
 */


public class ReverseWordsInAStringIII557 {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            j = i;
            while (j < s.length() && chars[j] != ' ') {
                j++;
            }
            for (int k=0; k<(j-i)/2; k++) {
                swap(chars, i+k, j-k-1);
            }
            i = j+1;
        }
        
        return new String(chars);
    }
    
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
