/**
 * Given a non-empty string s and an abbreviation abbr, return whether the
 * string matches with the given abbreviation.
 * 
 * A string such as "word" contains only the following valid abbreviations:
 * 
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
 * "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * Notice that only the above abbreviations are valid abbreviations of the
 * string "word". Any other string is not a valid abbreviation of "word".
 * 
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase
 * letters and digits.
 * 
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 * Return true.
 * 
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 * Return false.
 */

public class ValidWordAbbreviation408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr.length() == 0) return word.length() == 0;
        char[] wordChars = word.toCharArray();
        char[] abbrChars = abbr.toCharArray();
        int wLen = word.length();
        int aLen = abbr.length();
        int i = 0;
        int j = 0;
        while (i < wLen && j < aLen) {
            if (isDigit(abbrChars[j])) {
                if (abbrChars[j] == '0') return false;
                int preJ = j;
                while (j < aLen && isDigit(abbrChars[j])) {
                    j++;
                }
                int num = Integer.valueOf(abbr.substring(preJ, j));
                i += num;
                if (i > wLen) return false;
            } else {
                if (wordChars[i] != abbrChars[j]) return false;
                i++;
                j++;
            }
        }
        return i == wLen && j == aLen;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
