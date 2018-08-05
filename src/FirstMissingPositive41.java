/**
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.
 */


import java.util.ArrayList;
import java.util.List;


public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {

        List<Boolean> isMissing = new ArrayList<>();
        isMissing.add(false);

        int firstMissing = 1;

        for (int i = 0; i < nums.length; i++) {
            int now = nums[i];
            if (now > 0) {
                try {

                    if (isMissing.get(now)) {
                        isMissing.set(now, false);
                    }

                } catch (IndexOutOfBoundsException e) {


                    while (isMissing.size() < now) {
                        isMissing.add(true);
                    }

                    isMissing.add(false);
                }


            }
            try {
                while (!isMissing.get(firstMissing)) {
                    firstMissing++;
                }
            } catch (IndexOutOfBoundsException e) {
                isMissing.add(true);
            }


        }

        return firstMissing;

    }


    /**
     * 
     */
    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] - 1 != i) {
                int tmp = nums[nums[i] - 1];
                if (tmp == nums[i]) {
                    break;
                }
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


    /**
     * https://leetcode.com/problems/first-missing-positive/discuss/17083/O(1)-space-Java-Solution
     */
    public int firstMissingPositive3(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if (A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    public int firstMissingPositive4(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int N = nums.length;
        for (int i=0; i<N; i++) {
            int idx = i;
            int val = nums[idx];
            while (idx >= 0 && idx < N && val > 0 && val <= N && nums[idx] != idx + 1) {
                int newVal = nums[val - 1];
                nums[val - 1] = val;
                idx = newVal - 1;
                val = newVal;
            }
        }
        
        for (int i=0; i<N; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return N + 1;
    }

}
