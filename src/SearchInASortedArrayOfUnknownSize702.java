/**
 * Given an integer array sorted in ascending order, write a function to search
 * target in nums.  If target exists, then return its index, otherwise
 * return -1. However, the array size is unknown to you. You may only access
 * the array using an ArrayReader interface, where ArrayReader.get(k) returns
 * the element of the array at index k (0-indexed).
 * 
 * You may assume all integers in the array are less than 10000, and if you
 * access the array out of bounds, ArrayReader.get will return 2147483647.
 * 
 * Example 1:
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * Example 2:
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * 
 * Note:
 * You may assume that all elements in the array are unique.
 * The value of each element in the array will be in the range [-9999, 9999].
 */

public class SearchInASortedArrayOfUnknownSize702 {
    public int search(ArrayReader reader, int target) {
        return search(reader, target, 0, 10000);
    }

    private int search(ArrayReader reader, int target, int i, int j) {
        if (i >= j) return reader.get(i) == target ? i : -1;
        int mid = (i + j) / 2;
        int val = reader.get(mid);
        if (val == target) return mid;
        if (val > target) {
            return search(reader, target, i, mid-1);
        } else {
            return search(reader, target, mid+1, j);
        }
    }


    /**
     * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/discuss/151685/Shortest-and-cleanest-Java-solution-so-far...
     */
    public int search2(ArrayReader reader, int target) {
        int hi = 1;
        while (reader.get(hi) < target) {
            hi = hi << 1;
        }
        int low = hi >> 1;
        while (low <= hi) {
            int mid = low+(hi-low)/2;
            if (reader.get(mid) > target) {
                hi = mid-1;
            } else if (reader.get(mid) < target) {
                low = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public int search3(ArrayReader reader, int target) {
        int lo = 0;
        int hi = 20000;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int midVal = reader.get(mid);
            if (midVal == target) return mid;
            if (midVal == Integer.MAX_VALUE || midVal > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return reader.get(lo) == target ? lo : -1;
    }

}
