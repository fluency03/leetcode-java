/**
 * 
 */

import java.util.*;

public class Subsequence {

    public List<String> subsequences(String target, String[] dict) {
        List<String> res = new ArrayList<>();
        for (String word: dict) {
            if (target.length() <= word.length() && isSubsequence(target, word)) {
                res.add(word);
            }
        }
        return res;
    }

    // public boolean isInsertable(String target, String word) {
    //     int M = target.length();
    //     int N = word.length();
    //     char[] charsT = target.toCharArray();
    //     char[] charsW = word.toCharArray();
    //     boolean[][] dp = new boolean[M+1][N+1];
    //     for (int i=0; i<=N; i++) dp[0][i] = true;
    //     for (int i=1; i<=M; i++) {
    //         for (int j=i; j<=N; j++) {
    //             dp[i][j] = (dp[i-1][j-1] && charsT[i-1] == charsW[j-1]) || (dp[i][j-1]);
    //         }
    //     }
    //     return dp[M][N];
    // }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        while (i < chars.length && j < chart.length) {
            if (chars[i] == chart[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == chars.length;
    }

    public static void main(String[] args) {
        Insertables ins = new Insertables();
        System.out.println(ins.insertables("google", new String[]{"goooooogle", "ddgoogle", "abcd", "googles"}));
    }

}
