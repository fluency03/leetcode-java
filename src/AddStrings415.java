/**
 * Given two non-negative integers num1 and num2 represented as string, return
 * the sum of num1 and num2.
 * 
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 */

public class AddStrings415 {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int len1 = num1.length();
        int len2 = num2.length();
        int len = Math.max(len1, len2);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < len) {
            int a = (len1 - i - 1 < 0) ? 0 : chars1[len1 - i - 1] - '0';
            int b = (len2 - i - 1 < 0) ? 0 : chars2[len2 - i - 1] - '0';
            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
            i++;
        }
        if (carry > 0) sb.insert(0, Integer.toString(carry)); 
        return sb.toString();
    }


    /**
     * https://leetcode.com/problems/add-strings/
     */
    public String addStrings2(String num1, String num2) {
        char[] temp = new char[Math.max(num1.length(), num2.length())];
        int i = num1.length() - 1, j = num2.length() - 1;
        int index = temp.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int a = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = a + b + carry;
            temp[index--] = (char)(sum % 10 + '0');
            carry = sum / 10;
        }
        
        return carry == 0 ? new String(temp) : "1" + new String(temp);
    }

}
