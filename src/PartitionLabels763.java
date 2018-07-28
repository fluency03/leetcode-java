/**
 * A string S of lowercase letters is given. We want to partition this string
 * into as many parts as possible so that each letter appears in at most one
 * part, and return a list of integers representing the size of these parts.
 * 
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it
 * splits S into less parts.
 * 
 * Note:
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */

public class PartitionLabels763 {
    public List<Integer> partitionLabels(String S) {
        char[] chars = S.toCharArray();
        List<int[]> intervals = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        int ii = 0;
        int i = 0;
        for (char c: chars) {
            if (map.containsKey(c)) {
                intervals.get(map.get(c))[1] = i;
            } else {
                map.put(c, ii++);
                intervals.add(new int[]{i, i});
            }
            i++;
        }
        
        List<Integer> res = new ArrayList<>();
        int l = 0;
        int r = 0;
        for (int[] curr: intervals) {
            if (curr[0] <= r) {
                r = Math.max(curr[1], r);
            } else {
                res.add(r - l + 1);
                l = curr[0];
                r = curr[1];
            }
            
        }
        res.add(r - l + 1);
        return res;
    }


    /**
     * https://leetcode.com/problems/partition-labels/solution/
     */
    public List<Integer> partitionLabels2(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;
        
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }


}
