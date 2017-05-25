/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class WiggleSortII324 {
    // too slow
    public void wiggleSort(int[] nums) {
        int l = nums.length;

        Arrays.sort(nums);
        LinkedList<Integer> numsLL = new LinkedList<Integer>();
        for (int i = 0; i < l; i++) {
            numsLL.add(nums[i]);
        }
        LinkedList<Integer> sorted = new LinkedList();

        int last = 0;
        if (l%2 == 1) {
            last = numsLL.get(l/2);
            numsLL.remove(l/2);
        }

        int mid = numsLL.size()/2 - 1;

        while (mid >= 0) {
            Integer first = numsLL.get(mid);
            sorted.add(first);
            Integer next = mid + 1;
            while (numsLL.get(next).equals(first)) {
                next++;
            }
            sorted.add(numsLL.get(next));
            numsLL.remove(mid);
            numsLL.remove(next - 1);
            mid--;
        }

        if (l%2 == 1) {
            sorted.add(last);
        }

        for (int i = 0; i < l; i++) {
            nums[i] = sorted.get(i);
        }

    }


    



    public static void main(String[] args) {
        WiggleSortII324 ws2 = new WiggleSortII324();

        ws2.wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
        ws2.wiggleSort(new int[]{1, 3, 2, 2, 3, 1});
    }
}
