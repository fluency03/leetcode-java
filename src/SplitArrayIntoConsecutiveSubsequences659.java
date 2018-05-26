/**
 * You are given an integer array sorted in ascending order (may contain
 * duplicates), you need to split them into several subsequences, where each
 * subsequences consist of at least 3 consecutive integers. Return whether you
 * can make such a split.
 * 
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences : 
 * 1, 2, 3
 * 3, 4, 5
 * 
 *  Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences : 
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 * 
 * Note:
 * The length of the input is in range of [1, 10000]
 *
 */


public class SplitArrayIntoConsecutiveSubsequences659 {
    // too slow
    // public boolean isPossible(int[] nums) {
    //     if (nums == null || nums.length < 3) return false;

    //     Map<Integer, Integer> sizes = new HashMap<>();
    //     Map<Integer, Integer> lasts = new HashMap<>();
    //     for (int i=0; i<nums.length; i++) {
    //         int s1Index = -1;
    //         int s2Index = -1;
    //         for (int si=0; si<sizes.size(); si++) {
    //             if (lasts.get(si) + 1 != nums[i]) continue;
    //             // System.out.println(s1Index + ", " + s2Index);
    //             if (s1Index == -1) {
    //                 s1Index = si;
    //             }
    //             if (sizes.get(si) < 3) {
    //                 s2Index = si;
    //                 break;
    //             }
    //         }
    //         // System.out.println(s1Index + ", " + s2Index + "; " + nums[i]);
    //         int idx = (s2Index == -1) ? s1Index : s2Index;
    //         if (idx != -1) {
    //             lasts.put(idx, nums[i]);
    //             sizes.put(idx, sizes.get(idx)+1);
    //         } else {
    //             int newSub = sizes.size();
    //             lasts.put(newSub, nums[i]);
    //             sizes.put(newSub, 1);
    //         }
    //     }
        
    //     // return whether each stack is having at least 3 elements
    //     for (Integer sz: sizes.values()) {
    //         if (sz < 3) return false;
    //     }
    //     return true;
    // }


    /**
     * https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106496/Java-O(n)-Time-O(n)-Space
     * 
     * - We iterate through the array once to get the frequency of all the
     *      elements in the array
     * - We iterate through the array once more and for each element we either
     *      see if it can be appended to a previously constructed consecutive
     *      sequence or if it can be the start of a new consecutive sequence.
     *      If neither are true, then we return false.
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
        for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
        for (int i : nums) {
            if (freq.get(i) == 0) continue;
            else if (appendfreq.getOrDefault(i,0) > 0) {
                appendfreq.put(i, appendfreq.get(i) - 1);
                appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
            }   
            else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
            }
            else return false;
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }


    /**
     * https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence
     */
    public boolean isPossible2(int[] nums) {
        int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
        int cur = 0, cnt = 0, c1 = 0, c2 = 0, c3 = 0;
            
        for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
            for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; cnt++, i++);
                
            if (cur != pre + 1) {
                if (p1 != 0 || p2 != 0) return false;
                c1 = cnt; c2 = 0; c3 = 0;
                
            } else {
                if (cnt < p1 + p2) return false;
                c1 = Math.max(0, cnt - (p1 + p2 + p3));
                c2 = p1;
                c3 = p2 + Math.min(p3, cnt - (p1 + p2));
            }
        }
        
        return (p1 == 0 && p2 == 0);
    }


    public boolean isPossible3(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        
        int i = 0;
        while (i < nums.length) {
            int cur = nums[i];
            int count = 0;
            while (i < nums.length && nums[i] == cur) {
                i++;
                count++;
            }
            
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            if (cur == pre + 1) {
                if (count < p1 + p2) return false;
                c1 = Math.max(0, count - (p1 + p2 + p3));
                c2 = p1;
                c3 = p2 + Math.min(p3, count - (p1 + p2));
            } else {
                if (p1 != 0 || p2 != 0) return false;
                c1 = count;
                c2 = 0;
                c3 = 0;
            }
            
            pre = cur;
            p1 = c1;
            p2 = c2;
            p3 = c3;
        }
        
        return (p1 == 0 && p2 == 0);
    }

}
