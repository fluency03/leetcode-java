/**
 * BucketSort: mainly sort an array with elements uniformly distributed over a range.
 *
 * Worst Case Time Complexity: O(n^2).
 * Best and Average Case Time Complexity: O(n + k).
 * Space Complexity: O(n * k).
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * http://www.growingwiththeweb.com/2015/06/bucket-sort.html
 */
public class BucketSort {

    private static final int DEFAULT_BUCKET_SIZE = 5;

    public static void sort(int[] arr) {
        bucketSort(arr, DEFAULT_BUCKET_SIZE);
    }

    public static void bucketSort(int arr[], int size) {
        if (arr.length == 0) return;

        Integer minValue = arr[0];
        Integer maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        int bucketCount = (maxValue - minValue) / size + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            buckets.get((arr[i] - minValue) / size).add(arr[i]);
        }

        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            InsertionSort.sort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                arr[currentIndex++] = bucketArray[j];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 3, 7, 5, 1, 15, 20};
        BucketSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {};
        BucketSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {10};
        BucketSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
