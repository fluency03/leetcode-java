/**
 * HeapSort: a sort algorithm based on max heap, where the largest item is
 * stored at the root of the heap.
 *
 * Time Complexity: O(Logn).
 * Space Complexity: O(n).
 *
 */

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/heap-sort/
 */
public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1};
        HeapSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        HeapSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        HeapSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
