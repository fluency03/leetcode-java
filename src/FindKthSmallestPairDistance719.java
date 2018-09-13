/**
 * Given an integer array, return the k-th smallest distance among all the
 * pairs. The distance of a pair (A, B) is defined as the absolute difference
 * between A and B.
 * 
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0 
 * 
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * 
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */

public class FindKthSmallestPairDistance719 {
    public int smallestDistancePair(int[] nums, int k) {
        int len = nums.length;
        int[] buckets = new int[1000000];
        for (int i=0; i<len; i++) {
            int a = nums[i];
            for (int j=i+1; j<len; j++) {
                buckets[Math.abs(a - nums[j])]++;
            }
        }
        
        int count = 0;
        for (int i=0; i<1000000; i++) {
            count += buckets[i];
            if (count >= k) {
                return i;
            }
        }
        
        return -1;
    }


    public int smallestDistancePair3(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || len == 0) return -1; 
        Arrays.sort(nums);
        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int cnt = count(nums, mid);
            if (k <= cnt) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return lo;
    }

    private int count(int[] nums, int x) {
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            while (nums[right] - nums[left] > x) {
                left++;
            }
            count += right - left;
            right++;
        }
        return count;
    }

}

