/**
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 *
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 *
 */


public class ExpressionAddOperators282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        if (num.charAt(0) == '0') {
            helper(num, target, res, "0", 1, 1, 0, 0L);
        } else {
           for (int i=0; i<num.length(); i++) {
                String first = num.substring(0, i+1);
                helper(num, target, res, first, i+1, i+1, 0, Long.parseLong(first));
            }
        }
        return res;
    }

    private void helper(String num, int target, List<String> res, String s, int i1, int i2, long pre, long last) {
        if (i2 >= num.length()) {
            if (i1 == i2 && (pre + last) == target) res.add(s);
            return;
        }

        String curr = num.substring(i1, i2+1);
        Long i = Long.parseLong(curr);
        if (!(i1 == i2 && curr.charAt(0) == '0')) {
            helper(num, target, res, s, i1, i2+1, pre, last);
        }
        helper(num, target, res, s+"+"+curr, i2+1, i2+1, pre+last, i);
        helper(num, target, res, s+"-"+curr, i2+1, i2+1, pre+last, -i);
        helper(num, target, res, s+"*"+curr, i2+1, i2+1, pre, last*i);
    }


    /**
     * https://discuss.leetcode.com/topic/35942/java-ac-solution-19ms-beat-100-00
     */
    public List<String> addOperators2(String num, int target) {
        List<String> ret = new LinkedList<>();
        if (num.length() == 0) return ret;
        char[] path = new char[num.length() * 2 - 1];
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            dfs(ret, path, i + 1, 0, n, digits, i + 1, target);
            if (n == 0) break;
        }
        return ret;
    }

    private void dfs(List<String> ret, char[] path, int len, long left, long cur, char[] digits, int pos, int target) {
        if (pos == digits.length) {
            if (left + cur == target) ret.add(new String(path, 0, len));
            return;
        }
        long n = 0;
        int j = len + 1;
        for (int i = pos; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            path[j++] = digits[i];
            path[len] = '+';
            dfs(ret, path, j, left + cur, n, digits, i + 1, target);
            path[len] = '-';
            dfs(ret, path, j, left + cur, -n, digits, i + 1, target);
            path[len] = '*';
            dfs(ret, path, j, left, cur * n, digits, i + 1, target);
            if (digits[pos] == '0') break;
        }
    }


}
