/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */


public class LongestCommonPrefix14 {
    public String longestCommonPrefix(String[] strs) {
        String lcp = "";
        int S = strs.length;
        if (S == 0) return lcp;

        int L = strs[0].length();
        int idx = 0;
        for (int i = 1; i < S; i++) {
            if (strs[i].length() < L) {
                L = strs[i].length();
                idx = i;
            }
        }
        if (L == 0) return lcp;

        int p = 1;
        String possible = strs[idx];
        while (p <= L) {
            String sub = possible.substring(0, p);
            boolean b = true;
            for (int k = 0; k < S; k++) {
                if (!strs[k].startsWith(sub)) {
                    b = false;
                    break;
                }
            }
            if (!b) break;
            lcp = sub;
            p++;
        }

        return lcp;
    }


    /**
     * https://discuss.leetcode.com/topic/6987/java-code-with-13-lines
     */
    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }


    /**
     * https://discuss.leetcode.com/topic/27913/sorted-the-array-java-solution-2-ms
     */
    public String longestCommonPrefix3(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs!= null && strs.length > 0){

            Arrays.sort(strs);

            char [] a = strs[0].toCharArray();
            char [] b = strs[strs.length-1].toCharArray();

            for (int i = 0; i < a.length; i ++){
                if (b.length > i && b[i] == a[i]){
                    result.append(b[i]);
                }
                else {
                    return result.toString();
                }
            }
        }
        return result.toString();
    }

}
