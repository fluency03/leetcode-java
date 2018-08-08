/**
 * Given an input string, reverse the string word by word.
 * 
 * Example:  
 * 
 * Input: "the sky is blue",
 * Output: "blue is sky the".
 * 
 * Note:
 * 
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed
 * string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in
 * the reversed string.
 */


public class ReverseWordsInAString151 {
    public String reverseWords(String s) {
        if (s == null)  return s;
        String[] words = s.replaceAll("^\\s+", "").split("\\s+", 0);
        int i = 0;
        while (i < words.length/2) {
            swap(words, i, words.length-i-1);
            i++;
        }

        return String.join(" ", words);
    }

    private void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    /**
     * https://leetcode.com/problems/reverse-words-in-a-string/discuss/47781/Java-3-line-builtin-solution
     */
    public String reverseWords2(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }


    public String reverseWords3(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        String[] array = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--){
            if(array[i].length() != 0){
                if (res.length() > 0){
                    res.append(" ");
                }
                res.append(array[i]);
            }
        }
        return res.toString();
    }


    /**
     * https://leetcode.com/problems/reverse-words-in-a-string/discuss/47720/Clean-Java-two-pointers-solution-(no-trim(-)-no-split(-)-no-StringBuilder)
     */
    public String reverseWords4(String s) {
        if (s == null) return null;
        
        char[] a = s.toCharArray();
        int n = a.length;
        
        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }
    
    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;
          
        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }
    
    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;
          
        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }
      
        return new String(a).substring(0, i);
    }
    
    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }


    public String reverseWords5(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.trim().toCharArray();
        int N = chars.length;
        char[] res = new char[N];
        int right = N - 1;
        int left = 0;
        while (right >= 0) {
            while (right >= 0 && chars[right] == ' ') {
                right--;
            }
            if (right < 0) break;
            int end = right;
            while (right >= 0 && chars[right] != ' ') {
                right--;
            }
            
            int i = right + 1;
            while (i <= end) {
                res[left++] = chars[i++];
            }
            if (right < 0) break;
            res[left++] = ' ';
        }
        return new String(res, 0, left);
    }

}

