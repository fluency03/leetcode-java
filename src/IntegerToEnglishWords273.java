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
    private static final String[] DOTS = {"Thousand", "Million", "Billion"};


    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        if (num < Math.pow(10, 3)) {
            return threeDigits(num).trim();
        } else if (num < Math.pow(10, 6)) {
            String thou = threeDigits(num/1000);
            String hund = threeDigits(num%1000);
            return (thou + " Thousand" +
                    ((hund.trim().length() == 0) ? "": hund)).trim();
        } else if (num < Math.pow(10, 9)) {
            String mill = threeDigits(num/1000000);
            String thou = threeDigits((num/1000)%1000);
            String hund = threeDigits(num%1000);
            return (mill + " Million" +
                    ((thou.trim().length() == 0) ? "" : thou + " Thousand") +
                    ((hund.trim().length() == 0) ? "": hund)).trim();
        } else {
            String bill = threeDigits(num/1000000000);
            String mill = threeDigits((num/1000000)%1000);
            String thou = threeDigits((num/1000)%1000);
            String hund = threeDigits(num%1000);
            return (bill + " Billion" +
                    ((mill.trim().length() == 0) ? "" : mill + " Million") +
                    ((thou.trim().length() == 0) ? "" : thou + " Thousand") +
                    ((hund.trim().length() == 0) ? "" : hund)).trim();
        }
    }


    private String threeDigits(int num) {
        if (num >= 100) {
            int h = num/100;
            return " " + UNITS[h] + " Hundred" + twoDigits(num%100);
        }
        return twoDigits(num);
    }

    private String twoDigits(int num) {
        if (num == 0) return "";

        if (num > 0 && num < 20) {
            return " " + UNITS[num];
        }

        int t = num/10;
        int n = num%10;

        return " " + TENS[t-2] + ((n == 0) ? "": (" " + UNITS[n]));
    }


}
