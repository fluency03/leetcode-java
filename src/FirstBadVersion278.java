/**
 * You are a product manager and currently leading a team to develop a new
 * product. Unfortunately, the latest version of your product fails the quality
 * check. Since each version is developed based on the previous version, all
 * the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
 * bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether
 * sversion is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 *
 */


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion278 extends VersionControl {
    // time limited
    public int firstBadVersion(int n) {
        return firstBadVersion(1, n);
    }

    private int firstBadVersion(int i, int j) {
        if (i == j) return isBadVersion(i) ? i : -1;

        int mid = i+(j-i)/2;
        int f = firstBadVersion(i, mid);

        return (f != -1) ? f : firstBadVersion(mid+1, j);
    }


    public int firstBadVersion2(int n) {
        int i = 1;
        int j = n;
        int mid = 0;
        while (i <= j) {
            if (i == j) return i;
            mid = i+(j-i)/2;
            if (isBadVersion(mid)) j = mid;
            else i = mid+1;
        }
        return -1;
    }

}
