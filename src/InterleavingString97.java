/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 *
 */


import java.util.Stack;


public class InterleavingString97 {
    // cannot pass time limit
    class Pair {
        int i;
        int j;
        int k;
        Pair(int i, int j, int k) {
            this.i = i;
        	this.j = j;
            this.k = k;
        }
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if ((l1 + l2) != l3) return false;

        Stack<Pair> track = new Stack<>();

        int i = 0;
        int j = 0;
        int k = 0;
        boolean b = true;
        while (k < l3) {
            if (i<l1 && b && s3.charAt(k) == s1.charAt(i)) {
                track.push(new Pair(i, j, k));
                i++;
                k++;
                continue;
            }
            if (j<l2 && s3.charAt(k) == s2.charAt(j)) {
                j++;
                k++;
                b = true;
                continue;
            }
            if (k == 0 || track.size() == 0) {
                return false;
            }

            Pair p = track.pop();
            i = p.i;
            j = p.j;
            k = p.k;
            b = false;
        }

        return true;
    }

    // DP
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if ((l1 + l2) != l3) return false;

		    boolean[][] dp = new boolean[l2+1][l1+1];
        dp[0][0] = true;

        int i = 1;
        int j = 1;
        for (i = 1; i<=l2; i++) {
            dp[i][0] = dp[i-1][0] && (s3.charAt(i-1) == s2.charAt(i-1));
        }
        for (j = 1; j<=l1; j++) {
            dp[0][j] = dp[0][j-1] && (s3.charAt(j-1) == s1.charAt(j-1));
        }

        for (i=1; i<=l2; i++) {
            for (j=1; j<=l1; j++) {
                dp[i][j] = (dp[i-1][j] && s3.charAt(i+j-1) == s2.charAt(i-1)) || (dp[i][j-1] && s3.charAt(i+j-1) == s1.charAt(j-1));
            }
        }
        for (i = 0; i<=l2; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[l2][l1];
    }


}
