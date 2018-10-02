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
                String numString = Integer.toString(num);
                sb.append(numString);
                backtrace(word, res, false, j+1, sb);
                sb.delete(sb.length()-numString.length(), sb.length());
            }
        }
    }


}
