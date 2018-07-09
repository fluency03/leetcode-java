/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Note:
 * 
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would
 * always evaluate to a result and there won't be any divide by zero operation.
 * 
 * Example 1:
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * 
 * Example 2:
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * 
 * Example 3:
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation: 
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */

public class EvaluateReversePolishNotation150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String t: tokens) {
            switch (t) {
                case "+":
                    Integer i1 = stack.pop();
                    Integer i2 = stack.pop();
                    stack.push(i2 + i1);
                    break;
                case "-":
                    Integer i3 = stack.pop();
                    Integer i4 = stack.pop();
                    stack.push(i4 - i3);
                    break;
                case "*":
                    Integer i5 = stack.pop();
                    Integer i6 = stack.pop();
                    stack.push(i6 * i5);
                    break;
                case "/":
                    Integer i7 = stack.pop();
                    Integer i8 = stack.pop();
                    stack.push(i8 / i7);
                    break;
                default:
                    stack.push(Integer.valueOf(t));
            }
        }
        return stack.pop();
    }

}

