/**
 * Given a non-negative integer represented as a non-empty array of digits,
 * plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the
 * number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head
 * of the list.
 */

public class PlusOne66 {
    public int[] plusOne(int[] digits) {
        int[] res = new int[digits.length+1];
        int c = 1;
        for (int i=digits.length-1; i>=0; i--) {
            int s = digits[i] + c;
            c = s / 10;
            res[i+1] = s % 10;
        }
        res[0] = c;
        return (res[0] != 0) ? res : Arrays.copyOfRange(res, 1, res.length);
    }


    /**
     * https://leetcode.com/problems/plus-one/discuss/24082/My-Simple-Java-Solution
     */
    public int[] plusOne2(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newNumber = new int [n+1];
        newNumber[0] = 1;
        return newNumber;
    }

}
