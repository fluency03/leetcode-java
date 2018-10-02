/**
 * An abbreviation of a word follows the form
 * <first letter><number><last letter>. Below are some examples of word
 * abbreviations:
 * 
 * a) it                      --> it    (no abbreviation)
 * 
 *      1
 *      ↓
 * b) d|o|g                   --> d1g
 * 
 *               1    1  1
 *      1---5----0----5--8
 *      ↓   ↓    ↓    ↓  ↓    
 * c) i|nternationalizatio|n  --> i18n
 * 
 *               1
 *      1---5----0
 *      ↓   ↓    ↓
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation
 * is unique in the dictionary. A word's abbreviation is unique if no other
 * word from the dictionary has the same abbreviation.
 * 
 * Example:
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */

public class UniqueWordAbbreviation288 {
    class ValidWordAbbr {
        Map<String, Set<String>> map = new HashMap<>();

        public ValidWordAbbr(String[] dictionary) {
            for (String word: dictionary) {
                String encoded = encode(word);
                if (!map.containsKey(encoded)) {
                    map.put(encoded, new HashSet<>());
                }
                map.get(encoded).add(word);
            }
        }

        public boolean isUnique(String word) {
            String encoded = encode(word);
            return !map.containsKey(encoded) || (map.get(encoded).size() == 1 && map.get(encoded).contains(word));
        }
        
        private String encode(String word) {
            if (word.length() <= 2) return word;
            return word.charAt(0) + Integer.toString(word.length()-2) + word.charAt(word.length()-1);
        }
    }


    /**
     * https://leetcode.com/problems/unique-word-abbreviation/solution/
     */
    public class ValidWordAbbr2 {
        private final Map<String, Boolean> abbrDict = new HashMap<>();
        private final Set<String> dict;

        public ValidWordAbbr2(String[] dictionary) {
            dict = new HashSet<>(Arrays.asList(dictionary));
            for (String s : dict) {
                String abbr = toAbbr(s);
                abbrDict.put(abbr, !abbrDict.containsKey(abbr));
            }
        }

        public boolean isUnique(String word) {
            String abbr = toAbbr(word);
            Boolean hasAbbr = abbrDict.get(abbr);
            return hasAbbr == null || (hasAbbr && dict.contains(word));
        }

        private String toAbbr(String s) {
            int n = s.length();
            if (n <= 2) {
                return s;
            }
            return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
        }
    }


/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */

}

