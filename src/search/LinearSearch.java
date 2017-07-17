/**
 * LinearSearch: search an array of int linearly.
 *
 * Time complexity: O(n).
 * Space complexity: O(1).
 *
 * Start from the leftmost element of the array and one by one compare x with
 * each element.
 *    - If found, return the index;
 *    - Otherwise, not found, return -1.
 *
 */


public class LinearSearch {
    public static int search(int arr[], int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(LinearSearch.search(arr, 10));
        System.out.println(LinearSearch.search(arr, 1));
    }

}
