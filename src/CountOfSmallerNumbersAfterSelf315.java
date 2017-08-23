/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller
 * elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Return the array [2, 1, 1, 0].
 *
 */


public class CountOfSmallerNumbersAfterSelf315 {
    // brutal force method, Time Limit Exceeded
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);
        for (int i=0; i<nums.length; i++) {
            int now = nums[i];
            int count = 0;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] < now) {
                    count++;
                }
            }
            res.set(i, count);
        }
        return res;
    }

    // another method, Time Limit Exceeded
    public List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<Integer>();
        }
        LinkedList<Integer> res = new LinkedList<>();
        res.add(0);
        ListNode head = new ListNode(nums[nums.length-1]);
        for (int i=nums.length-2; i>=0; i--) {
            ListNode newNode = new ListNode(nums[i]);
            int count = 0;
            ListNode temp = head;
            if (nums[i] <= temp.val) {
                newNode.next = temp;
                head = newNode;
                res.addFirst(count);
                continue;
            }
            while (temp.next != null && nums[i] > temp.next.val) {
                temp = temp.next;
                count++;
            }
            if (temp.next == null) {
                temp.next = newNode;
            } else {
                newNode.next = temp.next;
                temp.next = newNode;
            }
            count++;
            res.addFirst(count);
        }
        return res;
    }







}
