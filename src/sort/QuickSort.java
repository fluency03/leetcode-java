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
    public static void sortRecursively(int[] nums) {
        quickSortRecursively(nums, 0, nums.length - 1);
    }

    public static void sortIteratively(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        quickSortIteratively(nums, 0, nums.length - 1);
    }

    private static void quickSortRecursively(int[] nums, int left, int right) {
        if (left >= right) return;

        int p = partition(nums, left, right);
        quickSortRecursively(nums, left, p - 1);
        quickSortRecursively(nums, p + 1, right);
    }

    private static void quickSortIteratively(int arr[], int l, int h) {
        int stack[] = new int[h-l+1];

        int top = -1;
        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];

            int p = partition(arr, l, h);

            if (p-1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            if (p+1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
       }
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


    private static int partition2(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j=left; j<right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private static int partition3(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left+1;
        int j = right;
        while (true) {
            while (i <= j && nums[i] <= pivot) i++;
            while (i <= j && nums[j] > pivot) j--;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, left, j);
        return j;
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 20, 15, 1};
        QuickSort.sortIteratively(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        QuickSort.sortIteratively(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        QuickSort.sortIteratively(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
