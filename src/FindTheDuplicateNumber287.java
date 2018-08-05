/**
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 * 
 *  Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated
 * more than once.
 */

public class FindTheDuplicateNumber287 {
    public int findDuplicate(int[] nums) {
      int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public int findDuplicate2(int[] nums) {
        int fast = nums[0];
        int slow = nums[0];
        while (true) {
            // System.out.println("slow: " + slow + "; " + "fast: " + fast);
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (slow == fast) break;
        }
        
        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }


    // This modified the input array!
    // public int findDuplicate(int[] nums) {
    //     int N = nums.length;
    //     Arrays.sort(nums);
        
    //     for (int i=0; i<N-1; i++) {
    //         if (nums[i] == nums[i+1]) return nums[i];
    //     }
    //     return nums[0];
    // }

}
