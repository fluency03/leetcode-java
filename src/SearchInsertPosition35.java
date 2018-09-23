/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 *
 * You may assume no duplicates in the array.
 *
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 */


public class SearchInsertPosition35 {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }


    /**
     * https://discuss.leetcode.com/topic/7874/my-8-line-java-solution
     */
    public int searchInsert2(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }


    public int searchInsert3(int[] nums, int target) {
        if (nums.length == 0) return 0;
        if (target > nums[nums.length-1]) return nums.length;
        int l = 0;
        int r = nums.length-1;
        
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

}
