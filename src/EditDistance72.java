/**
 * Given two words word1 and word2, find the minimum number of steps required
 * to convert word1 to word2. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */

import java.util.Arrays;

public class EditDistance72 {
    // time: O(mn); space: O(mn)
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) d[i][0] = i;
        for (int i = 1; i <= n; i++) d[0][i] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    d[i][j] = d[i-1][j-1];
                } else {
                    d[i][j] = Math.min(d[i-1][j], d[i][j-1]) + 1;
                    d[i][j] = Math.min(d[i][j], d[i-1][j-1] + 1);
                }
            }
        }

        return d[m][n];
    }


    // time: O(mn); space: O(n)
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (n == 0) {
            return m;
        }

        int[] d = new int[n + 1];

        for (int j = 0; j <= n; j++)
            d[j] = j;

        for (int i = 1; i <= m; i++) {
            int last = d[0];
            for (int j = 1; j <= n; j++) {
                int saveLast = d[j];
                d[0] = i;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    d[j] = last;
                } else {
                    d[j] = Math.min(d[j], d[j-1]) + 1;
                    d[j] = Math.min(d[j], last + 1);
                }
                last = saveLast;
            }
        }

        return d[n];
    }


    public int minDistance3(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        int[] dp = new int[len2+1];
        for (int j=0; j<=len2; j++) dp[j] = j;
        
        for (int i=1; i<=len1; i++) {
            int pre = dp[0];
            dp[0] = i;
            for (int j=1; j<=len2; j++) {
                int nextPre = dp[j];
                dp[j] = Math.min(pre + (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1),
                                   Math.min(dp[j], dp[j-1]) + 1);
                pre = nextPre;
            }
            
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        EditDistance72 ed = new EditDistance72();

        System.out.println(ed.minDistance2("word1", "word3"));
        System.out.println(ed.minDistance2("aaabbb", "aababb"));
        System.out.println(ed.minDistance2("b", ""));
        System.out.println(ed.minDistance2("", "b"));
    }
}
