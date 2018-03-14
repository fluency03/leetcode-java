/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */

public class PermutationsII47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        perm(result, nums, 0);
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> l: result) res.add(l);
        return res;
    }

    private static void perm(Set<List<Integer>> result, int[] nums, int start){
        if (start == nums.length-1) {
            Integer[] ele = new Integer[nums.length];
            for(int i = 0; i < nums.length; i++){
                ele[i] = nums[i];
            }
            result.add(Arrays.asList(ele));
            return;
        }
        int pre = nums[start];
        for (int i = start; i < nums.length; i++) {
            if (start != i && (nums[start] == nums[i] || pre == nums[i])) continue;
            pre = nums[i];
            swap(nums, start, i);
            perm(result, nums, start + 1);
            swap(nums, start, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * https://leetcode.com/problems/permutations-ii/discuss/18648/Share-my-Java-code-with-detailed-explanantion
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
      	perm(result, nums, 0);
      	return result;
    }

    private static void perm(List<List<Integer>> result, int[] nums, int start){
      	if (start == nums.length-1) {
            Integer[] ele = new Integer[nums.length];
            for(int i = 0; i < nums.length; i++){
                ele[i] = nums[i];
            }
            result.add(Arrays.asList(ele));
            return;
        }
        Set<Integer> seen = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (seen.add(nums[i])) {
                swap(nums, start, i);
                perm(result, nums, start + 1);
                swap(nums, start, i);
            }
        }
    }


    /**
     * https://leetcode.com/problems/permutations-ii/discuss/18594/Really-easy-Java-solution-much-easier-than-the-solutions-with-very-high-vote
     */
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums==null || nums.length==0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs(nums, used, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if(list.size()==nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
            used[i]=true;
            list.add(nums[i]);
            dfs(nums,used,list,res);
            used[i]=false;
            list.remove(list.size()-1);
        }
    }


    /**
     * https://leetcode.com/problems/permutations-ii/discuss/18601/Short-iterative-Java-solution
     */
    public List<List<Integer>> permuteUnique4s(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<>();
            while (res.peekFirst().size() == i) {
                List<Integer> l = res.removeFirst();
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;
    }


}
