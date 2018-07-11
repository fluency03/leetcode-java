/**
 * Given an input string , reverse the string word by word. 
 * 
 * Example:
 * 
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 
 * Note: 
 * 
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * Follow up: Could you do it in-place without allocating extra space?
 * 
 */


public class ReverseWordsInAStringII186 {
    public void reverseWords(char[] str) {
        int i = 0;
        while (i < str.length/2) {
            swap(str, i, str.length-i-1);
            i++;
        }
        i = 0;
        while (i < str.length) {
            int j = i;
            while (j < str.length && str[j] != ' ') j++;
            int k = 0;
            while (k < (j-i)/2) {
                swap(str, i+k, j-k-1);
                k++;
            }
            i = j+1;
        }
        
    }
    
    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }


    public void reverseWords2(char[] str) {
        if (str == null || str.length <= 2) return;
        int len = str.length;
        int i = 0;
        int j = len - 1;
        int preI = i;
        int preJ = j;
        while (i <= j) {
            if (str[i] == ' ') {
                reverseOneWord(str, j+1, preJ);
                preJ = j-1;
            }
            if (str[j] == ' ') {
                reverseOneWord(str, preI, i-1);
                preI = i + 1;
            }
            swap(str, i++, j--);
        }
        if (preI < preJ) {
            reverseOneWord(str, preI, preJ);
        }
    }
    
    private void reverseOneWord(char[] str, int i, int j) {
        while (i < j) {
            swap(str, i++, j--);
        }
    }

}
