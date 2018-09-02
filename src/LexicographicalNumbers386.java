/**
 * Given an integer n, return 1 - n in lexicographical order.
 * 
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * Please optimize your algorithm to use less time and space.
 * The input size may be as large as 5,000,000.
 */

public class LexicographicalNumbers386 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i=1; i<=9; i++) {
            helper(i, res, n);
        }
        return res;
    }

    private void helper(int curr, List<Integer> res, int n) {
        if (curr > n) return;
        res.add(curr);
        for (int i=0; i<=9; i++) {
            helper(10*curr+i, res, n);
        }
    }


    /**
     * https://leetcode.com/problems/lexicographical-numbers/discuss/86269/Simple-Java-DFS-Solution-(beats-85-12-lines)
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>(n);
        //  from  1 to 9.
        //  0 is can't be a soution.
        dfs(1, 9, n, res);
        return res;
    }

    private void dfs(int start, int end, int n, List<Integer> res){
        // <= n make the solution can't bigger than n
        for (int i = start; i <= end && i <= n; i++){
            res.add(i);
            // 10 -> next recursion: 100(->next recursion 1000), 101,102....
            // next loop: 11 -> next recursion: 110,  111,112....
            // next loop: 12 -> next recursion: 120,  121,122....
            // from 0 to 9 different from the dfs call in method lexicalOrder
            dfs(i * 10, i * 10 + 9, n, res);
        }
    }


    /**
     * https://leetcode.com/problems/lexicographical-numbers/discuss/86242/Java-O(n)-time-O(1)-space-iterative-solution-130ms
     */
    public List<Integer> lexicalOrder3(int n) {
        List<Integer> res = new ArrayList<>();
        int curr = 1;
        for (int i=1; i<=n; i++) {
            res.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return res;
    }

}
