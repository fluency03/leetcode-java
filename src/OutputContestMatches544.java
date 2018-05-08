/**
 * During the NBA playoffs, we always arrange the rather strong team to play
 * with the rather weak team, like make the rank 1 team play with the rank nth
 * team, which is a good strategy to make the contest more interesting. Now,
 * you're given n teams, you need to output their final contest matches in the
 * form of a string.
 * 
 * The n teams are given in the form of positive integers from 1 to n, which
 * represents their initial rank. (Rank 1 is the strongest team and Rank n is
 * the weakest team.) We'll use parentheses('(', ')') and commas(',') to
 * represent the contest team pairing - parentheses('(' , ')') for pairing and
 * commas(',') for partition. During the pairing process in each round, you
 * always need to follow the strategy of making the rather strong one pair with
 * the rather weak one.
 * 
 * Example 1:
 * Input: 2
 * Output: (1,2)
 * Explanation: 
 * Initially, we have the team 1 and the team 2, placed like: 1,2.
 * Then we pair the team (1,2) together with '(', ')' and ',', which is the
 * final answer.
 * 
 * Example 2:
 * Input: 4
 * Output: ((1,4),(2,3))
 * Explanation: 
 * In the first round, we pair the team 1 and 4, the team 2 and 3 together, as
 * we need to make the strong team and weak team together.
 * And we got (1,4),(2,3).
 * In the second round, the winners of (1,4) and (2,3) need to play again to
 * generate the final winner, so you need to add the paratheses outside them.
 * And we got the final answer ((1,4),(2,3)).
 * 
 * Example 3:
 * Input: 8
 * Output: (((1,8),(4,5)),((2,7),(3,6)))
 * Explanation: 
 * First round: (1,8),(2,7),(3,6),(4,5)
 * Second round: ((1,8),(4,5)),((2,7),(3,6))
 * Third round: (((1,8),(4,5)),((2,7),(3,6)))
 * Since the third round will generate the final winner, you need to output the
 * answer (((1,8),(4,5)),((2,7),(3,6))).
 *
 * Note:
 * The n is in range [2, 212].
 * We ensure that the input n can be converted into the form 2k, where k is a
 * positive integer.
 */

class OutputContestMatches544 {
    // O(NlogN). Each of O(logN) rounds performs O(N) work.
    public String findContestMatch(int n) {
        String[] arr = new String[n];
        for (int i=1; i<=n; i++) arr[i-1] = String.valueOf(i);

        while (n/2 >= 1) {
            int h = n/2;

            for (int i=1; i<=h; i++) {
                arr[i-1] = combine(arr[i-1], arr[n-i]);
            }

            n /= 2;
        }
        return arr[0];
    }

    private String combine(String l, String r) {
        return "(" + l + "," + r + ")";
    }


    /**
     * https://leetcode.com/problems/output-contest-matches/solution/
     */
    // O(N). We print each of O(N) characters in order.
    int[] team;
    int t;
    StringBuilder ans;
    public String findContestMatch2(int n) {
        team = new int[n];
        t = 0;
        ans = new StringBuilder();
        write(n, Integer.numberOfTrailingZeros(n));
        return ans.toString();
    }

    public void write(int n, int round) {
        if (round == 0) {
            int w = Integer.lowestOneBit(t);
            team[t] = w > 0 ? n / w + 1 - team[t - w] : 1;
            ans.append("" + team[t++]);
        } else {
            ans.append("(");
            write(n, round - 1);
            ans.append(",");
            write(n, round - 1);
            ans.append(")");
        }
    }

    
    /**
     * https://leetcode.com/problems/output-contest-matches/discuss/101228/3-ms-Java-Recursive-clean-code
     */
    public String findContestMatch3(int n) {
        StringBuilder sb = new StringBuilder();
        helper(sb, 3, n, 1);
        return sb.toString();
    }

    void helper(StringBuilder sb, int sum, int n, int val) {
        if (sum > n + 1) {
            sb.append(val);
            return;
        }
        sb.append('(');
        helper(sb, (sum << 1) - 1, n, val);
        sb.append(',');
        helper(sb, (sum << 1) - 1, n, sum - val);
        sb.append(')');        
    }

}