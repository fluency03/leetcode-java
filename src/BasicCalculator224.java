/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the
 * plus + or minus sign -, non-negative integers and empty spaces .
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * Note: Do not use the eval built-in library function.
 *
 */


public class BasicCalculator224 {
    public int calculate(String s) {
        int L = s.length();
        int result = 0;
        int start = 0;
        boolean isNum = false;
        boolean doPlus = true;
        for (int i=0; i<L; i++) {
            char c = s.charAt(i);
            if (isDigit(c) && !isNum) {
                start = i;
                isNum = true;
                continue;
            }
            if (isDigit(c)) continue;

            if (isNum) {
                result = doCal(s, result, start, i, doPlus);
                isNum = false;
            }
            if (isPlus(c)) {
                doPlus = true;
            } else if (isMunis(c)) {
                doPlus = false;
            } else if (isOpen(c)) {
                List<Integer> l = calculate(s, i+1, L);
                result = doCal(result, l.get(0), doPlus);
                i = l.get(1);
            }
        }
        if (isNum) {
            return doCal(s, result, start, L, doPlus);
        }

        return result;
    }

    private List<Integer> calculate(String s, int from, int L) {
        int result = 0;
        int start = from;
        int to = from;
        boolean isNum = false;
        boolean doPlus = true;
        for (int i=from; i<L; i++) {
            char c = s.charAt(i);
            if (isDigit(c) && !isNum) {
                start = i;
                isNum = true;
                continue;
            }
            if (isDigit(c)) continue;

            if (isNum) {
                result = doCal(s, result, start, i, doPlus);
                isNum = false;
            }
            if (isPlus(c)) {
                doPlus = true;
            } else if (isMunis(c)) {
                doPlus = false;
            } else if (isOpen(c)) {
                List<Integer> l = calculate(s, i+1, L);
                result = doCal(result, l.get(0), doPlus);
                i = l.get(1);
            } else {
                to = i;
                break;
            }
        }
        if (isNum) {
            return Arrays.asList(doCal(s, result, start, to, doPlus), to);
        }

        return Arrays.asList(result, to);
    }

    private int doCal(String s, int previous, int start, int end, boolean doPlus) {
        int num = Integer.parseInt(s.substring(start, end));
        return doCal(previous, num, doPlus);
    }

    private int doCal(int previous, int num, boolean doPlus) {
        if (doPlus) {
            return previous + num;
        } else {
            return previous - num;
        }
    }

    private boolean isPlus(char c) {
        return c == '+';
    }
    private boolean isMunis(char c) {
        return c == '-';
    }
    private boolean isOpen(char c) {
        return c == '(';
    }
    private boolean isClose(char c) {
        return c == ')';
    }
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    private boolean isSpace(char c) {
        return c == ' ';
    }


    /**
     * https://discuss.leetcode.com/topic/15816/iterative-java-solution-with-stack
     */
    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            }else if(c == '+'){
                result += sign * number;
                number = 0;
                sign = 1;
            }else if(c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if(number != 0) result += sign * number;
        return result;
    }

}
