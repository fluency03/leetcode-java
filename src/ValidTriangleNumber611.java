/**
 * Given an array consists of non-negative integers, your task is to count the
 * number of triplets chosen from the array that can make triangles if we take
 * them as side lengths of a triangle.
 * 
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are: 
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * 
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 */


public class ValidTriangleNumber611 {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int i=0; i<nums.length-2; i++) {
            for (int j=i+1; j<nums.length-1; j++) {
                for (int k=j+1; k<nums.length; k++) {
                    if (isValidTriangle(nums[i], nums[j], nums[k])) res++;
                    else break;
                }
            }
        }
        
        return res;
    }

    public boolean isValidTriangle(int a, int b, int c) {
        if (a == 0 || b == 0 || c == 0) return false;
        int longestSide = a;
        if (b > longestSide) longestSide = b;
        if (c > longestSide) longestSide = c;
        return longestSide < (a + b + c - longestSide);
    }
  
  
    public int triangleNumber2(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        
        Arrays.sort(nums);
        int[] sum = new int[1001];
        for(int n: nums) sum[n]++;
        for(int i=1; i<1001; i++) sum[i] += sum[i-1];

        int res = 0;
        for (int i=0; i<nums.length-2; i++) {
            if (nums[i] == 0) continue;
            for (int j=i+1; j<nums.length-1; j++) {
                if (nums[j] == 0) continue;
                int upper = nums[i] + nums[j];
                int lower = Math.abs(nums[i] - nums[j]);
                int c = sum[upper-1] - Math.max(sum[lower], j+1);
                res += c;
            }
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/valid-triangle-number/solution/
     */
    public int triangleNumber3(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k])
                    k++;
                count += k - j - 1;
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/valid-triangle-number/discuss/104174/Java-O(n2)-Time-O(1)-Space
     */
    public static int triangleNumber4(int[] A) {
        Arrays.sort(A);
        int count = 0, n = A.length;
        for (int i=n-1;i>=2;i--) {
            int l = 0, r = i-1;
            while (l < r) {
                if (A[l] + A[r] > A[i]) {
                    count += r-l;
                    r--;
                }
                else l++;
            }
        }
        return count;
    }
  
}



