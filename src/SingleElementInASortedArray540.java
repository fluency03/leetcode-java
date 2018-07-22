/**
 * Given a sorted array consisting of only integers where every element appears
 * twice except for one element which appears once. Find this single element
 * that appears only once.
 * 
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * 
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 * 
 * Note: Your solution should run in O(log n) time and O(1) space.
 */

public class SingleElementInASortedArray540 {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        return singleNonDuplicate(nums, 0, nums.length-1);
    }

    private int singleNonDuplicate(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo]; 
        int mid = (lo + hi) / 2;
        boolean b = (mid - lo) % 2 == 0;
        if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
            return nums[mid];
        }
        if (nums[mid] != nums[mid-1]) {
            if (b) {
                return singleNonDuplicate(nums, mid, hi);
            } else {
                return singleNonDuplicate(nums, lo, mid-1);
            }
            
        }
        
        if (b) {
            return singleNonDuplicate(nums, lo, mid);
        } else {
            return singleNonDuplicate(nums, mid+1, hi);
        }
    }


    private int singleNonDuplicate2(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo]; 
        int mid = (lo + hi) / 2;
        boolean b = (mid - lo) % 2 == 0;
        if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
            return nums[mid];
        }

        if ((nums[mid] == nums[mid-1]) == b) {
            if (nums[mid] != nums[mid-1]) mid--;
            return singleNonDuplicate2(nums, lo, mid);
        }

        if (nums[mid] == nums[mid-1]) mid++;
        return singleNonDuplicate2(nums, mid, hi);
    }


    private int singleNonDuplicate3(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo]; 
        int mid = (lo + hi) / 2;
        if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) return nums[mid];
        if (mid % 2 == 1) mid--;
        if (nums[mid] != nums[mid+1]) return singleNonDuplicate3(nums, lo, mid);
        return singleNonDuplicate3(nums, mid+2, hi);
    }


    /**
     * https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
     */
    public static int singleNonDuplicate2(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            // Example:
            // Index: 0 1 2 3 4 5 6
            // Array: 1 1 3 3 4 8 8
            //            ^
            int mid = (start + end) / 2;
            if (mid % 2 == 1) mid--;

            // We didn't find a pair. The single element must be on the left.
            // (pipes mean start & end)
            // Example: |0 1 1 3 3 6 6|
            //               ^ ^
            // Next:    |0 1 1|3 3 6 6
            if (nums[mid] != nums[mid + 1]) end = mid;

            // We found a pair. The single element must be on the right.
            // Example: |1 1 3 3 5 6 6|
            //               ^ ^
            // Next:     1 1 3 3|5 6 6|
            else start = mid + 2;
        }

        // 'start' should always be at the beginning of a pair.
        // When 'start > end', start must be the single element.
        return nums[start];
    }

}


