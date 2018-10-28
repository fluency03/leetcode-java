/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * 
 * But for multiples of three it should output “Fizz” instead of the number and
 * for the multiples of five output “Buzz”. For numbers which are multiples of
 * both three and five output “FizzBuzz”.
 * 
 * Example: n = 15,
 * Return:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 */

public class FizzBuzz412 {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            boolean isFizz = i % 3 == 0;
            boolean isBuzz = i % 5 == 0;
            if (isFizz && isBuzz) {
                res.add("FizzBuzz");
            } else if (isFizz) {
                res.add("Fizz");
            } else if (isBuzz) {
                res.add("Buzz");
            } else {
                res.add(Integer.toString(i));
            }
        }
        return res;
    }

}
