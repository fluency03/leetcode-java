/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 *
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *
 * Note: The result may be very large, so you need to return a string instead
 * of an integer.
 *
 */


public class LargestNumber179 {
    public String largestNumber(int[] nums) {
        String[] newNums = new String[nums.length];
        boolean allZeros = true;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) allZeros = false;
            newNums[i] = Integer.toString(nums[i]);
        }
        if (allZeros) return "0";

        Arrays.sort(newNums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String sum1 = s1 + s2;
                String sum2 = s2 + s1;
                return sum2.compareTo(sum1);
            }
        });

        return String.join("", newNums);
    }


    /**
     * https://discuss.leetcode.com/topic/7235/my-3-lines-code-in-java-and-python
     */
    public String largestNumber2(int[] num) {
        String[] array = Arrays.stream(num).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));
        return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
    }

}
