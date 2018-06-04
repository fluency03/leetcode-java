/**
 * Given a paragraph and a list of banned words, return the most frequent word
 * that is not in the list of banned words.  It is guaranteed there is at least
 * one word that isn't banned, and that the answer is unique.
 * 
 * Words in the list of banned words are given in lowercase, and free of
 * punctuation.  Words in the paragraph are not case sensitive.  The answer
 * is in lowercase.
 * 
 * Example:
 * Input: 
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * 
 * Explanation: 
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent
 * non-banned word in the paragraph. 
 * 
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"), 
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 * 
 * Note:
 * 
 * 1 <= paragraph.length <= 1000.
 * 1 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in
 * paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * Different words in paragraph are always separated by a space.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */


public class MostCommonWord819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        // update O(K)
        Set<String> bannedSet = new HashSet<>();
        for (String w: banned) bannedSet.add(w.toLowerCase());

        // update O(N), N: number of chars
        Map<String, Integer> freq = new HashMap<>();

        String mcw = "";
        int maxCount = -1;
        int left = 0;
        int right = 0;
        while (right < paragraph.length()) {
            while (left < paragraph.length() && !isLetter(paragraph.charAt(left))) left++;
            if (left >= paragraph.length()) break;
            right = left;
            while (right < paragraph.length() && isLetter(paragraph.charAt(right))) right++;
            String word  = paragraph.substring(left, right).toLowerCase();
            if (!bannedSet.contains(word)) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
                if (freq.get(word) > maxCount) {
                    maxCount = freq.get(word);
                    mcw = word;
                }
            }
            left = right;
        }

        return mcw;
    }

    private boolean isLetter(char c) {
        return (c - 'a' >= 0 && c - 'z' <= 0) || (c - 'A' >= 0 && c - 'Z' <= 0);
    }



    public String mostCommonWord2(String paragraph, String[] banned) {
        String[] splitArr = paragraph.replaceAll("[!?',;.]","").toLowerCase().split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        List<String> bannedList = Arrays.asList(banned);
        int currentMax = 0;
        String res = "";
        for(String str: splitArr) {
            if(!bannedList.contains(str)) {
                map.put(str, map.getOrDefault(str, 0) + 1);
                if (map.get(str) > currentMax) {
                    res = str;
                    currentMax = map.get(str);
                }
            }
        }
        return res;
    }

}

