/**
 * Given an integer, write an algorithm to convert it to hexadecimal. For
 * negative integer, two’s complement method is used.
 * 
 * Note:
 * 
 * All letters in hexadecimal (a-f) must be in lowercase.
 * The hexadecimal string must not contain extra leading 0s. If the number is
 * zero, it is represented by a single zero character '0'; otherwise, the first
 * character in the hexadecimal string will not be the zero character.
 * The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * You must not use any method provided by the library which converts/formats
 * the number to hex directly.
 * 
 * Example 1:
 * Input:
 * 26
 * Output:
 * "1a"
 * 
 * Example 2:
 * Input:
 * -1
 * Output:
 * "ffffffff"
 */

public class ConvertANumberToHexadecimal405 {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        long n = num & 0x00000000ffffffffL;
        while (n > 0) {
            sb.insert(0, map[(int) (n % 16)]);
            n /= 16;
        }
        return sb.toString();
    }


    /**
     * https://leetcode.com/problems/convert-a-number-to-hexadecimal/discuss/89253/Simple-Java-solution-with-comment
     */
    public String toHex2(int num) {
        if(num == 0) return "0";
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result; 
            num = (num >>> 4);
        }
        return result;
    }

}
