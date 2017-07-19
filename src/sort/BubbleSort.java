/**
 * BubbleSort: sort an array by repeatedly swapping the adjacent elements if
 * they are in wrong order.
 *
 * Worst and Average Case Time Complexity: O(n^2).
 * Best Case Time Complexity: O(n).
 * Space Complexity: O(1)
 *
 */


import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++)
                if (arr[j] > arr[j+1]) swap(arr, j, j+1);
        }
    }

    public static void sortEarlyStop(int[] arr) {
        boolean swapped = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            if (swapped == false) break;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        BubbleSort.sortEarlyStop(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        BubbleSort.sortEarlyStop(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        BubbleSort.sortEarlyStop(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
