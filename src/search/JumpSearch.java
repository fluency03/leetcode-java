/**
 * JumpSearch: search from an sorted array by jumping ahead fixed number of steps every time.
 *
 * Time complexity:
 *    - O(√n). Between Linear Search O(n) and Binary Search O(Logn).
 *    - Wosrt case: (n/m) + m-1, where n is number of elements, m is number of steps every time.
 * Space complexity: O(1).
 *
 * Start from the leftmost element of the array, and jump m number of elements
 * every time then compare x with current element.
 *    - If found, return the index;
 *    - Else if current is less than x, keep jumping;
 *    - Else if current is larger than x, do linear search between last element and current one;
 *    - Otherwise, not found, return -1.
 *
 */


public class JumpSearch {
    public static int search(int[] arr, int x) {
        int n = arr.length;
        int step = (int)Math.floor(Math.sqrt(n));

        int last = 0;
        int now = step;
        while (arr[Math.min(now, n)-1] < x) {
            last = now;
            now += step;
            if (last >= n) return -1;
        }

        while (arr[last] < x) {
            last++;
            if (last == Math.min(now, n)) return -1;
        }

        if (arr[last] == x) return last;

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 10, 15, 20};
        System.out.println(JumpSearch.search(arr, 10));
        System.out.println(JumpSearch.search(arr, 1));
    }

}
