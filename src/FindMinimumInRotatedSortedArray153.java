/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * Input: [3,4,5,1,2] 
 * Output: 1
 * Example 2:
 * 
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * 
 */


public class FindMinimumInRotatedSortedArray153 {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }

    public int findMin(int[] nums, int s, int e) {
        if (nums[s] <= nums[e]) return nums[s];
        int mid = (s + e) / 2;
        return Math.min(findMin(nums, s, mid), findMin(nums, mid+1, e));
    }


    public int findMin2(int[] nums) {
        int s = 0;
        int e = nums.length-1;
        while (s < e) {
            int mid = (s + e) / 2;
            if (nums[mid] > nums[e]) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return nums[e];
    }

}