/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all
 * valid but "(]" and "([)]" are not.
 */


import java.util.Map;
import java.util.HashMap;
import java.util.Stack;


public class ValidParentheses20 {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();

        Stack st = new Stack();

        for (char c: chars) {

            if (isBackP(c)) {
                if (st.empty()) {
                    return false;
                } else {
                    char top = (char) st.pop();
                    if (c != top) {
                        return false;
                    }
                }
            } else {
                st.push(pair(c));
            }
        }

        return st.empty();
    }

    private boolean isBackP(char c) {
        return c == '}' || c == ']' || c == ')';
    }

    private char pair(char c) {
        if (c == '{') {
            return '}';
        } else if (c == '[') {
            return ']';
        } else {
            return ')';
        }
    }


    /**
     * https://discuss.leetcode.com/topic/27572/short-java-solution
     */
    public boolean isValid2(String s) {
      	Stack<Character> stack = new Stack<Character>();
      	for (char c : s.toCharArray()) {
        		if (c == '(')
        			  stack.push(')');
        		else if (c == '{')
        			  stack.push('}');
        		else if (c == '[')
        			  stack.push(']');
        		else if (stack.isEmpty() || stack.pop() != c)
        			  return false;
      	}
      	return stack.isEmpty();
    }



    public boolean isValid3(String s) {
        if (s == null || s.length()%2 == 1) return false;
        if (s.length() == 0) return true;

        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
                continue;
            }

            if (st.isEmpty()) return false;

            switch (c) {
                case ')':
                    if (st.peek() == '(') {
                        st.pop();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (st.peek() == '[') {
                        st.pop();
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (st.peek() == '{') {
                        st.pop();
                        break;
                    } else {
                        return false;
                    }
                default: return false;
            }

        }

        return st.isEmpty();
    }


    public static void main(String[] args) {
        ValidParentheses20 vp = new ValidParentheses20();

        System.out.println(vp.isValid("["));
        System.out.println(vp.isValid("]"));
        System.out.println(vp.isValid("[]"));
    }

}
