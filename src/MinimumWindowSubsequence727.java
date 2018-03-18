/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so
 * that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there are multiple such minimum-length windows, return
 * the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 *
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 */



public class MinimumWindowSubsequence727 {
    public String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) return "";
        char[] schars = S.toCharArray();
        char[] tchars = T.toCharArray();
        int sl = schars.length;
        int tl = tchars.length;

        int[][] n = new int[tl+1][sl+1];
        for (int j=0; j<=sl; j++) n[0][j] = 0;
        for (int i=1; i<=tl; i++) n[i][0] = -1;

        for (int i=1; i<=tl; i++) {
            for (int j=1; j<=sl; j++) {
                n[i][j] = (schars[j-1] == tchars[i-1] && n[i-1][j-1] != -1) ? j : -1;
                n[i][j] = Math.max(n[i][j], n[i][j-1]);
            }
        }

        int[] edges = new int[2];
        int diff = Integer.MAX_VALUE;
        int j = 1;
        while (j <= sl && n[tl][j] == -1) j++;
        while (j <= sl) {
            int lastJ = j;
            int i = tl;
            while (i > 1 && j > 1) {
                i--;
                j--;
                j = n[i][j];
            }
            if (i == 1 && j > 0 && diff > lastJ - j + 1) {
                edges[0] = j;
                edges[1] = lastJ;
                diff = lastJ - j + 1;
            }
            j = lastJ;
            while (j <= sl && n[tl][j] == lastJ) j++;
        }

        return (diff == Integer.MAX_VALUE) ? "" : S.substring(edges[0]-1, edges[1]);
    }



}
