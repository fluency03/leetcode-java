/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a
 * target number (target), find all unique combinations in candidates where
 * the candidate numbers sums to target.
 * 
 * The same repeated number may be chosen from candidates unlimited number of times.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            List<Integer> newPath = new ArrayList<Integer>();
            for (Integer i: path) newPath.add(i);
            res.add(newPath);
        }
    
        for (int i = start; i<candidates.length; i++) {
            int c = candidates[i];
            int count =  target / c;
            if (count >= 1) {
                for (int j=1; j<= count; j++) {path.add(c);}
                while (count >= 1) {
                    helper(candidates, target - c * count, i+1, path, res);
                    path.remove(path.size() - 1);
                    count--;
                }
            }
        }
    }
  
}
