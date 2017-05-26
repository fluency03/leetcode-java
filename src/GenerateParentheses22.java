/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GenerateParentheses22 {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<String>();
        } else if (n == 1) {
            List<String> temp = new ArrayList<String>();
            temp.add("()");
            return temp;
        } else {
            return addOnePair(generateParenthesis(n-1), n);
        }

    }

    private List<String> addOnePair(List<String> source, Integer n) {
        Set<String> returned = new HashSet<String>();
        for (String prev: source) {
            for (int i=0; i<n; i++) {
                returned.add(prev.substring(0, n-1+i) + "()" + prev.substring(n-1+i, 2*n-2));
            }
        }
        return new ArrayList(returned);
    }


    /** -------------------------------------------------------------------
     * Top Solution:
     * https://discuss.leetcode.com/topic/8724/easy-to-understand-java-backtracking-solution
     * --------------------------------------------------------------------
     */

    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    private void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    /** -------------------------------------------------------------------
     * Top Solution (same idea above, but more efficient):
     * https://discuss.leetcode.com/topic/8724/easy-to-understand-java-backtracking-solution
     * --------------------------------------------------------------------
     */

    public List<String> generateParenthesis3(int n) {
         List<String> res = new ArrayList<>();
         helper(res, new StringBuilder(), 0, 0, n);
         return res;
    }

    private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
        if(open == n && close == n) {
            res.add(sb.toString());
            return;
        }

        if(open < n) {
            sb.append("(");
            helper(res, sb, open+1, close, n);
            sb.setLength(sb.length()-1);
        }
        if(close < open) {
            sb.append(")");
            helper(res, sb, open, close+1, n);
            sb.setLength(sb.length()-1);
        }
    }


    public static void main(String[] args) {
        GenerateParentheses22 gp = new GenerateParentheses22();
        System.out.println(gp.generateParenthesis(3));
        System.out.println(gp.generateParenthesis2(3));
        System.out.println(gp.generateParenthesis3(3));
    }

}
