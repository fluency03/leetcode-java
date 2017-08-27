/**
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 */


public class DecodeString394 {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder("");

        int i = 0;
        StringBuilder num = new StringBuilder("");
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num.append(c);
                i++;
                continue;
            }

            if (c == '[') {
                Res res = decodeString(s, i);
                int next = res.next;
                String in = res.in;
                for (int j = 0; j<Integer.parseInt(num.toString()); j++) {
                    sb.append(in);
                }
                num = new StringBuilder("");
                i = next;
                continue;
            }

            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(c);
                i++;
                continue;
            }

        }

        return sb.toString();
    }

    public Res decodeString(String s, int start) {
        StringBuilder sb = new StringBuilder("");

        int i = start+1;
        StringBuilder num = new StringBuilder("");
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num.append(c);
                i++;
                continue;
            }

            if (c == '[') {
                Res res = decodeString(s, i);
                int next = res.next;
                String in = res.in;
                for (int j = 0; j<Integer.parseInt(num.toString()); j++) {
                    sb.append(in);
                }
                num = new StringBuilder("");
                i = next;
                continue;
            }

            if (c == ']') {
                return new Res(i+1, sb.toString());
            }

            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(c);
                i++;
                continue;
            }

        }
        return new Res(i+1, sb.toString());
    }

    class Res {
        int next;
        String in;
        Res (int next, String in) {
            this.next = next;
            this.in = in;
        }
    }


    /**
     * https://discuss.leetcode.com/topic/57318/java-simple-recursive-solution
     */
    public String decodeString2(String s) {
        if (s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int digit_begin = i;
                while (s.charAt(i) != '[') i++;
                int num = Integer.valueOf(s.substring(digit_begin, i));
                int count = 1;
                int str_begin = i+1;
                i ++;
                while (count != 0) {
                    if (s.charAt(i) == '[') count ++;
                    else if (s.charAt(i) == ']') count --;
                    i ++;
                }
                i--;
                String str = decodeString(s.substring(str_begin, i));
                for (int j = 0; j < num; j ++) {
                    sb.append(str);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * https://discuss.leetcode.com/topic/57159/simple-java-solution-using-stack
     */
    public String decodeString3(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
