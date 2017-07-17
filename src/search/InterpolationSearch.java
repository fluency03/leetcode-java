/**
 * InterpolationSearch: search x from a sorted array of n uniformly distributed values.
 *
 * Time complexity:
 *    - If elements are uniformly distributed, O(log(logn));
 *    - Worst case: O(n).
 * Space complexity: O(1).
 *
 * The Interpolation Search is an improvement over Binary Search, where the
 * values in a sorted array are uniformly distributed. Instead of always going
 * to the middle element, interpolation search may go to a different location
 * based on the value of value being searched. For example, if the value is
 * closer to the last element, interpolation search is likely to start search
 * toward the end side.
 *
 * To find the position to be searched, instead of
 *    mid = l + (r-l)/2
 * it uses following formula:
 *    pos = lo + [ (hi-lo) / (arr[hi]-arr[Lo]) * (x-arr[lo])]
 *
 */


public class InterpolationSearch {
    public static int search(int[] arr, int x) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            int pos = lo + (((hi-lo)/(arr[hi]-arr[lo]))*(x-arr[lo]));

            if (arr[pos] == x) return pos;

            if (arr[pos] > x) {
                hi = pos - 1;
            } else {
                lo = pos + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(InterpolationSearch.search(arr, 10));
        System.out.println(InterpolationSearch.search(arr, 1));
    }

}
