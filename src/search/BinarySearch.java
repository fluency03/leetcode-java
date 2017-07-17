/**
 * LinearSearch: search a sorted array by resursively dividing the array into half.
 *
 * Time complexity: O(Logn).
 * Space complexity:
 *    - Recursively: O(Logn).
 *    - Iteratively: O(1).
 *
 * Everytime, compare x with the middle element of the array.
 *    - If x matches with the middle element, return the mid index;
 *    - Else if x is greater than the mid element, go to right half of the array;
 *    - Otherwise, go to the left half.
 *
 */


public class BinarySearch {
    public static int search(int arr[], int x) {
        // Recursively
        return binarySearch(arr, 0, arr.length - 1, x);
        // Iteratively
        // return binarySearch(arr, x);
    }

    // Recursively
    private static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l)/2;

            if (arr[mid] == x) return mid;

            if (arr[mid] > x) return binarySearch(arr, l, mid-1, x);

            return binarySearch(arr, mid+1, r, x);
        }

        return -1;
    }

    // Iteratively
    private static int binarySearch(int arr[], int x) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r-l)/2;

            if (arr[m] == x) return m;

            if (arr[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(BinarySearch.search(arr, 10));
        System.out.println(BinarySearch.search(arr, 1));
    }

}
