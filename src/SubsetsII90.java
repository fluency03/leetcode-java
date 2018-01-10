/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example,
 * If nums = [1,2,2], a solution is:
 *
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */


public class SubsetsII90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Map<Integer, Integer> co = new HashMap<>();
        helper(0, nums, co, res);
        return new ArrayList<List<Integer>>(res);
    }


    public void helper(int pos, int[] nums, Map<Integer, Integer> co, Set<List<Integer>> res) {
        if (pos >= nums.length) {
            res.add(mapToList(co));
            return;
        }
        res.add(mapToList(co));
        helper(pos+1, nums, co, res);
        co.put(nums[pos], co.getOrDefault(nums[pos], 0) + 1);
        helper(pos+1, nums, co, res);
        co.put(nums[pos], co.get(nums[pos]) - 1);
    }

    public List<Integer> mapToList(Map<Integer, Integer> co) {
        List<Integer> l = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> e: co.entrySet()) {
            for (int i=0; i<e.getValue(); i++) {
                l.add(e.getKey());
            }
        }
        return l;
    }






}
