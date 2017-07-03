/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 *
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */


import java.util.List;
import java.util.ArrayList;
import java.util.Stack;


public class RestoreIPAddresses93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        int L = s.length();
        if (L < 4) return results;

        Stack<String> st = new Stack<>();

        helper(s, results, st, 0, L);

        return results;
    }


    private void helper(String s, List<String> results, Stack<String> st, int start, int L) {
        if (st.size() == 3 && (start + 3) < L) return;

        if (st.size() == 4) {
            if (start == L) results.add(String.join(".", st));
            return;
        }

        for (int i = 1; i<=3 && start+i<=L; i++) {
            String current = s.substring(start, start+i);
            if (!isValid(current)) continue;

            st.push(current);
            helper(s, results, st, start+i, L);
            st.pop();
        }
    }

    private boolean isValid(String current) {
        // starts with "0" but not "0"
        if (current.charAt(0)=='0' && current.length() > 1) return false;
        // larger than 255
        if (Integer.valueOf(current) > 255) return false;
        return true;
    }

    /**
     * https://discuss.leetcode.com/topic/3919/my-code-in-java
     */
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 1; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid2(s1) && isValid2(s2) && isValid2(s3) && isValid2(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean isValid2(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }

}
