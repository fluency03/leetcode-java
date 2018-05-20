/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */


public class AddBinary67 {
    public String addBinary(String a, String b) {
        return addBinary(a, b, a.length()-1, b.length()-1, 0) ;
    }

    private String addBinary(String a, String b, int i, int j, int carry) {
        if (i < 0 && j < 0) return (carry == 0) ? "" : "1";

        int sum = carry;
        if (i >= 0 && a.charAt(i) == '1') sum++;
        if (j >= 0 && b.charAt(j) == '1') sum++;

        return addBinary(a, b, i-1, j-1, (sum < 2) ? 0 : 1) + ((sum%2 == 0) ? "0" : "1");
    }



    public String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        addBinary(a, b, a.length()-1, b.length()-1, 0, sb) ;
        return sb.toString();
    }

    private void addBinary(String a, String b, int i, int j, int carry, StringBuilder sb) {
        if (i < 0 && j < 0) {
            sb.append((carry == 0) ? "" : "1");
            return;
        }

        int sum = carry;
        if (i >= 0 && a.charAt(i) == '1') sum++;
        if (j >= 0 && b.charAt(j) == '1') sum++;
        addBinary(a, b, i-1, j-1, (sum < 2) ? 0 : 1, sb);

        sb.append((sum%2 == 0) ? '0' : '1');
    }


    public String addBinary3(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length()-1;
        int j = b.length()-1;
        while (i >= 0 && j >= 0) {
            int sum = carry;
            if (a.charAt(i) == '1') sum++;
            if (b.charAt(j) == '1') sum++;
            carry = (sum < 2) ? 0 : 1;
            sb.insert(0, (sum%2 == 0) ? '0' : '1');
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = carry;
            if (a.charAt(i) == '1') sum++;
            carry = (sum < 2) ? 0 : 1;
            sb.insert(0, (sum%2 == 0) ? '0' : '1');
            i--;
        }

        while (j >= 0) {
            int sum = carry;
            if (b.charAt(j) == '1') sum++;
            carry = (sum < 2) ? 0 : 1;
            sb.insert(0, (sum%2 == 0) ? '0' : '1');
            j--;
        }

        if (carry == 1) sb.insert(0, '1');

        return sb.toString();
    }

    public String addBinary4(String a, String b) {
        char[] intToChar = new char[]{'0', '1'};
        int len = Math.max(a.length(), b.length());
        char[] res = new char[len + 1];
        int carry = 0;
        int s = len;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 && j >= 0) {
            int sum = charToInt(a.charAt(i--)) + charToInt(b.charAt(j--)) + carry;
            carry = sum >> 1;
            res[s--] = intToChar[sum & 1];
        }
        while (i >= 0) {
            int sum = charToInt(a.charAt(i--)) + carry;
            carry = sum >> 1;
            res[s--] = intToChar[sum & 1];
        }
        while (j >= 0) {
            int sum = charToInt(b.charAt(j--)) + carry;
            carry = sum >> 1;
            res[s--] = intToChar[sum & 1];
        }
        res[0] = intToChar[carry];
        return res[0] == '0' ? (new String(res)).substring(1) : new String(res);
    }
    
    private int charToInt(char c) {
        return c - '0';
    }

}
