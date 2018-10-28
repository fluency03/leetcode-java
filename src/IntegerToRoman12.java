/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * 
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * 
 * There are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * 
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be
 * within the range from 1 to 3999.
 * 
 * Example 1:
 * Input: 3
 * Output: "III"
 * 
 * Example 2:
 * Input: 4
 * Output: "IV"
 * 
 * Example 3:
 * Input: 9
 * Output: "IX"
 * 
 * Example 4:
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * 
 * Example 5:
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

public class IntegerToRoman12 {
    //                                    1,   5,  10,  50,  100, 500, 1000
    private static char[] R = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int base = 0;
        while (num > 0) {
            int digit = num % 10;
            if (digit > 0) {
                char[] cur = curr(digit, base);
                sb.insert(0, cur);
            }
            num /= 10;
            base += 2;
        }
        return sb.toString();
    }

    private char[] curr(int digit, int base) {
        if (digit >= 1 && digit <= 3) {
            char[] res = new char[digit];
            Arrays.fill(res, R[base]);
            return res;
        } else if (digit == 4) {
            return new char[]{R[base], R[base+1]};
        } else if (digit == 5) {
            return new char[]{R[base+1]};
        } else if (digit >= 6 && digit <= 8) {
            char[] res = new char[digit-5+1];
            Arrays.fill(res, R[base]);
            res[0] = R[base+1];
            return res;
        } else { // 9
            return new char[]{R[base], R[base+2]};
        }
    }


    /**
     * https://leetcode.com/problems/integer-to-roman/discuss/6274/Simple-Solution
     */
    public static String intToRoman2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

}
