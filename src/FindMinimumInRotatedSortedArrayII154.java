/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * Example 1:
 * Input: [1,3,5]
 * Output: 1
 * 
 * Example 2:
 * Input: [2,2,2,0,1]
 * Output: 0
 * 
 * Note:
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */


public class FindMinimumInRotatedSortedArrayII154 {
    public int findMin(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            if (nums[i] < nums[i-1]) return nums[i];
        }
        return nums[0];
    }


    public int findMin2(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }
    
    private int findMin(int[] nums, int i, int j) {
        if (i == j) return nums[i];
        if (nums[i] < nums[j]) return nums[i];
        int mid = (i + j) / 2;
        return Math.min(findMin(nums, i, mid), findMin(nums, mid+1, j));
    }


    public int findMin3(int[] nums) {
        int s = 0;
        int e = nums.length-1;
        while (s < e) {
            int mid = (s + e) / 2;
            if (nums[mid] < nums[e]) {
                e = mid;
            } else if (nums[mid] > nums[e]) {
                s = mid + 1;
            } else {
                e--;
            }
            
        }
        return nums[s];
    }

}
