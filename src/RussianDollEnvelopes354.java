/**
 * You have a number of envelopes with widths and heights given as a pair of
 * integers (w, h). One envelope can fit into another if and only if both the
 * width and height of one envelope is greater than the width and height of
 * the other envelope.
 * 
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * 
 * Note:
 * Rotation is not allowed.
 * 
 * Example:
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3 
 * Explanation: The maximum number of envelopes you can Russian doll
 * is 3 ([2,3] => [5,4] => [6,7]).
 */

public class RussianDollEnvelopes354 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                int wDiff = Integer.compare(e2[0], e1[0]);
                if (wDiff != 0) return wDiff;
                return -Integer.compare(e2[1], e1[1]);
            }
        };

        Arrays.sort(envelopes, comp);
        int N = envelopes.length;
        int[] dp = new int[N];
        int res = 1;
        for (int i=0; i<N; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if (isLarger(envelopes[j], envelopes[i]) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    private boolean isLarger(int[] e1, int[] e2) {
        return e1[0] > e2[0] && e1[1] > e2[1];
    }


    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                int wDiff = Integer.compare(e1[0], e2[0]);
                if (wDiff != 0) return wDiff;
                return Integer.compare(e2[1], e1[1]);
            }
        };

        Arrays.sort(envelopes, comp);
        int N = envelopes.length;
        int[] dp = new int[N];
        int res = 0;
        for (int i=0; i<N; i++) {
            int index = Arrays.binarySearch(dp, 0, res, envelopes[i][1]);
            if (index < 0) index = - index - 1;
            dp[index] = envelopes[i][1];
            if (index == res) res++;
        }
        return res;
    }

}
