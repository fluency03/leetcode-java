/**
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2.
 * 
 * Note:
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 * 
 * Example:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */

public class TwoSumIIInputArrayIsSorted167 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[2];
        
        int len = numbers.length;
        for (int i=0; i<=len-2; i++) {
            int idx = binarySearch(numbers, target-numbers[i], i+1, len-1);
            if (idx != -1) {
                return new int[]{i+1, idx+1};
            }
        }
        
        return new int[2];
    }

    private int binarySearch(int[] numbers, int target, int l, int r) {
        if (l > r) return -1;
        if (l == r) return numbers[l] == target ? l : -1;
        
        int mid = (l + r) / 2;
        if (numbers[mid] == target) return mid;
        if (numbers[mid] > target) {
            return binarySearch(numbers, target, l, mid-1);
        } else {
            return binarySearch(numbers, target, mid+1, r);
        }
    }


    // using Arrays.binarySearch
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[2];
        
        int len = numbers.length;
        for (int i=0; i<=len-2; i++) {
            int idx = Arrays.binarySearch(numbers, i+1, len, target - numbers[i]);
            if (idx >= 0) {
                return new int[]{i+1, idx+1};
            }
        }
        
        return new int[2];
    }

}
