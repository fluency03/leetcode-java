/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 */


public class MedianOfTwoSortedArrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = 0;
        int r1 = nums1.length-1;
        int l2 = 0;
        int r2 = nums2.length-1;
        int L = 0;
        int R = 0;

        while (l1 <= r1 && l2 <= r2) {
            if (nums1[l1] <= nums2[l2]) {
                L = nums1[l1];
                l1++;
            } else {
                L = nums2[l2];
                l2++;
            }

            if (nums1[r1] <= nums2[r2]) {
                R = nums2[r2];
                r2--;
            } else {
                R = nums1[r1];
                r1--;
            }
        }

        if (l1 > r1 && l2 > r2) {
            return (L + R) / 2.0;
        } else if (l1 > r1) {
            int mid = (l2 + r2) / 2;
            if ((l2 + r2)%2 == 0) {
                return nums2[mid];
            } else {
                return (nums2[mid] + nums2[mid+1]) / 2.0;
            }
        } else {
            int mid = (l1 + r1) / 2;
            if ((l1 + r1)%2 == 0) {
                return nums1[mid];
            } else {
                return (nums1[mid] + nums1[mid+1]) / 2.0;
            }
        }
    }

    /**
     * https://leetcode.com/articles/median-of-two-sorted-arrays/
     */
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }


}
