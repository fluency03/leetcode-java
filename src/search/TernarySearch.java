/**
 * LinearSearch: search a sorted array by resursively dividing the array into half.
 *
 * Time complexity: O(Logn).
 * Space complexity:
 *    - Recursively: O(Logn).
 *    - Iteratively: O(1).
 *
 * Every time, compare x with the middle element of the array.
 *    - If x matches with the middle element, return the mid index;
 *    - Else if x is greater than the mid element, go to right half of the array;
 *    - Otherwise, go to the left half.
 *
 */


public class TernarySearch {
    public static int searchRecursively(int arr[], int x) {
        return ternarySearch(arr, 0, arr.length - 1, x);
    }

    public static int searchIteratively(int arr[], int x) {
        return ternarySearch(arr, x);
    }

    // Recursively
    public static int ternarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid1 = l + (r - l)/3;
            int mid2 = mid1 + (r - l)/3;

            if (arr[mid1] == x)  return mid1;
            if (arr[mid2] == x)  return mid2;
            if (arr[mid1] > x) return ternarySearch(arr, l, mid1-1, x);
            if (arr[mid2] < x) return ternarySearch(arr, mid2+1, r, x);
            return ternarySearch(arr, mid1+1, mid2-1, x);
        }
        return -1;
    }

    // Iteratively
    public static int ternarySearch(int arr[], int x) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid1 = l + (r - l)/3;
            int mid2 = mid1 + (r - l)/3;

            if (arr[mid1] == x)  return mid1;
            if (arr[mid2] == x)  return mid2;

            if (arr[mid1] > x) {
                r = mid1 - 1;
                continue;
            }

            if (arr[mid2] < x) {
                l = mid2 + 1;
                continue;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(TernarySearch.searchIteratively(arr, 10));
        System.out.println(TernarySearch.searchIteratively(arr, 1));
    }

}
