/**
 * In the "100 game," two players take turns adding, to a running total, any
 * integer from 1..10. The player who first causes the running total to reach
 * or exceed 100 wins.
 * 
 * What if we change the game so that players cannot re-use integers?
 * 
 * For example, two players might take turns drawing from a common pool of
 * numbers of 1..15 without replacement until they reach a total >= 100.
 * 
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players
 * play optimally.
 * 
 * You can always assume that maxChoosableInteger will not be larger than 20
 * and desiredTotal will not be larger than 300.
 * 
 * Example
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * Output:
 * false
 * 
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */

public class CanIWin464 {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true;
        if (((1 + maxChoosableInteger) / 2 * maxChoosableInteger) < desiredTotal) {
            return false;
        }
        return helper(new boolean[maxChoosableInteger], desiredTotal, new HashMap<>());
    }

    private boolean helper(boolean[] set, int desiredTotal, Map<String, Boolean> memo) {
        if (desiredTotal <= 0) return false;
        String k = setKey(set);
        if (memo.containsKey(k)) return memo.get(k);

        for (int i=set.length-1; i>=0; i--) {
            if (!set[i]) {
                set[i] = true;
                if (!helper(set, desiredTotal-i-1, memo)) {
                    set[i] = false;
                    memo.put(k, true);
                    return true;
                }
                set[i] = false;
            }
        }
        memo.put(k, false);
        return false;
    }

    private String setKey(boolean[] set) {
        StringBuilder sb = new StringBuilder();
        for (boolean b: set) {
            sb.append(b ? 't' : 'f');
        }
        return sb.toString();
    }



    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true;
        if (((1 + maxChoosableInteger) / 2 * maxChoosableInteger) < desiredTotal) {
            return false;
        }
        return helper(0, desiredTotal, new Boolean[1 << maxChoosableInteger], maxChoosableInteger);
    }

    private boolean helper(int state, int desiredTotal, Boolean[] memo, int M) {
        if (desiredTotal <= 0) return false;
        if (memo[state] != null) return memo[state];
        for (int i=M-1; i>=0; i--) {
            if ((state & (1 << i)) == 0) {
                state |= 1 << i;
                if (!helper(state, desiredTotal-i-1, memo, M)) {
                    state &= ~(1 << i);
                    memo[state] = true;
                    return true;
                }
                state &= ~(1 << i);
            }
        }
        memo[state] = false;
        return false;
    }


}
