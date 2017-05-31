/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 *
 * A = [3,2,1,0,4], return false.
 */



public class JumpGame55 {
    public boolean canJump(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1 ) {
            return true;
        }

        boolean[] dp = new boolean[length];
        dp[0] = true;

        for (int i = 1; i <= length - 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && (nums[j] >= i - j);
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[length - 1];
    }


    /**
     * https://discuss.leetcode.com/topic/3443/simplest-o-n-solution-with-constant-space
     */
    public boolean canJump2(int[] nums) {
        return canJumpHelper(nums, nums.length);
    }

    boolean canJumpHelper(int A[], int n) {
        int last=n-1,i,j;
        for(i=n-2;i>=0;i--){
            if(i+A[i]>=last)last=i;
        }
        return last<=0;
    }


    /**
     * https://discuss.leetcode.com/topic/7661/java-solution-easy-to-understand
     */
    public boolean canJump3(int[] A) {
        int max = 0;
        for(int i=0;i<A.length;i++){
            if(i>max) {return false;}
            max = Math.max(A[i]+i,max);
        }
        return true;
    }


    /**
     * https://discuss.leetcode.com/topic/36578/java-98-percentile-solution/7
     */
    public boolean canJump4(int[] nums) {
       if(nums.length < 2) return true;

       for(int curr = nums.length-2; curr>=0;curr--){
           if(nums[curr] == 0){
               int neededJumps = 1;
               while(neededJumps > nums[curr]){
                   neededJumps++;
                   curr--;
                   if(curr < 0) return false;
               }
           }
       }
       return true;
    }
}
