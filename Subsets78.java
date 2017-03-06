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
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;


public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        Set<List<Integer>> returned = new HashSet<List<Integer>>();
        int size = nums.size();
        int mid = size / 2;

        subsetsOneSize(nums, mid, returned);
        return new ArrayList(returned);
    }

    private List<List<Integer>> subsetsOneSize(int[] nums, int i, List<List<Integer>> returned) {
        if (i == 0) {
            returned.add([]);
            returned.add(nums);
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            tmp.add([]);
            return tmp;
        }

        List<List<Integer>> tmp = subsetsOneSize(nums, i - 1, returned);




        return tmp;
    }

    public static void main(String[] args) {
        Subsets78 ss = new Subsets78();

        System.out.println(ss.subsets);
    }


}
