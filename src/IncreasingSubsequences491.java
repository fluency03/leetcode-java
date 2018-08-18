/**
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing
 * subsequence should be at least 2 .
 * 
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also
 * be considered as a special case of increasing sequence.
 */

public class IncreasingSubsequences491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, Integer.MIN_VALUE, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void helper(int[] nums, int pre, int start, List<Integer> path, List<List<Integer>> res) {
        if (path.size() >= 2) res.add(new ArrayList<>(path));
        if (start == nums.length) return;

        boolean[] visited = new boolean[201];
        for (int i=start; i<nums.length; i++) {
            if (visited[nums[i]+100]) continue;
            visited[nums[i]+100] = true;
            if (nums[i] >= pre) {
                path.add(nums[i]);
                helper(nums, nums[i], i+1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

}
