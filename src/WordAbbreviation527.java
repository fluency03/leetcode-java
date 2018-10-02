/**
 * Given an array of n distinct non-empty strings, you need to generate minimal
 * possible abbreviations for every word following rules below.
 * 
 * Begin with the first character and then the number of characters abbreviated,
 * which followed by the last character.
 * 
 * If there are any conflict, that is more than one words share the same
 * abbreviation, a longer prefix is used instead of only the first character
 * until making the map from word to abbreviation become unique. In other
 * words, a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 * 
 * Example:
 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * 
 * Note:
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 */

public class WordAbbreviation527 {
    public List<String> wordsAbbreviation(List<String> dict) {
        int N = dict.size();
        Abbr[] abbrs = new Abbr[N];

        for (int i=0; i<N; i++) {
            String word = dict.get(i);
            abbrs[i] = new Abbr(word, encode(word, 0), 0);
        }

        for (int i=0; i<N; i++) {
            while (true) {
                Set<Abbr> duplicates = new HashSet<>();
                for (int j=i+1; j<N; j++) {
                    if (abbrs[j].abbr.equals(abbrs[i].abbr)) {
                        duplicates.add(abbrs[j]);
                    }
                }
                if (duplicates.isEmpty()) break;
                duplicates.add(abbrs[i]);
                for (Abbr abbr: duplicates) {
                    abbr.offset++;
                    abbr.abbr = encode(abbr.word, abbr.offset);
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (Abbr abbr: abbrs) {
            res.add(abbr.abbr);
        }
        return res;
    }

    private String encode(String word, int offset) {
        if (offset + 2 >= word.length()) return word;
        String encoded = word.substring(0, offset+1) + Integer.toString(word.length()-offset-2) + word.charAt(word.length()-1);
        return encoded.length() >= word.length() ? word : encoded;
    }

    class Abbr {
        String word;
        String abbr;
        int offset;
        Abbr(String word, String abbr, int offset) {
            this.word = word;
            this.abbr = abbr;
            this.offset = offset;
        } 
    }

}
