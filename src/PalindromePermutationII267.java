/**
 * Given a string s, return all the palindromic permutations
 * (without duplicates) of it. Return an empty list if no palindromic
 * permutation could be form.
 * 
 * Example 1:
 * Input: "aabb"
 * Output: ["abba", "baab"]
 * 
 * Example 2:
 * Input: "abc"
 * Output: []
 */


public class PalindromePermutationII267 {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        int[] map = new int[256];
        int N = 0;
        for (char ch: s.toCharArray()) {
            map[ch]++;
            N++;
        }
        StringBuilder sb = new StringBuilder();
        int odd = -1;
        for (int i=0; i<256; i++) {
            if (map[i] % 2 != 0) {
                if (odd != -1) return res;
                odd = i;
                sb.append((char) i);
                map[i]--;
            }
        }
        helper(map, 0, sb, res, N);
        return res;
    }

    private void helper(int[] map, int curr, StringBuilder sb, List<String> res, int N) {
        if (sb.length() == N) {
            res.add(sb.toString());
            return;
        }
        for (int i=0; i<256; i++) {
            if (map[i] == 0) continue;
            map[i] -= 2;
            add(sb, (char) i);
            helper(map, curr+1, sb, res, N);
            remove(sb);
            map[i] += 2;
        }
    }

    private void add(StringBuilder sb, char ch) {
        sb.append(ch);
        sb.insert(0, ch);
    }
    
    private void remove(StringBuilder sb) {
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
    }

}
