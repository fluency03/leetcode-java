/**
 * You are playing the following Bulls and Cows game with your friend:
 * You write down a number and ask your friend to guess what the number is.
 * Each time your friend makes a guess, you provide a hint that indicates
 * how many digits in said guess match your secret number exactly in both digit
 * and position (called "bulls") and how many digits match the secret number
 * but locate in the wrong position (called "cows"). Your friend will use
 * successive guesses and hints to eventually derive the secret number.
 *
 * Bulls and Cows https://en.wikipedia.org/wiki/Bulls_and_Cows
 *
 * For example:
 *
 * Secret number:  "1807"
 * Friend's guess: "7810"
 *
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and
 * friend's guess, use A to indicate the bulls and B to indicate the cows.
 * In the above example, your function should return "1A3B".
 *
 * Please note that both secret number and friend's guess may contain duplicate digits, for example:
 *
 * Secret number:  "1123"
 * Friend's guess: "0111"
 *
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a
 * cow, and your function should return "1A1B".
 *
 * You may assume that the secret number and your friend's guess only contain
 * digits, and their lengths are always equal.
 */


public class BullsAndCows299 {
    public String getHint(String secret, String guess) {

        Map<Character, Integer> secrets = new HashMap<>();
        Map<Character, Integer> guesses = new HashMap<>();

        Map<String, Integer> counts = new HashMap<>();
        counts.put("bull", 0);
        counts.put("cows", 0);

        List<Integer> left = new ArrayList<>();

        for (int i=0; i<guess.length(); i++) {
            Character c1 = secret.charAt(i);
            Character c2 = guess.charAt(i);

            if (c1.equals(c2)) {
                counts.put("bull", counts.get("bull")+1);
            } else {
                secrets.put(c1, secrets.getOrDefault(c1, 0)+1);
                guesses.put(c2, guesses.getOrDefault(c2, 0)+1);
            }
        }

        for (Map.Entry<Character, Integer> e: guesses.entrySet()) {
            if (secrets.containsKey(e.getKey())) {
                counts.put("cows", counts.get("cows") + Math.min(secrets.get(e.getKey()), e.getValue()));
            }
        }

        return String.format("%dA%dB", counts.get("bull"), counts.get("cows"));
    }


    public String getHint2(String secret, String guess) {

        Map<Character, Integer> secrets = new HashMap<>();
        Map<Character, Integer> guesses = new HashMap<>();

        Map<String, Integer> counts = new HashMap<>();
        counts.put("bull", 0);
        counts.put("cows", 0);

        for (int i=0; i<guess.length(); i++) {
            Character c1 = secret.charAt(i);
            Character c2 = guess.charAt(i);

            if (c1.equals(c2)) {
                counts.put("bull", counts.get("bull")+1);
            } else {
                if (secrets.containsKey(c2) && secrets.get(c2) > 0) {
                    secrets.put(c2, secrets.get(c2)-1);
                    counts.put("cows", counts.get("cows")+1);
                } else {
                    guesses.put(c2, guesses.getOrDefault(c2, 0)+1);
                }
                if (guesses.containsKey(c1) && guesses.get(c1) > 0) {
                    guesses.put(c1, guesses.get(c1)-1);
                    counts.put("cows", counts.get("cows")+1);
                } else {
                    secrets.put(c1, secrets.getOrDefault(c1, 0)+1);
                }
            }
        }

        return String.format("%dA%dB", counts.get("bull"), counts.get("cows"));
    }

    /**
     * https://discuss.leetcode.com/topic/28463/one-pass-java-solution
     */
    public String getHint3(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
                if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }

}
