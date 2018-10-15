/**
 * Solve a given equation and return the value of x in the form of string
 * "x=#value". The equation contains only '+', '-' operation, the variable x
 * and its coefficient.
 * 
 * If there is no solution for the equation, return "No solution".
 * 
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * 
 * If there is exactly one solution for the equation, we ensure that the value
 * of x is an integer.
 * 
 * Example 1:
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 * 
 * Example 2:
 * Input: "x=x"
 * Output: "Infinite solutions"
 * 
 * Example 3:
 * Input: "2x=x"
 * Output: "x=0"
 * 
 * Example 4:
 * Input: "2x+3x-6x=x+2"
 * Output: "x=-1"
 * 
 * Example 5:
 * Input: "x=x+2"
 * Output: "No solution"
 */

public class SolveTheEquation640 {
    public String solveEquation(String equation) {
        char[] chars = equation.toCharArray();
        int N = chars.length;
        int a = 0;
        int b = 0;
        int curr = 0;
        int sign = 1;
        int i = 0;
        while (chars[i] != '=') {
            if (Character.isDigit(chars[i])) {
                curr = curr * 10 + (int)(chars[i] - '0');
                if (chars[i+1] == '=') {
                    i += 2;
                    break;
                }
            } else if (chars[i] == '+') {
                b += sign * curr;
                curr = 0;
                sign = 1;
            } else if (chars[i] == '-') {
                b += sign * curr;
                curr = 0;
                sign = -1;
            } else {
                if (i == 0 || chars[i-1] != '0') {
                    a += sign * (curr == 0 ? 1 : curr);
                }
                curr = 0;
                i++;
                if (chars[i] != '=') {
                    sign = chars[i] == '+' ? 1 : -1;
                } else {
                    sign = 0;
                    i++;
                    break;
                }
            }
            i++;
        }
        if (sign != 0) {
            b += sign * curr;
        }

        curr = 0;
        sign = 1;
        while (i < N) {
            if (Character.isDigit(chars[i])) {
                curr = curr * 10 + (int)(chars[i] - '0');
            } else if (chars[i] == '+') {
                b -= sign * curr;
                curr = 0;
                sign = 1;
            } else if (chars[i] == '-') {
                b -= sign * curr;
                curr = 0;
                sign = -1;
            } else {
                if (i == 0 || chars[i-1] != '0') {
                    a -= sign * (curr == 0 ? 1 : curr);
                }
                curr = 0;
                i++;
                if (i != N) {
                    sign = chars[i] == '+' ? 1 : -1;
                }
            }
            i++;
        }
        b -= sign * curr;

        if (a == 0) {
            return b == 0 ? "Infinite solutions" : "No solution";
        } else {
            return "x=" + (- b / a);
        }
    }


    /**
     * https://leetcode.com/problems/solve-the-equation/discuss/105311/Concise-Java-Solution
     */
    public String solveEquation2(String equation) {
        int[] res = evaluateExpression(equation.split("=")[0]);
        int[] res2 = evaluateExpression(equation.split("=")[1]);
        res[0] -= res2[0];
        res[1] = res2[1] - res[1];
        if (res[0] == 0 && res[1] == 0) return "Infinite solutions";
        if (res[0] == 0) return "No solution";
        return "x=" + res[1]/res[0];
    }  

    public int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])"); 
        int[] res =  new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }

}
