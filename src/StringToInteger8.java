/**
 * Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 *
 * Notes: It is intended for this problem to be specified vaguely (ie, no given
 * input specs). You are responsible to gather all the input requirements up front.
 *
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of
 * this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty
 * or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned. If the
 * correct value is out of the range of representable values,
 * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *
 */



public class StringToInteger8 {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;

        long res = 0;  // Initialize result
        int sign = 1;  // Initialize sign as positive
        int i = 0;  // Initialize index of first digit
        int L = str.length();


        while (i < L && str.charAt(i) == ' ') {
            i++;
        }

        // If number is negative, then update sign
        boolean hasSign = false;
        while (i < L) {
            char c = str.charAt(i);
            if (c == '-') {
                if (hasSign) {
                    return 0;
                } else {
                    sign = -1;
                    i++;  // Also update index of first digit
                    hasSign = true;
                }
            } else if (c == '+') {
                if (hasSign) {
                    return 0;
                } else {
                    i++;  // Also update index of first digit
                    hasSign = true;
                }
            } else if (c == '0') {
                i++;  // Also update index of first digit
            } else {
                break;
            }
        }

        // Iterate through all digits of input string and update result
        for (; i < L; ++i) {
            if (isNumericChar(str.charAt(i)) == false) break;
            res = res * 10 + str.charAt(i) - '0';
            if (res > Integer.MAX_VALUE) break;
        }

        long r = sign*res;

        if (r > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (r < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int)r;
    }

    // A utility function to check whether x is numeric
    private boolean isNumericChar(char x) {
        return (x >= '0' && x <= '9') ? true : false;
    }



}
