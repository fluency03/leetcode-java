/**
 * SelectionSort: sorts an array by repeatedly finding the minimum element from
 * unsorted part and putting it at the beginning.
 *
 * Time Complexity: O(n^2).
 * Space Complexity: O(1).
 *
 */

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] arr) {
        for (int i=0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length-1; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        SelectionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        SelectionSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        SelectionSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
