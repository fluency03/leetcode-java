/**
 * InsertionSort: for every element in the array, insert it into sorted sequence iteratively.
 *
 * Time Complexity: O(n^2).
 * Space Complexity: O(1).
 *
 */


import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i=1; i < arr.length; i++) {
            int curr = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > curr) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = curr;
        }
    }

    public static void sort(Integer[] arr) {
        for (int i=1; i < arr.length; i++) {
            int curr = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > curr) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = curr;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        InsertionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        InsertionSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        InsertionSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
