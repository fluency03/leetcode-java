/**
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 *
 * Example:
 *
 * Input: "babad"
 *
 * Output: "bab"
 *
 * Note: "aba" is also a valid answer.
 * Example:
 *
 * Input: "cbbd"
 *
 * Output: "bb"
 */


import java.util.Arrays;

public class LongestPalindromicSubstring5 {
    public String longestPalindrome(String s) {
        char[] input = s.toCharArray();
        int length = input.length;
        if (length == 1) {
            return s;
        }

        String output = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                String temp = "";
                int left = i;
                int right = i + j;
                while (left >= 0 && right < length && input[left] == input[right]) {
                    left--;
                    right++;
                }
                left++;
                right--;
                if (output.length() < right - left + 1) {
                    output = String.valueOf(Arrays.copyOfRange(input, left, right+1));
                }
            }
        }

        return output;
    }


    /**
     * https://discuss.leetcode.com/topic/23498/very-simple-clean-java-solution
     */
    private int lo, maxLen;

    public String longestPalindrome2(String s) {
      	int len = s.length();
      	if (len < 2)
      		  return s;

        for (int i = 0; i < len-1; i++) {
         	  extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
         	  extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
      	while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
      		  j--;
      		  k++;
      	}
      	if (maxLen < k - j - 1) {
      		  lo = j + 1;
      		  maxLen = k - j - 1;
      	}
    }

}
