/**
 * Given a string of numbers and operators, return all possible results from
 * computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 *
 * Example 1
 * Input: "2-1-1".
 *
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 *
 *
 * Example 2
 * Input: "2*3-4*5"
 *
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 *
 */


public class DifferentWaysToAddParentheses241 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if (c != '-' && c != '*' && c != '+' ) continue;
            List<Integer> res1 = diffWaysToCompute(input.substring(0, i));
            List<Integer> res2 = diffWaysToCompute(input.substring(i+1));
            for (Integer int1: res1) {
                for (Integer int2: res2) {
                    res.add(cal(int1, int2, c));
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    private Integer cal(Integer int1, Integer int2, Character op) {
        switch (op) {
            case '+':
                return int1 + int2;
            case '-':
                return int1 - int2;
            case '*':
                return int1 * int2;
        }
        return 0;
    }
}
