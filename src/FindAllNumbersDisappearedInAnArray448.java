/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
 * elements appear twice and others appear once.
 * 
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [5,6]
 */

public class FindAllNumbersDisappearedInAnArray448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length <= 1) return new ArrayList<>();
        int len = nums.length;
        int i = 0;
        while (i < len) {
            int curr = nums[i];
            while (curr != i + 1) {
                int next = nums[curr-1];
                if (curr == next) break;
                swap(nums, i, curr-1);
                curr = nums[i];
            }
            i++;
        }
        List<Integer> res = new ArrayList<>();
        for (int j=0; j<len; j++) {
            if (nums[j] != j+1) {
                res.add(j+1);
            }
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
  
}
