/**
 * Given two strings S and T, return if they are equal when both are typed into
 * empty text editors. # means a backspace character.
 * 
 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * 
 * Example 2:
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * 
 * Example 3:
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * 
 * Example 4:
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * 
 * Note:
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * 
 * Follow up:
 * Can you solve it in O(N) time and O(1) space?
 */

public class BackspaceStringCompare844 {
    public boolean backspaceCompare(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
    
        int indexS = lenS-1;
        int countS = 0;
        int indexT = lenT-1;
        int countT = 0;
        while (indexS >= 0 && indexT >= 0) {
            char charS = S.charAt(indexS);
            char charT = T.charAt(indexT);
    
            if (charS != '#' && charT != '#' && countS == 0 && countT == 0) {
                if (charS != charT) return false;
                indexS--;
                indexT--;
            } else {
                if (charS == '#') {
                    countS++;
                    indexS--;
                } else {
                    if (countS != 0) {
                        countS--;
                        indexS--;
                    }
                }
                if (charT == '#') {
                    countT++;
                    indexT--;
                } else {
                    if (countT != 0) {
                        countT--;
                        indexT--;
                    }
                }
            }
        }
    
        while (indexS >= 0) {
            if (S.charAt(indexS) == '#') {
                countS++;
            } else {
                if (countS == 0) return false;
                countS--;
            }
            indexS--;
        }
        while (indexT >= 0) {
            if (T.charAt(indexT) == '#') {
                countT++;
            } else {
                if (countT == 0) return false;
                countT--;
            }
            indexT--;
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/backspace-string-compare/solution/
     */
    public boolean backspaceCompare2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }


    /**
     * https://leetcode.com/problems/backspace-string-compare/solution/
     */
    public boolean backspaceCompare3(String S, String T) {
      int i = S.length() - 1, j = T.length() - 1;
      int skipS = 0, skipT = 0;

      while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
          while (i >= 0) { // Find position of next possible char in build(S)
              if (S.charAt(i) == '#') {skipS++; i--;}
              else if (skipS > 0) {skipS--; i--;}
              else break;
          }
          while (j >= 0) { // Find position of next possible char in build(T)
              if (T.charAt(j) == '#') {skipT++; j--;}
              else if (skipT > 0) {skipT--; j--;}
              else break;
          }
          // If two actual characters are different
          if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
              return false;
          // If expecting to compare char vs nothing
          if ((i >= 0) != (j >= 0))
              return false;
          i--; j--;
      }
      return true;
  }

}
