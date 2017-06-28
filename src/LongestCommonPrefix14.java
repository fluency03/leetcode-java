/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */


public class LongestCommonPrefix14 {
    public String longestCommonPrefix(String[] strs) {
        String lcp = "";
        int S = strs.length;
        if (S == 0) return lcp;

        int L = strs[0].length();
        int idx = 0;
        for (int i = 1; i < S; i++) {
            if (strs[i].length() < L) {
                L = strs[i].length();
                idx = i;
            }
        }
        if (L == 0) return lcp;

        int p = 1;
        String possible = strs[idx];
        while (p <= L) {
            String sub = possible.substring(0, p);
            boolean b = true;
            for (int k = 0; k < S; k++) {
                if (!strs[k].startsWith(sub)) {
                    b = false;
                    break;
                }
            }
            if (!b) break;
            lcp = sub;
            p++;
        }

        return lcp;
    }
}
