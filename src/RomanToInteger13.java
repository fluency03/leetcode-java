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
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There
 * are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 * 
 * Example 1:
 * Input: "III"
 * Output: 3
 * 
 * Example 2:
 * Input: "IV"
 * Output: 4
 * 
 * Example 3:
 * Input: "IX"
 * Output: 9
 * 
 *  Example 4:
 * Input: "LVIII"
 * Output: 58
 * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
 * 
 * Example 5:
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */


public class RomanToInteger13 {
    public int romanToInt(String s) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    if (i+1 < s.length()) {
                        char next = s.charAt(i+1);
                        if (next == 'V') {
                            res += 4;
                            i += 2;
                        } else if (next == 'X') {
                            res += 9;
                            i += 2;
                        } else {
                            res += 1;
                            i++;
                        }
                    } else {
                        res += 1;
                        i++;
                    }
                    break;
                case 'V':
                    res += 5;
                    i++;
                    break;
                case 'X':
                    if (i+1 < s.length()) {
                        char next = s.charAt(i+1);
                        if (next == 'L') {
                            res += 40;
                            i += 2;
                        } else if (next == 'C') {
                            res += 90;
                            i += 2;
                        } else {
                            res += 10;
                            i++;
                        }
                    } else {
                        res += 10;
                        i++;
                    }
                    break;
                case 'L':
                    res += 50;
                    i++;
                    break;
                case 'C':
                    if (i+1 < s.length()) {
                        char next = s.charAt(i+1);
                        if (next == 'D') {
                            res += 400;
                            i += 2;
                        } else if (next == 'M') {
                            res += 900;
                            i += 2;
                        } else {
                            res += 100;
                            i++;
                        }
                    } else {
                        res += 100;
                        i++;
                    }
                    break;
                case 'D':
                    res += 500;
                    i++;
                    break;
                case 'M':
                    res += 1000;
                    i++;
                    break;
                default:
                    return 0;
            }
        }
        
        return res;
    }


    /**
     * https://leetcode.com/problems/roman-to-integer/discuss/6509/7ms-solution-in-Java.-easy-to-understand
     */
    public int romanToInt2(String s) {
        int nums[]=new int[s.length()];
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }
        int sum=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1])
                sum-=nums[i];
            else
                sum+=nums[i];
        }
        return sum+nums[nums.length-1];
    }


}