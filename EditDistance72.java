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





public class EditDistance72 {
    // time: O(mn); space: O(mn)
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] d = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            d[i][0] = i;
        for (int i = 1; i <= n; i++)
            d[0][i] = i;

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


    // time: O(mn); space: O(m)


    public static void main(String[] args) {
        EditDistance72 ed = new EditDistance72();

        System.out.println(ed.minDistance("word1", "word3"));
        System.out.println(ed.minDistance("aaabbb", "aababb"));
        System.out.println(ed.minDistance("b", ""));
    }
}
