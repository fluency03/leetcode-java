/**
 * InterpolationSearch: search x from a sorted array of n uniformly distributed values.
 *
 * Time complexity:
 *    - If elements are uniformly distributed, O(log(logn));
 *    - Worst case: O(n).
 * Space complexity: O(1).
 *
 * The Interpolation Search is an improvement over Binary Search for instances,
 * where the values in a sorted array are uniformly distributed. Binary Search
 * always goes to middle element to check. On the other hand interpolation
 * search may go to different locations according the value of key being
 * searched. For example if the value of key is closer to the last element,
 * interpolation search is likely to start search toward the end side.
 *
 * To find the position to be searched, it uses following formula.
 *
 * // The idea of formula is to return higher value of pos
 * // when element to be searched is closer to arr[hi]. And
 * // smaller value when closer to arr[lo]
 * pos = lo + [ (hi-lo) / (arr[hi]-arr[Lo]) * (x-arr[lo])]
 *
 * arr[] ==> Array where elements need to be searched
 * x     ==> Element to be searched
 * lo    ==> Starting index in arr[]
 * hi    ==> Ending index in arr[]
 *
 * Rest of the Interpolation algorithm is same except the above partition logic:
 *    Step1: In a loop, calculate the value of “pos” using the probe position formula.
 *    Step2: If it is a match, return the index of the item, and exit.
 *    Step3: If the item is less than arr[pos], calculate the probe position of
 *           the left sub-array. Otherwise calculate the same in the right sub-array.
 *    Step4: Repeat until a match is found or the sub-array reduces to zero.
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
