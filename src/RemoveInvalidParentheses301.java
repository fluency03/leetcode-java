/**
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 *      "()())()" -> ["()()()", "(())()"]
 *      "(a)())()" -> ["(a)()()", "(a())()"]
 *      ")(" -> [""]
 */


/**
 * A review of all solutions (BFS and DFS)
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75038/Evolve-from-intuitive-solution-to-optimal-a-review-of-all-solutions
 *
 * There are three challenges:
 *   - Remove minimum parenthesis
 *   - The result is valid
 *   - Without duplicates.
 *
 */


public class RemoveInvalidParentheses301 {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> results = new HashSet<>();
        int L = s.length();
        if (L == 0) return Arrays.asList("");

        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder("");

        int maxLength = helper(s, 0, st, sb, L, results);

        return setToList(results, maxLength);
    }

    private int helper(String s, int level, Stack<Character> st, StringBuilder sb, int L, Set<String> results) {
        int maxLength = 0;
        if (level == L) {
            if (st.size() == 0) {
                maxLength = Math.max(maxLength, sb.length());
                results.add(sb.toString());
            }
            return maxLength;
        }

        char c = s.charAt(level);
        int localMax = 0;
        if (c >= 'a' && c <= 'z') {
            sb.append(c);
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
            sb.deleteCharAt(sb.length()-1);
        } else if (c == '(') {
            st.push('(');
            sb.append('(');
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
            st.pop();
            sb.deleteCharAt(sb.length()-1);
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
        } else if (c == ')' && !st.empty() && st.peek() == '(') {
            st.pop();
            sb.append(c);
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
            st.push('(');
            sb.deleteCharAt(sb.length()-1);
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
        } else {
            localMax = helper(s, level+1, st, sb, L, results);
            maxLength = Math.max(maxLength, localMax);
        }

        return maxLength;
    }


    private List<String> setToList(Set<String> set, int maxLength) {
        List<String> results = new ArrayList<>();
        for (String s: set) {
            if (s.length() == maxLength) {
                results.add(s);
            }
        }
        return results;
    }



    /**
     * https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }


    /**
     * https://discuss.leetcode.com/topic/28827/share-my-java-bfs-solution
     */
    public List<String> removeInvalidParentheses3(String s) {
      List<String> res = new ArrayList<>();

      // sanity check
      if (s == null) return res;

      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();

      // initialize
      queue.add(s);
      visited.add(s);

      boolean found = false;

      while (!queue.isEmpty()) {
        s = queue.poll();

        if (isValid(s)) {
          // found an answer, add to the result
          res.add(s);
          found = true;
        }

        if (found) continue;

        // generate all possible states
        for (int i = 0; i < s.length(); i++) {
          // we only try to remove left or right paren
          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

          String t = s.substring(0, i) + s.substring(i + 1);

          if (!visited.contains(t)) {
            // for each state, if it's not visited, add it to the queue
            queue.add(t);
            visited.add(t);
          }
        }
      }

      return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
      int count = 0;

      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        if (c == ')' && count-- == 0) return false;
      }

      return count == 0;
    }


    /**
     * https://discuss.leetcode.com/topic/30743/easiest-9ms-java-solution
     */
    public List<String> removeInvalidParentheses4(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<String>(res);
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
        	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
        	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

        } else {
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);
        }

        sb.setLength(len);
    }


    /**
     * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75095/Java-optimized-DFS-solution-3-ms
     */
    public List<String> removeInvalidParentheses5(String s) {
         int count = 0, openN = 0, closeN = 0;

         // calculate the total numbers of opening and closing parentheses
         // that need to be removed in the final solution
         for (char c : s.toCharArray()) {
             if (c == '(') {
                 count++;
             } else if (c == ')') {
                 if (count == 0) closeN++;
                 else count--;
             }
         }
         openN = count;
         count = 0;

         if (openN == 0 && closeN == 0) return Arrays.asList(s);

         List<String> result = new ArrayList<>();
         StringBuilder sb = new StringBuilder();

         dfs(s.toCharArray(), 0, count, openN, closeN, result, sb);

         return result;
     }

     private void dfs(char[] s, int p, int count, int openN, int closeN, List<String> result, StringBuilder sb) {
         if (count < 0) return; // the parentheses is invalid

         if (p == s.length) {
             if (openN == 0 && closeN == 0) { // the minimum number of invalid parentheses have been removed
                 result.add(sb.toString());
             }
             return;
         }

         if (s[p] != '(' && s[p] != ')') {
             sb.append(s[p]);
             dfs(s, p + 1, count, openN, closeN, result, sb);
             sb.deleteCharAt(sb.length() - 1);
         } else if (s[p] == '(') {
             int i = 1;
             while (p + i < s.length && s[p + i] == '(') i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
             sb.append(s, p, i);
             dfs(s, p + i, count + i, openN, closeN, result, sb);
             sb.delete(sb.length() - i, sb.length());

             if (openN > 0) {
                 // remove the current opening parenthesis
                 dfs(s, p + 1, count, openN - 1, closeN, result, sb);
             }
         } else {
             int i = 1;
             while (p + i < s.length && s[p + i] == ')') i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
             sb.append(s, p, i);
             dfs(s, p + i, count - i, openN, closeN, result, sb);
             sb.delete(sb.length() - i, sb.length());

             if (closeN > 0) {
                 // remove the current closing parenthesis
                 dfs(s, p + 1, count, openN, closeN - 1, result, sb);
             }
         }
     }


}
