/**
 * Given a set of distinct integers, nums, return all possible subsets.
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example,
 * If nums = [1,2,3], a solution is:
 *
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Subsets78 {

    /**
     * https://discuss.leetcode.com/topic/46159/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;
        
        res.add(new ArrayList<Integer>());
        for (Integer n: nums) {
            int size = res.size();
            for (int i=0; i<size; i++) { 
                List<Integer> newList = new ArrayList<Integer>(res.get(i));
                newList.add(n);
                res.add(newList);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Subsets78 ss = new Subsets78();

        System.out.println(ss.subsets(new int[]{1, 2, 3, 4}));
        System.out.println(ss.subsets(new int[]{1, 2, 3}));
    }


}
