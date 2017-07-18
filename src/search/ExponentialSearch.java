/**
 * ExponentialSearch: 1. Find range where element is present;
 *                    2. Do Binary Search in above found range.
 *
 * Time complexity: O(Logn).
 * Space complexity:
 *    - Recursively: O(Logn).
 *    - Iteratively: O(1).
 *
 *
 * Different from JumpSearch, where every jump is having fixed number of steps,
 * ExponentialSearch is having jumps with exponentially increasing number of steps.
 *
 * The target x will be within the range (i/2, i), where arr[i/2] < x < arr[i].
 *
 */

import java.util.Arrays;

public class ExponentialSearch {
    public static int search(int arr[], int x) {
        int n = arr.length;
        if (arr[0] == x) return 0;

        int i = 1;
        while (i < n && arr[i] <= x) i *= 2;

        return Arrays.binarySearch(arr, i/2, Math.min(i, n), x);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(ExponentialSearch.search(arr, 10));
        System.out.println(ExponentialSearch.search(arr, 1));
    }

}
