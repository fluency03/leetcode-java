/**
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * For example,
 *
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 *
 */


public class FractionToRecurringDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        boolean sameSign = (numerator >= 0 && denominator >= 0) || (numerator < 0 && denominator < 0);
        long nume = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        Long ones = nume / deno;

        List<Long> decimals = new ArrayList<>();
        long left = nume % deno;
        Map<Long, Integer> map = new HashMap<>();

        left *= 10;
        int pos = 0;
        int repeat = -1;
        while (true) {
            if (left == 0) break;

            if (map.containsKey(left)) {
                repeat = map.get(left);
                break;
            }

            long newOne = Math.abs(left / deno);
            long newLeft = left % deno;
            decimals.add(newOne);
            map.put(left, pos);
            left = newLeft*10;
            pos++;
        }

        String pre = (sameSign || ones <= 0L) ? ones.toString() : ("-" + ones.toString());
        if (decimals.size() == 0) return pre;


        StringBuilder sb = new StringBuilder();
        for (int i=0; i<decimals.size(); i++) {
            String curr = decimals.get(i).toString();
            String newD = (repeat != i) ? curr : ("(" + curr);
            sb.append(newD);
        }

        if (repeat != -1) sb.append(")");

        return String.format("%s.%s", (sameSign) ? ones.toString() : ("-" + ones.toString()), sb);
    }


    public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder fraction = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }

        long num = Math.abs(Long.valueOf(numerator));
        long denom = Math.abs(Long.valueOf(denominator));

        fraction.append(num / denom);
        long remainder = num % denom;

        if (remainder == 0) {
            return fraction.toString();
        }

        fraction.append(".");
        HashMap<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }

            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(remainder / denom);
            remainder = remainder % denom;
        }

        return fraction.toString();
    }


}
