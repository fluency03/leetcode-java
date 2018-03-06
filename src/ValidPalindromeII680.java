/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 *
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z.
 * The maximum length of the string is 50000.
 *
 */


public class ValidPalindromeII680 {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length()-1, false);
    }

    private boolean validPalindrome(String s, int l, int r, boolean flag) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
                continue;
            }

            if (flag) return false;
            if (l == r) return true;
            return validPalindrome(s, l+1, r, true) || validPalindrome(s, l, r-1, true);
        }

        return true;
    }

    // // stackoverflow
    // private boolean validPalindrome(String s, int l, int r, boolean flag) {
    //     if (l >= r) return true;
    //     if (s.charAt(l) == s.charAt(r)) {
    //         return validPalindrome(s, l+1, r-1, flag);
    //     } else if (!flag) {
    //         return validPalindrome(s, l+1, r, true) || validPalindrome(s, l, r-1, true);
    //     } else {
    //         return false;
    //     }
    // }

}
