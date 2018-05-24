/**
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number.
 * The time complexity must be in O(n).
 * 
 * Example 1:
 * Input: [3, 2, 1]
 * Output: 1
 * Explanation: The third maximum is 1.
 * 
 * Example 2:
 * Input: [1, 2]
 * Output: 2
 * Explanation: The third maximum does not exist, so the maximum (2) is
 * returned instead.
 * 
 * Example 3:
 * Input: [2, 2, 3, 1]
 * Output: 1
 * Explanation: Note that the third maximum here means the third maximum
 * distinct number. Both numbers with value 2 are both considered as
 * second maximum.
 * 
 */


public class ThirdMaximumNumber414 {

    public static int thirdMax(int[] arr) {
        // non-empty array, no need for checking empty
        Stack<Integer> st = new Stack<>();
        for (int i: arr) {
            Stack<Integer> temp = new Stack<>();
            while (!st.empty() && st.peek() <= i) {
                temp.push(st.pop());
            }
            st.push(i);
            while (!temp.empty() && st.size() < 3) {
                int t = temp.pop();
                if (!st.empty() && st.peek() == t) continue;
                st.push(t);
            }
            while (st.size() > 3) {
                st.pop();
            }
        }

        if (st.size() == 3) return st.peek();

        while (st.size() > 1) {
            st.pop();
        }
        return st.peek();
    }

    public static int thirdMax2(int[] arr) {
        PriorityQueue<Integer> q = new PriorityQueue();
        // non empty array
        q.add(arr[0]);
        for (int i=1; i<arr.length; i++) {
            int num = arr[i];
            if (q.size() >= 3 && q.peek() >= num) continue;
            if (q.contains(num)) continue;
            if (q.size() >= 3) q.poll();
            q.add(num);
        }
        // exits
        if (q.size() == 3) return q.peek();

        // not exits
        while (q.size() > 1) q.poll();
        return q.peek();
    }


    /**
     * https://leetcode.com/problems/third-maximum-number/discuss/90190/Java-PriorityQueue-O(n)-+-O(1)
     */
    public int thirdMax3(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) {
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) {
                    set.remove(pq.poll());
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }


    /**
     * https://leetcode.com/problems/third-maximum-number/discuss/90202/Java-neat-and-easy-understand-solution-O(n)-time-O(1)-space
     */
    public int thirdMax4(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }


    // public static int thirdMax4(int[] arr) {
    //     int[] temp = new int[3];
    //     Arrays.fill(temp, Integer.MIN_VALUE);
    //     int c = 0;
    //     for (int i: arr) {
    //         int minIdx = -1;
    //         int min = Integer.MAX_VALUE;
    //         boolean dup = false;
    //         for (int k=0; k<3; k++) {
    //             if (temp[k] == i) {
    //                 dup = true;
    //                 break;
    //             }
    //             if (min > i) {
    //                 min = i;
    //                 minIdx = k;
    //             }
    //         }
    //         if (!dup && minIdx != -1) {
    //             c++;
    //             temp[minIdx] = i;
    //         }
    //     }

    //     if (c >= 3) {
    //         return Math.min(Math.min(temp[0], temp[1]), temp[2]);
    //     } else {
    //         return Math.max(Math.max(temp[0], temp[1]), temp[2]);
    //     }
    // }


}
