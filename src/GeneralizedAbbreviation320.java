/**
 * Write a function to generate the generalized abbreviations of a word. 
 * 
 * Note: The order of the output does not matter.
 * 
 * Example:
 * Input: "word"
 * Output:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
 * "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

public class GeneralizedAbbreviation320 {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        int N = word.length();
        
        backtrace(word.toCharArray(), res, true, 0, new StringBuilder());
        
        return res;
    }

    private void backtrace(char[] word, List<String> res, boolean prevIsWord, int i, StringBuilder sb) {
        if (word.length == i) {
            res.add(sb.toString());
            return;
        } 

        sb.append(word[i]);
        backtrace(word, res, true, i+1, sb);
        sb.deleteCharAt(sb.length() - 1);

        if (prevIsWord) {
            for (int j=i; j<word.length; j++) {
                int num = j-i+1;
                int len = sb.length();
                sb.append(num);
                backtrace(word, res, false, j+1, sb);
                sb.setLength(len);
            }
        }
    }


    /**
     * https://leetcode.com/problems/generalized-abbreviation/solution/
     */
    public List<String> generateAbbreviations2(String word) {
        List<String> ans = new ArrayList<>();
        for (int x = 0; x < (1 << word.length()); ++x) // loop through all possible x
            ans.add(abbr(word, x));
        return ans;
    }

    // build the abbreviation for word from number x
    private String abbr(String word, int x) {
        StringBuilder builder = new StringBuilder();
        int k = 0, n = word.length(); // k is the count of consecutive ones in x
        for (int i = 0; i < n; ++i, x >>= 1) {
            if ((x & 1) == 0) { // bit is zero, we keep word.charAt(i)
                if (k != 0) { // we have abbreviated k characters
                    builder.append(k);
                    k = 0; // reset the counter k
                }
                builder.append(word.charAt(i));
            }
            else // bit is one, increase k
                ++k;
        }
        if (k != 0) builder.append(k); //don't forget to append the last k if non zero
        return builder.toString();
    }

}
