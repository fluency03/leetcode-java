/**
 * Given a sorted integer array without duplicates, return the summary of
 * its ranges.
 * 
 * Example 1:
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * 
 * Example 2:
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */

public class SummaryRanges228 {
    public List<String> summaryRanges(int[] arr) {
        List<String> res = new ArrayList<>();
        if (arr == null || arr.length == 0) return res;
        if (arr.length == 1) {
            res.add(Integer.toString(arr[0]));
            return res;
        }
    
        int len = arr.length;
        int i = 0;
        int j = 1;
        while (j <= len) {
            if (j == len || arr[j] - arr[j-1] != 1) {
                if (j-1 <= i) {
                    res.add(Integer.toString(arr[i]));
                } else {
                    res.add(arr[i] + "->" + arr[j-1]);
                }
                i = j;
            }
            j++;
        }
    
        return res;
    }

}
