/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Image: http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 *
 * Note:
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 *
 */


public class LetterCombinationsOfAPhoneNumber17 {
    /**
     * https://discuss.leetcode.com/topic/8465/my-java-solution-with-fifo-queue
     */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        int L = digits.length();
        if (L == 0) return ans;

        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }


    /**
     * https://discuss.leetcode.com/topic/6380/my-recursive-solution-using-java
     */
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations2(String digits) {
      List<String> ret = new LinkedList<String>();
          if (digits.length() == 0) return ret;

      combination("", digits, 0, ret);
      return ret;
    }

    private void combination(String prefix, String digits, int offset, List<String> ret) {
      if (offset >= digits.length()) {
        ret.add(prefix);
        return;
      }
      String letters = KEYS[(digits.charAt(offset) - '0')];
      for (int i = 0; i < letters.length(); i++) {
        combination(prefix + letters.charAt(i), digits, offset + 1, ret);
      }
    }



    private String[] alphas = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations4(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        String[] letters = new String[digits.length()];
        for (int i=0; i<digits.length(); i++) letters[i] = alphas[Character.getNumericValue(digits.charAt(i))];

        helper(res, letters, 0, new StringBuilder());

        return res;
    }

    private void helper(List<String> res, String[] letters, int i, StringBuilder sb) {
        if (i >= letters.length) {
            res.add(sb.toString());
            return;
        }

        char[] chars = letters[i].toCharArray();
        for (char c: chars) {
            sb.append(c);
            helper(res, letters, i+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

}
