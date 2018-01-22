/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note:
 * The input string length won't exceed 1000.
 *
 */


public class PalindromicSubstrings647 {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        for (int l=0; l<s.length(); l++) {
            dp[l][l] = true;
            res++;
        }

        for (int j=1; j<s.length(); j++) {
            for (int i=0; i<j; i++) {
                boolean sameSide = s.charAt(i) == s.charAt(j);
                boolean curr = (j == i+1) ? (sameSide) : (sameSide && dp[i+1][j-1]);
                if (curr) res++;
                dp[i][j] = curr;
            }
        }

        return res;
    }


    /**
     * https://discuss.leetcode.com/topic/96884/very-simple-java-solution-with-detail-explanation/4
     */
    int count =1;
    public int countSubstrings2(String s) {
        if(s.length()==0)
            return 0;
        for(int i=0; i<s.length()-1; i++){
            checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
            checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
        }
        return count;
    }

    private void checkPalindrome(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string
            count++;    //Increment the count if palindromin substring found
            i--;    //To trace string in left direction
            j++;    //To trace string in right direction
        }
    }

}
