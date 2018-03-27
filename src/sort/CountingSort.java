/**
 * CountingSort: sort an array based on keys between a specific range. It works
 * by counting the number of objects having distinct key values, and based on
 * the counts, calculate the position of each element in the output array.
 *
 * It only works if you already know the range (of the distinct elements) of
 * your input array.
 *
 * Time Complexity: O(n+k).
 * Space Complexity: O(n+k).
 *
 * n is the number of elements in input array and k is the range of input.
 *
 * The following implementation is only working on non-negative keys.
 *
 */

import java.util.Arrays;

public class CountingSort {
    public static void sort(int arr[], int range) {
        int n = arr.length;
        int output[] = new int[n];
        final int R = range;

        int count[] = new int[R];
        for (int i=0; i<n; ++i)
            ++count[arr[i]];

        for (int i=1; i<=R-1; ++i)
            count[i] += count[i-1];

        for (int i = n-1; i>=0; i--) {
            output[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }

        for (int i = 0; i<n; ++i)
            arr[i] = output[i];
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1};
        CountingSort.sort(arr1, 100);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        CountingSort.sort(arr2, 100);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        CountingSort.sort(arr3, 100);
        System.out.println(Arrays.toString(arr3));
    }
}
