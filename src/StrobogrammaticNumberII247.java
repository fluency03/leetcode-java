/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * Example:
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 */

public class StrobogrammaticNumberII247 {
    private char[] sames = new char[]{'0', '1', '8'};
    private char six = '6';
    private char nine = '9';
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        char[] chars = new char[n];
        findStrobogrammatic(chars, 0, n-1, res);
        return res;
    }
    
    public void findStrobogrammatic(char[] chars, int left, int right, List<String> res) {
        if (left > right) {
            res.add(new String(chars));
            return;
        }
        if (left == right) {
            for (char c: sames) {
                chars[left] = c;
                res.add(new String(chars));
            }
            return;
        }
        
        for (char c: sames) {
            if (c == '0' && left == 0) continue;
            chars[left] = c;
            chars[right] = c;
            findStrobogrammatic(chars, left+1, right-1, res);
        }
        chars[left] = six;
        chars[right] = nine;
        findStrobogrammatic(chars, left+1, right-1, res);
        chars[left] = nine;
        chars[right] = six;
        findStrobogrammatic(chars, left+1, right-1, res);
    }
  
}
