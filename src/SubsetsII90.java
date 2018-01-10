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


    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> each = new ArrayList<>();
        helper2(0, nums, each, res);
        return new ArrayList<List<Integer>>(res);
    }

    public void helper2(int pos, int[] nums, List<Integer> each, Set<List<Integer>> res) {
        if (pos >= nums.length) {
            res.add(new ArrayList<Integer>(each));
            return;
        }
        res.add(new ArrayList<Integer>(each));
        helper(pos+1, nums, each, res);
        each.add(nums[pos]);
        helper(pos+1, nums, each, res);
        each.remove(each.size()-1);
    }


    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper3(0, nums, new ArrayList<>(), res);
        return res;
    }

    public void helper3(int pos, int[] nums, List<Integer> each, List<List<Integer>> res) {
        if (pos <= nums.length) res.add(new ArrayList<Integer>(each));

        for(int i=pos; i < nums.length; i++){
            if(i > pos && nums[i] == nums[i-1]) continue;
            each.add(nums[i]);
            helper(i+1, nums, each, res);
            each.remove(each.size()-1);
        }
    }


}
