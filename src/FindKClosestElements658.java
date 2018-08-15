/**
 * Given a sorted array, two integers k and x, find the k closest elements to x
 * in the array. The result should also be sorted in ascending order. If there
 * is a tie, the smaller elements are always preferred.
 * 
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * 
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * 
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 * 
 * UPDATE (2017/9/19):
 * The arr parameter had been changed to an array of integers (instead of a
 * list of integers). Please reload the code definition to get the latest
 * changes.
 */


public class FindKClosestElements658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos = binarySearch(arr, x, 0, arr.length-1);
        pos = updatePos(arr, x, pos);
        LinkedList<Integer> res = new LinkedList<>();
        int left = pos - 1;
        int right = pos;
        int i = 0;
        while (i < k) {
            if (left >= 0 && right < arr.length) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    res.addFirst(arr[left--]);
                } else {
                    res.addLast(arr[right++]);
                }
            } else if (left >= 0) {
                res.addFirst(arr[left--]);
            } else {
                res.addLast(arr[right++]);
            }
            i++;
        }
        return res;
    }

    public int binarySearch(int[] arr, int x, int lo, int hi) {
        if (lo == hi) return lo;
        if (hi - lo == 1) return hi;
        int mid = (lo + hi) / 2;
        if (arr[mid] == x) return mid;
        else if (arr[mid] > x) {
            return binarySearch(arr, x, lo, mid);
        } else {
            return binarySearch(arr, x, mid, hi);
        }
    }
    
    private int updatePos(int[] arr, int x, int pos) {
        if (arr[pos] == x || pos == 0) return pos;
        return (Math.abs(arr[pos-1]-x) <= Math.abs(arr[pos]-x)) ? pos - 1 : pos;
    }
  
    // /**
    //  * https://leetcode.com/problems/find-k-closest-elements/solution/
    //  */
    // public List<Integer> findClosestElements2(List<Integer> arr, int k, int x) {
    //     Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
    //     arr = arr.subList(0, k);
    //     Collections.sort(arr);
    //     return arr;
    // }


    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int N = arr.length;
        int start = 0;
        int end = N - k;

        /*
        0...............................mid.....|....mid+k................(N-k)
        
        arr[mid] >= x, end = mid
        
        arr[mid] < x:
            x - arr[mid] > arr[mid+k] - x, start = mid + 1;
            x - arr[mid] <= arr[mid+k] - x, end = mid
        */

        while (start < end) {
            int mid = (end + start) / 2;
            // if (arr[mid] >= x) {
            //     end = mid;
            // } else { // arr[mid] < x
                if (x - arr[mid] > arr[mid+k] - x) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            // }
        }
        
        for (int i=0; i<k; i++) {
            res.add(arr[start+i]);
        }
        return res;
    }


    public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr == null || arr.length == 0) return res;
        int idx = binarySearch(arr, x);
        int lo = idx - 1;
        int hi = idx;
        while (hi - lo - 1 < k) {
            if (lo < 0 && hi >= arr.length) break;
            if (lo < 0) {
                hi++;
            } else if (hi >= arr.length) {
                lo--;
            } else {
                if (Math.abs(arr[lo] - x) <= Math.abs(arr[hi] - x)) {
                    lo--;
                } else {
                    hi++;
                }
            }
        }
        
        for (int i=lo+1; i<hi; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    private int binarySearch(int[] arr, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < x) {
                lo++;
            } else {
                hi--;
            }
        }
        return lo;
    }

}
