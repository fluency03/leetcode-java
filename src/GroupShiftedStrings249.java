/**
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * 
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all
 * strings that belong to the same shifting sequence.
 * 
 * Example:
 * 
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output: 
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 */

public class GroupShiftedStrings249 {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            boolean found = false;
            for (String k: map.keySet()) {
                if (s.length() != k.length()) continue;
                boolean b = true;
                int diff = getDiff(s.charAt(0), k.charAt(0));
                for (int i=1; i<s.length(); i++) {
                    b = getDiff(s.charAt(i), k.charAt(i)) == diff;
                    if (!b) break;
                }
                if (b) {
                    map.get(k).add(s);
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<String> tmp = new ArrayList<>();
                tmp.add(s);
                map.put(s, tmp);
            }
        }

        return new ArrayList<List<String>>(map.values());
    }

    private int getDiff(char c1, char c2) {
        int init = c1 - c2;
        while (init < 0) {
            init += 26;
        }
        return init;
    }

    /**
     * https://leetcode.com/problems/group-shifted-strings/discuss/127090/Short-Java-solution
     */
    public List<List<String>> groupStrings2(String[] strings) {
        Map<String, List<String>> groups = new HashMap<String, List<String>>();
        for (String s : strings) {
            int shift = 'z' - s.charAt(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append((s.charAt(i) + shift) % 26);
            }
            if (!groups.containsKey(sb.toString())) {
                groups.put(sb.toString(), new ArrayList<String>());
            }
            groups.get(sb.toString()).add(s);
        }
        return new ArrayList<List<String>>(groups.values());
    }

}