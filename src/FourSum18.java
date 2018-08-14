/**
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example:
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */

public class FourSum18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i=0; i<nums.length-3; i++) {
            if (i == 0 || nums[i] != nums[i-1]) {
                for (int j=i+1; j<nums.length-2; j++) {
                    if (j == i+1 || nums[j] != nums[j-1]) {
                        int expected = target - nums[i] - nums[j];
                        int lo = j + 1;
                        int hi = nums.length - 1;
                        while (lo < hi) {
                            int twoSum = nums[lo] + nums[hi];
                            if (twoSum == expected) {
                                res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                                while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                                while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                                lo++;
                                hi--;
                            } else if (twoSum > expected) {
                                hi--;
                            } else {
                                lo++;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/4sum/discuss/8575/Clean-accepted-java-O(n3)-solution-based-on-3sum
     */
    public List<List<Integer>> fourSum2(int[] num, int target) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if(num.length<4)return ans;
        Arrays.sort(num);
        for(int i=0; i<num.length-3; i++){
            if(num[i]+num[i+1]+num[i+2]+num[i+3]>target)break; //first candidate too large, search finished
            if(num[i]+num[num.length-1]+num[num.length-2]+num[num.length-3]<target)continue; //first candidate too small
            if(i>0&&num[i]==num[i-1])continue; //prevents duplicate result in ans list
            for(int j=i+1; j<num.length-2; j++){
                if(num[i]+num[j]+num[j+1]+num[j+2]>target)break; //second candidate too large
                if(num[i]+num[j]+num[num.length-1]+num[num.length-2]<target)continue; //second candidate too small
                if(j>i+1&&num[j]==num[j-1])continue; //prevents duplicate results in ans list
                int low=j+1, high=num.length-1;
                while(low<high){
                    int sum=num[i]+num[j]+num[low]+num[high];
                    if(sum==target){
                        ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                        while(low<high&&num[low]==num[low+1])low++; //skipping over duplicate on low
                        while(low<high&&num[high]==num[high-1])high--; //skipping over duplicate on high
                        low++; 
                        high--;
                    }
                    //move window
                    else if(sum<target)low++; 
                    else high--;
                }
            }
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA
     */
    int len = 0;
    public List<List<Integer>> fourSum3(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
   private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(index >= len) {
            return res;
        }
        if(k == 2) {
          int i = index, j = len - 1;
          while(i < j) {
                //find a pair
              if(target - nums[i] == nums[j]) {
                List<Integer> temp = new ArrayList<>();
                  temp.add(nums[i]);
                  temp.add(target-nums[i]);
                    res.add(temp);
                    //skip duplication
                    while(i<j && nums[i]==nums[i+1]) i++;
                    while(i<j && nums[j-1]==nums[j]) j--;
                    i++;
                    j--;
                //move left bound
              } else if (target - nums[i] > nums[j]) {
                  i++;
                //move right bound
              } else {
                  j--;
              }
          }
        } else{
            for (int i = index; i < len - k + 1; i++) {
                //use current number to reduce ksum into k-1sum
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
                if(temp != null){
                    //add previous results
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len-1 && nums[i] == nums[i+1]) {
                    //skip duplicated numbers
                    i++;
                }
            }
        }
        return res;
    }

}
