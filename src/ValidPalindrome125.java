/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric
 * characters and ignoring cases.
 *
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 *
 * Note:
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 *
 * For the purpose of this problem, we define empty string as valid palindrome.
 *
 */


public class ValidPalindrome125 {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        s = s.toLowerCase();
        while (l < r) {
            while (!isLowerAlphanumeric(s.charAt(l)) && l < r) {
                l++;
            }
            while (!isLowerAlphanumeric(s.charAt(r)) && l < r) {
                r--;
            }
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }

        return true;
    }

    private boolean isLowerAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }


    public boolean isPalindrome2(String s) {
        int l = 0;
        int r = s.length()-1;
        // s = s.toLowerCase();
        while (l < r) {
            while (!isAlphanumeric(s.charAt(l)) && l < r) {
                l++;
            }
            while (!isAlphanumeric(s.charAt(r)) && l < r) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }

        return true;
    }

    private boolean isAlphanumeric(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

}
