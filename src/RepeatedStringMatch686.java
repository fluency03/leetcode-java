/**
 * Given two strings A and B, find the minimum number of times A has to be
 * repeated such that B is a substring of it. If no such solution, return -1.
 * 
 * For example, with A = "abcd" and B = "cdabcdab".
 * 
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a
 * substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 * 
 * Note:
 * The length of A and B will be between 1 and 10000.
 */

public class RepeatedStringMatch686 {
    public int repeatedStringMatch(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        for (int i=0; i<lenA; i++) {
            if (A.charAt(i) == B.charAt(0)) {
                int bi = 0;
                int ai = i;
                int nr = 1;
                boolean found = true;
                while (bi < lenB) {
                    char bc = B.charAt(bi);
                    char ac = A.charAt(ai);
                    if (ac != bc) {
                        found = false;
                        break;
                    }
                    bi++;
                    if (bi == lenB) break;
                    if (ai == lenA - 1) {
                        ai = 0;
                        nr++;
                    } else {
                        ai++;
                    }
                }
                if (found) return nr;
            }
        }
        return -1;
    }


    /**
     * https://leetcode.com/problems/repeated-string-match/solution/
     */
    public int repeatedStringMatch2(String A, String B) {
        int q = 1;
        StringBuilder S = new StringBuilder(A);
        for (; S.length() < B.length(); q++) S.append(A);
        if (S.indexOf(B) >= 0) return q;
        if (S.append(A).indexOf(B) >= 0) return q+1;
        return -1;
    }


}
