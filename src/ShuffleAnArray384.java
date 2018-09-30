/**
 * Shuffle a set of numbers without duplicates.
 * 
 * Example:
 * 
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of
 * [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 */

public class ShuffleAnArray384 {

    class Solution {
        private int[] nums;
        private Random rand = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return this.nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] randomed = this.nums.clone();
            for (int i=randomed.length-1; i>0; i--) {
                int idx = rand.nextInt(i+1);
                swap(randomed, idx, i);
            }
            return randomed;
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

}
