/**
 * Given a collection of distinct numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Permutations46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        boolean[] selected = new boolean[nums.length];

        helper(results, result, nums, nums.length, selected);

        return results;
    }

    private void helper(List<List<Integer>> results, List<Integer> result, int[] nums, int length, boolean[] selected) {
        if (result.size() == length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (!selected[i]) {
                result.add(nums[i]);
                selected[i] = true;
                helper(results, result, nums, length, selected);
                result.remove(result.size() - 1);
                selected[i] = false;
            }
        }
    }


    /**
     * In this way, we are not using selected, saved space, but increased time!
     * result.contains(nums[i]) is with time O(n); but selected[i] = true is O(1)
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        helper(results, result, nums, nums.length);

        return results;
    }

    private void helper(List<List<Integer>> results, List<Integer> result, int[] nums, int length) {
        if (result.size() == length) {
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                helper(results, result, nums, length);
                result.remove(result.size() - 1);
            }
        }
    }


    /**
     * https://discuss.leetcode.com/topic/6377/my-ac-simple-iterative-java-python-solution
     */
    public List<List<Integer>> permute3(int[] num) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (num.length ==0) return ans;
        List<Integer> l0 = new ArrayList<Integer>();
        l0.add(num[0]);
        ans.add(l0);
        for (int i = 1; i< num.length; ++i){
            List<List<Integer>> new_ans = new ArrayList<List<Integer>>();
            for (int j = 0; j<=i; ++j){
                for (List<Integer> l : ans){
                    List<Integer> new_l = new ArrayList<Integer>(l);
                    new_l.add(j,num[i]);
                    new_ans.add(new_l);
                }
            }
            ans = new_ans;
        }
        return ans;
    }

    /**
     * https://discuss.leetcode.com/topic/23036/java-clean-code-two-recursive-solutions
     */
    public List<List<Integer>> permute4(int[] nums) {
     		List<List<Integer>> permutations = new ArrayList<>();
     		if (nums.length == 0) {
     			  return permutations;
     		}

     		collectPermutations(nums, 0, new ArrayList<>(), permutations);
     		return permutations;
    }

   	private void collectPermutations(int[] nums, int start, List<Integer> permutation,
    		List<List<Integer>>  permutations) {

     		if (permutation.size() == nums.length) {
       			permutations.add(permutation);
       			return;
     		}

     		for (int i = 0; i <= permutation.size(); i++) {
     			  List<Integer> newPermutation = new ArrayList<>(permutation);
     			  newPermutation.add(i, nums[start]);
     			  collectPermutations(nums, start + 1, newPermutation, permutations);
     		}
   	}


    /**
     * https://discuss.leetcode.com/topic/42417/2ms-java-solution-beats-93-i-think-it-could-be-optimized
     */
    public List<List<Integer>> permute5(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
      	perm(result, nums, 0, nums.length - 1);
      	return result;
    }
    public static void perm(List<List<Integer>> result, int[] nums, int start, int end){
      	if(start == end){
        		Integer[] ele = new Integer[nums.length];
        		for(int i = 0; i < nums.length; i++){
        			  ele[i] = nums[i];
        		}
        		result.add(Arrays.asList(ele));
      	}
      	else{
        		for(int i = start; i <= end; i++){
          			int temp = nums[start];
          			nums[start] = nums[i];
          			nums[i] = temp;

          			perm(result, nums, start + 1, end);

          			temp = nums[start];
          			nums[start] = nums[i];
          			nums[i] = temp;
        		}
      	}
    }


    public List<List<Integer>> permute6(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
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
        for (int i = start; i < nums.length; i++){
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

}
