/**
 * RadixSort: sort an array of integers digit by digit sort starting from least
 * significant digit to most significant digit. For every single digit, counting
 * sort is considered.
 *
 * Time Complexity: O(wn) for n keys which are integers of word size w.
 * Space Complexity: O(w + N).
 *
 */

import java.util.Arrays;

public class RadixSort {
    public static void sort(int[] arr) {
        radixSort(arr, arr.length);
    }

    private static void radixSort(int arr[], int n) {
        int m = getMax(arr, n);

        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    private static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx) mx = arr[i];
        }
        return mx;
    }

    private static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];

        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i-1];

        for (i = n - 1; i >= 0; i--) {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1};
        MergeSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        MergeSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        MergeSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
