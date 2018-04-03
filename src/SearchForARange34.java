/**
 * Given an array of integers sorted in ascending order, find the starting and
 * ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */



public class SearchForARange34 {
    public int[] searchRange(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            if (nums[start] < target) {
                start++;
            } else if (nums[end] > target) {
                end--;
            } else {
                break;
            }
        }

        if (start > nums.length - 1 || end < 0 || start > end) {
            return new int[]{-1, -1};
        }

        return new int[]{start, end};
    }



    public int[] searchRange2(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int mid = (end - start) >> 1 + start;

        while (start <= end) {
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                break;
            }

            mid = (end - start) / 2 + start;
        }

        while (start <= end) {
            if (nums[start] < target) {
                start++;
            } else if (nums[end] > target) {
                end--;
            } else {
                break;
            }
        }

        if (start > nums.length - 1 || end < 0 || start > end) {
            return new int[]{-1, -1};
        }

        return new int[]{start, end};
    }


    /**
     * https://discuss.leetcode.com/topic/6327/a-very-simple-java-solution-with-only-one-binary-search-algorithm
     */
    public int[] searchRange3(int[] A, int target) {
  		int start = Solution.firstGreaterEqual(A, target);
  		if (start == A.length || A[start] != target) {
  			return new int[]{-1, -1};
  		}
  		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
  	}

  	//find the first number that is greater than or equal to target.
  	//could return A.length if target is greater than A[A.length-1].
  	//actually this is the same as lower_bound in C++ STL.
  	private static int firstGreaterEqual(int[] A, int target) {
  		int low = 0, high = A.length;
  		while (low < high) {
  			int mid = low + ((high - low) >> 1);
  			//low <= mid < high
  			if (A[mid] < target) {
  				low = mid + 1;
  			} else {
  				//should not be mid-1 when A[mid]==target.
  				//could be mid even if A[mid]>target because mid<high.
  				high = mid;
  			}
  		}
  		return low;
  	}


    /**
     * https://discuss.leetcode.com/topic/10692/simple-and-strict-o-logn-solution-in-java-using-recursion
     */
    public int[] searchRange4(int[] A, int target) {
        int[] range = {A.length, -1};
        searchRange(A, target, 0, A.length - 1, range);
        if (range[0] > range[1]) range[0] = -1;
        return range;
    }

    public void searchRange(int[] A, int target, int left, int right, int[] range) {
        if (left > right) return;
        int mid = left + (right - left) / 2;
        if (A[mid] == target) {
            if (mid < range[0]) {
                range[0] = mid;
                searchRange(A, target, left, mid - 1, range);
            }
            if (mid > range[1]) {
                range[1] = mid;
                searchRange(A, target, mid + 1, right, range);
            }
        } else if (A[mid] < target) {
            searchRange(A, target, mid + 1, right, range);
        } else {
            searchRange(A, target, left, mid - 1, range);
        }
    }


    public int[] searchRange5(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        return searchRange(nums, target, 0, nums.length-1);
    }

    public int[] searchRange(int[] nums, int target, int lo, int hi) {
        if (lo > hi) return new int[]{-1, -1};

        int mid = (lo + hi) / 2;
        if (nums[mid] > target) {
            return searchRange(nums, target, lo, mid-1);
        } else if (nums[mid] < target) {
            return searchRange(nums, target, mid+1, hi);
        } else {
            int[] res = new int[]{mid, mid};
            int[] left = searchRange(nums, target, lo, mid-1);
            int[] right = searchRange(nums, target, mid+1, hi);
            if (left[0] != -1) res[0] = left[0];
            if (right[1] != -1) res[1] = right[1];
            return res;
        }
    }


    /**
     * https://leetcode.com/problems/search-for-a-range/solution/
     */
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo+hi)/2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange6(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

}
