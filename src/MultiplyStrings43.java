/**
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2, also represented as a string.
 * 
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * 
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * 
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 */

public class MultiplyStrings43 {
    public String multiply(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append('0');
        for (int j=chars2.length - 1; j>=0; j--) {
            StringBuilder oneMul = multiplyForOne(chars1, chars2[j]);
            for (int z=0; z<chars2.length - 1 - j; z++) {
                oneMul.append('0');
            }
            sb = add(sb, oneMul);
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    private StringBuilder multiplyForOne(char[] chars, char ch) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int b = (int)(ch - '0');
        for (int i = chars.length-1; i>=0; i--) {
            int prod = (int)(chars[i] - '0') * b + carry;
            carry = prod / 10;
            sb.insert(0, Character.forDigit(prod % 10, 10));
        }
        if (carry != 0) sb.insert(0, Character.forDigit(carry, 10));
        return sb;
    }

    private StringBuilder add(StringBuilder sb1, StringBuilder sb2) {
        StringBuilder sb = new StringBuilder();
        int i = sb1.length()-1;
        int j = sb2.length()-1;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int prod = (int)(sb1.charAt(i--) - '0') + (int)(sb2.charAt(j--) - '0') + carry;
            carry = prod / 10;
            sb.insert(0, Character.forDigit(prod % 10, 10));
        }
        while (i >= 0) {
            int prod = (int)(sb1.charAt(i--) - '0')+ carry;
            carry = prod / 10;
            sb.insert(0, Character.forDigit(prod % 10, 10));
        }
        while (j >= 0) {
            int prod = (int)(sb2.charAt(j--) - '0') + carry;
            carry = prod / 10;
            sb.insert(0, Character.forDigit(prod % 10, 10));
        }
        if (carry != 0) sb.insert(0, Character.forDigit(carry, 10));
        return sb;
    }


    /**
     * https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
     */
    public String multiply2(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
      
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
    
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

}
