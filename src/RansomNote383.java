/**
 * Given an arbitrary ransom note string and another string containing letters
 * from all the magazines, write a function that will return true if the ransom
 * note can be constructed from the magazines ; otherwise, it will return false.
 * 
 * Each letter in the magazine string can only be used once in your ransom note.
 * 
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * 
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */


public class RansomNote383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> dict = new HashMap<>();
        for (char c: magazine.toCharArray()) dict.put(c, dict.getOrDefault(c, 0) + 1);
        for (char c: ransomNote.toCharArray()) {
            int count = dict.getOrDefault(c, 0);
            if (count <= 0) return false;
            dict.put(c, count-1);
        }
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] dict = new int[26];
        for (char c: magazine.toCharArray()) dict[c-'a']++;
        for (char c: ransomNote.toCharArray()) {
            dict[c-'a']--;
            if (dict[c-'a'] < 0) return false;
        }
        return true;
    }

}
