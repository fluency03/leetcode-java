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

}
