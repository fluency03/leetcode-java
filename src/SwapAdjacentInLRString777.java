/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a
 * move consists of either replacing one occurrence of "XL" with "LX", or
 * replacing one occurrence of "RX" with "XR". Given the starting string start
 * and the ending string end, return True if and only if there exists a
 * sequence of moves to transform one string to the other.
 * 
 * Example:
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * 
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * 
 * Note:
 * 1 <= len(start) = len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 */


public class SwapAdjacentInLRString777 {
    public boolean canTransform(String start, String end) {
        int len = start.length();
        char[] starts = start.toCharArray();
        char[] ends = end.toCharArray();
        int ps = 0;
        int pe = 0;
        while (ps < len) {
            while (ps < len && starts[ps] == 'X') {
                ps++;
            }
            if (ps == len) break;

            while (pe < len && ends[pe] == 'X') {
                pe++;
            }
            if (pe == len) return false;

            if (starts[ps] == 'R') {
                if (ends[pe] != 'R' || pe < ps) return false;
            } else { // starts[ps] == 'L'
                if (ends[pe] != 'L' || pe > ps) return false;
            }
            ps++;
            pe++;
        }

        while (pe < len) {
            if (ends[pe++] != 'X') return false;
        }

        return true;
    }


    /**
     * https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/114737/Simple-Java-Solution
     */
    public boolean canTransform2(String start, String end) {
        int r = 0;
        int l = 0;
        for (int i = 0; i < start.length(); i++){
            if (start.charAt(i) == 'L') l++;
            if (start.charAt(i) == 'R') r++;
            if (end.charAt(i) == 'L') l--;
            if (end.charAt(i) == 'R') r--;
            if (l > 0 || r < 0 || (r > 0 && l != 0)) {
                return false;
            }
        }
        return l == 0 && r == 0;
    }


}
