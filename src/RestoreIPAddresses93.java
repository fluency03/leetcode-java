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
import java.util.LinkedList;


public class RestoreIPAddresses93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        int L = s.length();
        if (L < 4) return results;

        List<String> one = new LinkedList<>();

        helper(s, results, one, 0, 0, L);

        return results;
    }


    private void helper(String s, List<String> results, List<String> one, int start, int level, int L) {
        if (level == 3 && (start + 3) < L) return;

        if (level >= 4) {
            if (start == L) results.add(String.join(".", one));
            return;
        }

        for (int i = 1; i<=3 && start+i<=L; i++) {
            String current = s.substring(start, start+i);
            if (!isValid(current)) continue;

            one.add(current);
            helper(s, results, one, start+i, level+1, L);
            one.remove(one.size()-1);
        }
    }

    private boolean isValid(String current) {
        // starts with "0" but not "0"
        if (current.startsWith("0") && current.length() > 1) return false;
        // larger than 255
        if (Integer.valueOf(current) > 255) return false;
        return true;
    }


}
