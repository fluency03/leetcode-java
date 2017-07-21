/**
 * QuickSort: every time, it picks one element as a pivot and partitions the
 * array to two parts, where the elements of the first part before pivot are
 * always less or equal to the pivot, and the elements of the second part after.
 *
 * Worst Case Time Complexity: O(n^2).
 * Best and Average Case Time Complexity: O(nlogn).
 * Space Complexity: O(n).
 *
 * Different ways of picking the pivot:
 *
 *    - Always pick first element as pivot.
 *    - Always pick last element as pivot (implemented below)
 *    - Pick a random element as pivot.
 *    - Pick median as pivot.
 *    
 */

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = partition(nums, left, right);
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, right);
        return (i + 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1};
        QuickSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        QuickSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        QuickSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
