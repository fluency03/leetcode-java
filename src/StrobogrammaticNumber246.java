/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Write a function to determine if a number is strobogrammatic. The number
 * is represented as a string.
 * 
 * Example 1:
 * Input:  "69"
 * Output: true
 * 
 * Example 2:
 * Input:  "88"
 * Output: true
 * 
 * Example 3:
 * Input:  "962"
 * Output: false
 */


public class StrobogrammaticNumber246 {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return true;
        boolean[][] map = new boolean[10][10];
        map[0][0] = true;
        map[1][1] = true;
        map[8][8] = true;
        map[6][9] = true;
        map[9][6] = true;
        int len = num.length();
        int l = 0;
        int r = len-1;
        char[] chars = num.toCharArray();
        while (l <= r) {
            if (!map[chars[l++]-'0'][chars[r--]-'0']) return false;
        }
        return true;
    }
}
