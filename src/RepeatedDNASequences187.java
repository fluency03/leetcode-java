/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * Example:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */

public class RepeatedDNASequences187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Boolean> map = new HashMap<>();
        int N = s.length();
        for (int i=0; i<=N-10; i++) {
            String sub = s.substring(i, i+10);
            if (map.containsKey(sub)) {
                if (!map.get(sub)) {
                    map.put(sub, true);
                    res.add(sub);
                }
            } else {
                map.put(sub, false);
            }
        }
        return res;
    }

}
