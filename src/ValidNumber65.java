/**
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * 
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one.
 */

public class ValidNumber65 {
    public boolean isNumber(String s) {
        try {
            Double.valueOf(s);
        } catch (Exception e1) {
            return false;
        }
        char last = s.charAt(s.length()-1);
        if(last == 'D' || last == 'd' || last == 'f' || last == 'F') return false;
        return true;
    }
}
