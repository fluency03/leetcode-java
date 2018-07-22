/**
 * Find all possible combinations of k numbers that add up to a number n, given
 * that only numbers from 1 to 9 can be used and each combination should be a
 * unique set of numbers.
 * 
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * 
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

public class CombinationSumIII216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3(k, n, new boolean[9], new ArrayList<>(), 0, 1, res);
        return res;
    }

    private void combinationSum3(int k, int n, List<Integer> path, int sum, int start, List<List<Integer>> res) {
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }
        for (int i=start; i<=9; i++) {
            path.add(i);
            combinationSum3(k, n, visited, path, sum + i, i+1, res);
            path.remove(path.size()-1);
        }
    }

}
