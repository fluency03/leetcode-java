/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 
 * Example 1:
 * 
 * Input:
 * "tree"
 * 
 * Output:
 * "eert"
 * 
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * Input:
 * "cccaaa"
 * 
 * Output:
 * "cccaaa"
 * 
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * 
 * Example 3:
 * 
 * Input:
 * "Aabb"
 * 
 * Output:
 * "bbAa"
 * 
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */


public class SortCharactersByFrequency451 {
    public String frequencySort(String s) {
        int[] map = new int[256];
        for (char c: s.toCharArray()) map[c]++;
        List<Character>[] bucket = new List[s.length() + 1];
        for (int i=0; i<256; i++) {
            int freq = map[i];
            if (freq != 0) {
                if (bucket[freq] == null) bucket[freq] = new LinkedList<>();
                bucket[freq].add((char) i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=bucket.length-1; i>=0; i--) {
            List<Character> list = bucket[i];
            if (list != null) {
                for (Character c: list) {
                    char[] chars = new char[i];
                    Arrays.fill(chars, c);
                    sb.append(chars);
                }
            }
        }
        
        return sb.toString();
    }
}
