/**
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */


public class IntegerToEnglishWords273 {

    private static final String[] UNITS = {
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};


    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";
        while (num > 0) {
            if (num % 1000 != 0) {
        	      words = threeDigits(num % 1000) + " " + THOUSANDS[i] + " " + words;
            }
          	num /= 1000;
          	i++;
        }

        return words.trim();
    }


    private String threeDigits(int num) {
        if (num >= 100) {
            int h = num/100;
            String twoDigits = twoDigits(num%100);
            return UNITS[h] + " Hundred" + ((twoDigits.length() == 0) ? "" : " " + twoDigits);
        }
        return twoDigits(num);
    }

    private String twoDigits(int num) {
        if (num == 0) return "";

        if (num > 0 && num < 20) {
            return UNITS[num];
        }

        int t = num/10;
        int n = num%10;

        return TENS[t-2] + ((n == 0) ? "": (" " + UNITS[n]));
    }


}
