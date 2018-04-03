/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */


public class SearchInRotatedSortedArray33 {
    public int search(int[] nums, int target) {
        int L = nums.length;
        if (L == 0) return -1;

        return searchHelper(nums, target, 0, L-1);
    }

    private int searchHelper(int[] nums, int target, int s, int e) {
        if (s > e) return -1;

        int mid = (e-s)/2 + s;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[s] < nums[e] && (nums[s] > target || nums[e] < target)) {
            return -1;
        }

        if (nums[s] > nums[e] && target < nums[s] && nums[e] < target) {
            return -1;
        }

        int left = searchHelper(nums, target, s, mid-1);
        if (left != -1) return left;

        return searchHelper(nums, target, mid+1, e);
    }


    /**
     * https://discuss.leetcode.com/topic/7711/revised-binary-search
     */
    public int search2(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        if (hi == -1) return -1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;

            if (A[lo] <= A[mid]) {
                if (target >= A[lo] && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return A[lo] == target ? lo : -1;
    }


    public int search3(int[] nums, int target) {
        int L = nums.length;
        if (L == 0) return -1;
        return searchHelper2(nums, target, 0, L-1);
    }

    private int searchHelper2(int[] nums, int target, int s, int e) {
        if (s > e) return -1;

        int mid = (e+s)/2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[s] <= nums[mid]) {
            if (target >= nums[s] && target <= nums[mid]) {
                return searchHelper2(nums, target, s, mid-1);
            } else {
                return searchHelper2(nums, target, mid+1, e);
            }
        }
        if (target >= nums[mid] && target <= nums[e]) {
            return searchHelper2(nums, target, mid+1, e);
        }

        return searchHelper2(nums, target, s, mid-1);
    }


}
