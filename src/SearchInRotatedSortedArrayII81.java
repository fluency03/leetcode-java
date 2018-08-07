/**
 * Would this affect the run-time complexity? How and why?
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Write a function to determine if a given target is in the array.
 *
 * The array may contain duplicates.
 *
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 */


public class SearchInRotatedSortedArrayII81 {
    public boolean search(int[] nums, int target) {
        System.out.println(Arrays.toString(nums));
        int L = nums.length;
        if (L == 0) return false;
        int mid = L/2;

        if (nums[mid] == target) {
            return true;
        }

        return (search(Arrays.copyOfRange(nums, 0, mid), target) ||
            search(Arrays.copyOfRange(nums, mid+1, L), target));
    }


    // now, let's truncate it, and do not use array copy
    // public boolean search(int[] nums, int target) {
    //     int L = nums.length;
    //     if (L == 0) return false;
    //
    //     if (nums[0] < nums[L-1] && (nums[0] > target || nums[L-1] < target)) {
    //         return false;
    //     }
    //
    //     if (nums[0] > nums[L-1] && target < nums[0] && nums[L-1] < target) {
    //         return false;
    //     }
    //
    //     int mid = L/2;
    //     if (nums[mid] == target) {
    //         return true;
    //     }
    //
    //     return (search(Arrays.copyOfRange(nums, 0, mid), target) ||
    //         search(Arrays.copyOfRange(nums, mid+1, L), target));
    // }


    //now, let's truncate it, and do not use array copy
    public boolean search2(int[] nums, int target) {
        int L = nums.length;
        if (L == 0) return false;

        return searchHelper(nums, target, 0, L-1);
    }

    private boolean searchHelper(int[] nums, int target, int s, int e) {
        if (s > e) return false;

        int mid = (e-s)/2 + s;
        if (nums[mid] == target) {
            return true;
        }

        if (nums[s] < nums[e] && (nums[s] > target || nums[e] < target)) {
            return false;
        }

        if (nums[s] > nums[e] && target < nums[s] && nums[e] < target) {
            return false;
        }

        return (searchHelper(nums, target, s, mid-1) || searchHelper(nums, target, mid+1, e));
    }


    /**
     * https://discuss.leetcode.com/topic/25487/neat-java-solution-using-binary-search
     */
     public boolean search3(int[] nums, int target) {
         int start  = 0, end = nums.length - 1;

         //check each num so we will check start == end
         //We always get a sorted part and a half part
         //we can check sorted part to decide where to go next
         while(start <= end){
             int mid = start + (end - start)/2;
             if(nums[mid] == target) return true;

             //if left part is sorted
             if(nums[start] < nums[mid]){
                 if(target < nums[start] || target > nums[mid]){
                     //target is in rotated part
                     start = mid + 1;
                 }else{
                     end = mid - 1;
                 }
             }else if(nums[start] > nums[mid]){
                 //right part is sorted

                 //target is in rotated part
                 if(target < nums[mid] || target > nums[end]){
                     end = mid -1;
                 }else{
                     start = mid + 1;
                 }
             }else{
                 //duplicates, we know nums[mid] != target, so nums[start] != target
                 //based on current information, we can only move left pointer to skip one cell
                 //thus in the worest case, we would have target: 2, and array like 11111111, then
                 //the running time would be O(n)
                 start ++;
             }
         }

         return false;
     }


     public boolean search4(int[] nums, int target) {
         int L = nums.length;
         if (L == 0) return false;
         return searchHelper2(nums, target, 0, L-1);
     }

     private boolean searchHelper2(int[] nums, int target, int s, int e) {
         if (s > e) return false;

         int mid = (e+s)/2;
         if (nums[mid] == target) {
             return true;
         }

         if (nums[s] < nums[mid]) {
             if (target >= nums[s] && target <= nums[mid]) {
                 return searchHelper2(nums, target, s, mid-1);
             } else {
                 return searchHelper2(nums, target, mid+1, e);
             }
         } else if (nums[s] > nums[mid]) {
             if (target >= nums[mid] && target <= nums[e]) {
                 return searchHelper2(nums, target, mid+1, e);
             } else {
                 return searchHelper2(nums, target, s, mid-1);
             }
         } else {
             return searchHelper2(nums, target, s+1, e);
         }
     }


     /**
      * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28212/When-there-are-duplicates-the-worst-case-is-O(n).-Could-we-do-better
      */
     public boolean search5(int A[], int key) {
         int l = 0, r = A.length - 1;
         while (l <= r) {
             int m = l + (r - l)/2;
             if (A[m] == key) return true; //return m in Search in Rotated Array I
             if (A[l] < A[m]) { //left half is sorted
                 if (A[l] <= key && key < A[m])
                     r = m - 1;
                 else
                     l = m + 1;
             } else if (A[l] > A[m]) { //right half is sorted
                 if (A[m] < key && key <= A[r])
                     l = m + 1;
                 else
                     r = m - 1;
             } else l++;
         }
         return false;
     }


     public boolean search6(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            int midVal = nums[mid];
            if (midVal == target) return true;
            if (midVal > nums[start]) {
                if (target >= nums[start] && target < midVal) {
                    end = mid -1;
                } else {
                    start = mid + 1;
                }
            } else if (midVal < nums[end]) {
                if (target > midVal && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end= mid - 1;
                }
            } else {
                if (midVal == nums[start]) {
                    start++;
                }
                if (midVal == nums[end]) {
                    end--;
                }
            }
        }

        return nums[start] == target;
    }

}
